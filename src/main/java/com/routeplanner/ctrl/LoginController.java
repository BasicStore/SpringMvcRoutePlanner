package com.routeplanner.ctrl;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.routeplanner.client.service.TravelInfoService;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.CardType;
import com.routeplanner.shopping.PassengerType;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.TicketType;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.ex.UsernameNotFoundException;
import com.routeplanner.shopping.service.BasketService;
import com.routeplanner.shopping.service.UserService;


@Controller
@RequestMapping("/routeplanner")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	// TODO this must be dynamic
	final ResourceBundle prop = ResourceBundle.getBundle("messages", Locale.FRANCE);
	
	
	@Autowired
	private TravelInfoService travelInfoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BasketService basketService;
	
	
	public LoginController() {
		
	}
	
	@GetMapping("/")
	public String welcomeToApp(HttpServletRequest request, Model model) {
		return displayLoginForm(request, model);
	}
    	
	
	@GetMapping("/login")
    public String displayLoginForm(HttpServletRequest request, Model model) {
		String currLocation = System.getProperty("user.dir");    	
		logger.info("logging from tomcat with slf4j - current location: " + currLocation);
		logger.info("in the get request for login");
		
		// bind a user object for the current user to sign in
		User user = new User();
		logger.info("check new user role is USER ==> " + user.getRoleLevel().getRoleName());
		model.addAttribute("user", user);
    	
    	// RESOURCE FILE: testing accessing a configuration file which can be done from any java class
    	ResourceBundle rb = ResourceBundle.getBundle("config.sysprops");
    	String value = rb.getString("database.name");
    	logger.info("working with database:  " + value);
    	
        return "login";
    }


	
    
    @PostMapping("/login")
    public ModelAndView attemptLogin(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute User loginUser, BindingResult errors) {
    	logger.info("Dealing with login request");
    	if (errors.hasErrors() || pageHasBlankMandatoryFields(loginUser)) {
    		// TODO apply login form validation here...........
    		logger.info("errors exist on login request form");
    		return new ModelAndView("login");
    	}    	
    	
    	User dbUser = getLoginUser(request, loginUser.getUsername());
    	if (dbUser == null) {
    		User newusr = new User();
			model.addAttribute("user", newusr);
    		return new ModelAndView("login");
    	}
    	
    	logger.info("User found in database with username = '" + dbUser.getUsername() + "', and role '" 
    			+ dbUser.getRoleLevel().getRoleName() + "'");
    	
    	initSessVars(request, dbUser);

    	// set up the new route query, at this stage it is just a query, so is not part of the shopping session variable
    	model.addAttribute("routeQuery", new RouteQuery());
    	
    	return new ModelAndView("query");
    }

    
    
    private void initSessVars(HttpServletRequest request, User user) {
    	// setup common static session variables
    	HttpSession sess = request.getSession();
    	sess.setAttribute("stationList", travelInfoService.getStationList());
    	sess.setAttribute("ticketTypeList", TicketType.values());
    	sess.setAttribute("passengerTypeList", PassengerType.values());
    	sess.setAttribute("cardTypeList", CardType.values());
    	sess.setAttribute("titleList", getTitles());
    	sess.setAttribute("currUser", user);
    	
    	// get an existing open basket for this user from the database
    	Basket openBasket = basketService.findOpenBasketForUser(user.getId());
    	
    	// if no shopping and basket exist in the session, then add them
    	if (request.getSession().getAttribute("shopping") == null) {
    		Shopping shopping = new Shopping(user);

    		// create a new open basket if one doesn't exist in the database
    		if (openBasket == null) {
    			openBasket = new Basket(user);
    			basketService.save(openBasket);
    		}
    		
    		// put the user's open basket into the session
    		shopping.setBasket(openBasket);
    		sess.setAttribute("shopping", shopping);
    	}
    }
        
    
    
    private String[] getTitles() {
    	return new String[] {prop.getString("rp.person.title.mr"), 
    			prop.getString("rp.person.title.mrs"), 
    			prop.getString("rp.person.title.miss")};
    }
    
    
    
    // TODO replace eventually with spring security
    private User getLoginUser(HttpServletRequest request, String gvnUsername) {
    	try {
			logger.info("Attempting to retrieve user '" + gvnUsername + "' from database");
			Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
			try {
				User dbuser = userService.findByUsername(gvnUsername);
				logger.info("user has been retrieved successfully from db");
				return dbuser;
			} catch(UsernameNotFoundException e) {
				logger.info("User not found: " + e.getMessage());
				return null;
			}
		} catch(Exception e) {
			logger.info("Error retrieving user from db: " + e.getMessage());
		}
    	
    	return null;
    }
    
    
    
    
    
    @PostMapping("/new-search")  // 
    public ModelAndView findAnotherRoute(HttpServletRequest request, Model model) {
    	Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
    	
    	// returning after initial search - so a shopping object with a valid user should 
    	// exist in the session - logout if this is not the case
    	if (shopping == null || shopping.getUser() == null) {
    		User user = new User();
    		logger.info("resetting user: " + user.getRoleLevel().getRoleName());
    		model.addAttribute("user", user);
    		return new ModelAndView("login");
    	}
    	
    	// delete the existing basket and create a new one from scratch
    	User user = shopping.getUser();
		Basket openBasket = new Basket(user);
		basketService.save(openBasket);
		
		// put the user's open basket into the session
		shopping.startFreshJourneyPrunePreviousPurchase();
		shopping.setBasket(openBasket);
		shopping.setUser(shopping.getUser());
    	
		model.addAttribute("routeQuery", new RouteQuery());
    	return new ModelAndView("query");
    }
    

    private static boolean pageHasBlankMandatoryFields(User login) {
	//	  return (login == null || StringUtils.isBlank(login.getUsername()) || StringUtils.isBlank(login.getUsername())) ? true : false;
	    return false;	  
	}
	
  
}