package com.routeplanner.ctrl;
import java.util.Collection;
import java.util.Optional;
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
import com.routeplanner.repository.BasketRepository;
import com.routeplanner.repository.UserRepository;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.PassengerType;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.TicketType;
import com.routeplanner.shopping.User;


@Controller
@RequestMapping("/routeplanner")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
		
	@Autowired
	private TravelInfoService travelInfoService;
	
	// TODO NOT GREAT TO PUT THESE HERE, WRITE A SERVICE TIER FOR THIS.......!!!!!!!!!
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BasketRepository basketRepository;
	
	
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
    	HttpSession sess = request.getSession();
    	sess.setAttribute("stationList", travelInfoService.getStationList());
    	
    	// get an existing open basket for this user from the database
    	Basket openBasket = (Basket)basketRepository.findOpenBasketForUser(user.getId());
    	
    	// if no shopping and basket exist in the session, then add them
    	if (request.getSession().getAttribute("shopping") == null) {
    		Shopping shopping = new Shopping(user);

    		// create a new open basket if one doesn't exist in the database
    		if (openBasket == null) {
    			openBasket = new Basket(user);
    			basketRepository.save(openBasket);
    		}
    		
    		// put the user's open basket into the session
    		shopping.setBasket(openBasket);
    		sess.setAttribute("shopping", new Shopping(user));
    	}
    }
        
    
    
    // TODO replace eventually with spring security
    private User getLoginUser(HttpServletRequest request, String gvnUsername) {
    	try {
			logger.info("Attempting to retrieve user '" + gvnUsername + "' from database");
			Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
			Optional<User> dbuser = userRepository.findByUsername(gvnUsername);
			if (Optional.ofNullable(dbuser).isPresent()) {
				logger.info("user has been retrieved successfully from db");
				return dbuser.get();
			}
		} catch(Exception e) {
			logger.info("Error retrieving user from db: " + e.getMessage());
		}
    	
    	return null;
    }
    
    
    
    
    // returning after initial search
    @PostMapping("/new-search")  // 
    public ModelAndView findAnotherRoute(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute User loginUser, BindingResult errors) {
    	
    	// get user object from the basket
    	
    	// delete the existing basket and create a new one from scratch 
    	
    	
    	// TODO test only
		User user = new User();
		model.addAttribute("user", user);
    	
		model.addAttribute("routeQuery", new RouteQuery());
		
    	
    	ModelAndView mv = new ModelAndView("query");
    	
    	return mv;
    }
    

    private static boolean pageHasBlankMandatoryFields(User login) {
	//	  return (login == null || StringUtils.isBlank(login.getUsername()) || StringUtils.isBlank(login.getUsername())) ? true : false;
	    return false;	  
	}
	
  
}