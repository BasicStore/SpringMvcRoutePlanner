package com.routeplanner.ctrl;
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
import com.routeplanner.shopping.RegistrationDetails;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.TicketType;
import com.routeplanner.shopping.UpdateUser;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.ex.UsernameNotFoundException;
import com.routeplanner.shopping.service.BasketService;
import com.routeplanner.shopping.service.UserService;
import com.routeplanner.shopping.utils.FormValidation;


@Controller
@RequestMapping("/routeplanner")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
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
		logger.info("check new user role is user: " + user.getRoleLevel().getRoleName());
		model.addAttribute("user", user);
    	
    	// RESOURCE FILE: testing accessing a configuration file which can be done from any java class
    	ResourceBundle rb = ResourceBundle.getBundle("config.sysprops");
    	String value = rb.getString("database.name");
    	logger.info("working with database:  " + value);
    	
        return "login";
    }


	private void addLoginFormErrMsgs(User loginUser, ModelMap model) {
		model.addAttribute("errorMsg", "rp.login.generic.form.error");
		FormValidation.addBlankValidation(loginUser.getUsername(), "usernameErr", model, "rp.login.username-no-value");
		FormValidation.addBlankValidation(loginUser.getPassword(), "passwordErr", model, "rp.login.password-no-value");
	}
		
    
	@PostMapping("/login")
    public ModelAndView login(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute User loginUser, BindingResult errors) {
    	logger.info("Dealing with login request");
    	if (errors.hasErrors() || pageHasBlankMandatoryFields(loginUser)) {
    		addLoginFormErrMsgs(loginUser, model);
    		logger.info("errors exist on login request form");
    		return new ModelAndView("login");
    	}    	
    	
    	User dbUser = getLoginUser(request, loginUser.getUsername(), loginUser.getPassword());
    	if (dbUser == null) {
    		addLoginFormErrMsgs(loginUser, model);
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
    
	
	@GetMapping("/go-to-registration") 
	public String gotToRegistration(HttpServletRequest request, ModelMap model) {
		logger.info("preparing to register a new member");
		RegistrationDetails registerPerson = new RegistrationDetails();
		model.addAttribute("registerPerson", registerPerson);
		HttpSession sess = request.getSession();
		if (sess.getAttribute("titleList") == null) {
			sess.setAttribute("titleList", getTitleLiterals());
		}
		return "registration";
	}
	
	
	@GetMapping("/go-to-change-pass")
	public String gotToChangePassword(HttpServletRequest request, ModelMap model) {
		logger.info("preparing to load change password page");
		model.addAttribute("changePassUsr", new UpdateUser());
		HttpSession sess = request.getSession();
		if (sess.getAttribute("titleList") == null) {
			sess.setAttribute("titleList", getTitleLiterals());
		}
		return "change-pass";
	}

	
	
	
	@PostMapping("/do-change-pass")
	public ModelAndView registerPerson(HttpServletRequest request, ModelMap model, 
			@Valid @ModelAttribute UpdateUser updateUser, BindingResult errors) {
		if (errors.hasErrors()) {
			// TODO implement
			model.addAttribute("errorLineMsg", "rp.");
			
			
			
		}
			
		try {
			
			if (!updateUser.getNewPasword().contentEquals(updateUser.getCfmNewPasword())) {
				logger.info("new user name does not match confirmation field");
				model.addAttribute("changePassUsr", new UpdateUser());
				return new ModelAndView("change-pass");
			}
			
			
			// update user and if successful go back to the login page
			model.addAttribute("user", new User());
			return new ModelAndView("login");
		} catch (Throwable t) {
			// there was a problem saving the password change, so redirect back to change pass page for user to retry
			logger.info("could not persist registration details: " + t.getMessage());
			model.addAttribute("changePassUsr", new UpdateUser());
			return new ModelAndView("change-pass");
		}
	}
	
	/*
	UpdateUser {

	private String oldUsername;
	
	private String oldPassword;
	
	private String newPasword;
	
	private String cfmNewPasword;
	 */
	
	
    
	@PostMapping("/do-registration")
    public ModelAndView registerPerson(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute RegistrationDetails registerPerson, BindingResult errors) {
		logger.info("Dealing with registration request");
		
		// check for errors, and if they exist go back to registration
    	if (errors.hasErrors()) {     //  || pageHasBlankMandatoryFields(registerPerson)
    		model.addAttribute("errorLineMsg", "rp.register.generic.form.error");
    		FormValidation.addBespokeContactDetailsErrMsgs(registerPerson, model);
    		FormValidation.addBlankValidation(registerPerson.getUser().getUsername(), "username", model, "rp.register.bad.username");
    		FormValidation.addBlankValidation(registerPerson.getUser().getPassword(), "password", model, "rp.register.bad.password");
    		logger.info("errors exist on registration request form");
    		model.addAttribute("registerPerson", registerPerson);
    		return new ModelAndView("registration");
    	}    	
		
    	// complete the registration request for member status
		try {
			consolidateUserObject(registerPerson);
			userService.register(registerPerson);
			return login(request, model, registerPerson.getUser(), errors);
		} catch (Throwable t) {
			logger.info("could not persist registration details: " + t.getMessage());
			model.addAttribute("user", new User());
			// TODO add error msg for screen
			return new ModelAndView("login");
		}
	}
	
	
	private static void consolidateUserObject(RegistrationDetails registerPerson) {
		User loginUser = registerPerson.getUser();
		loginUser.setEmail(registerPerson.getEmail());
		loginUser.setActive(1);
	}
	
	
    private void initSessVars(HttpServletRequest request, User user) {
    	// setup common static session variables
    	HttpSession sess = request.getSession();
    	sess.setAttribute("stationList", travelInfoService.getStationList());
    	sess.setAttribute("ticketTypeList", TicketType.values());
    	sess.setAttribute("passengerTypeList", PassengerType.values());
    	sess.setAttribute("cardTypeList", CardType.values());
    	sess.setAttribute("titleList", getTitleLiterals());
    	sess.setAttribute("currUser", user);
    	
    	// get an existing open basket for this user from the database
    	Basket openBasket = basketService.findOpenBasketForUser(user.getId());
    	
    	// if no shopping and basket exist in the session, then add them
    	if (request.getSession().getAttribute("shopping") == null) {
    		Shopping shopping = new Shopping(user);

    		// create a new open basket if one doesn't exist in the database
    		if (openBasket == null) {
    			openBasket = new Basket(user);
    			openBasket.setOpen(true);
    			basketService.save(openBasket);
    		}
    		
    		// put the user's open basket into the session
    		shopping.setBasket(openBasket);
    		sess.setAttribute("shopping", shopping);
    	}
    }
    
    
    
    
    private String[] getTitleLiterals() {
    	return new String[] {"rp.person.title.mr", 
    			"rp.person.title.mrs", 
    			"rp.person.title.miss"};
    }
    
    
    
    // TODO replace eventually with spring security
    private User getLoginUser(HttpServletRequest request, String gvnUsername, String gvnPassword) {
    	try {
			logger.info("Attempting to retrieve user '" + gvnUsername + "' from database");
			Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
			try {
				User dbuser = userService.findByUsername(gvnUsername, gvnPassword);
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
    	openBasket.setOpen(true);
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