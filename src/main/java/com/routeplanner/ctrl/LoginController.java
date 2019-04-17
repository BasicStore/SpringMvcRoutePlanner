package com.routeplanner.ctrl;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
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
import com.routeplanner.shopping.RoleLevel;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.User;


@Controller
@RequestMapping("/routeplanner")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
		
	// TODO move this with the route engine
	final static ResourceBundle mybundle = ResourceBundle.getBundle("application");
	
	@Autowired
	private TravelInfoService travelInfoService;
	
	
	@GetMapping("/")
	public String welcomeToApp(HttpServletRequest request, Model model) {
		return greetingForm(request, model);
	}
    
	
	
	@GetMapping("/login")
    public String greetingForm(HttpServletRequest request, Model model) {
		String currLocation = System.getProperty("user.dir");    	
		logger.info("logging from tomcat with slf4j - current location: " + currLocation);
		logger.info("in the get request for login");
		
		// bind a user object for the current user to sign in
		User user = new User();
		user.setRoleLevel(RoleLevel.USER);
		model.addAttribute("user", user);
    	
    	// RESOURCE FILE: testing accessing a configuration file which can be done from any java class
    	ResourceBundle rb = ResourceBundle.getBundle("config.sysprops");
    	String value = rb.getString("database.name");
    	logger.info("working with database:  " + value);
    	
        return "login";
    }


	
    
    @PostMapping("/login")
    public ModelAndView greetingSubmit(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute User loginUser, BindingResult errors) {
    	logger.info("Dealing with login request");
    	if (errors.hasErrors() || pageHasBlankMandatoryFields(loginUser)) {
    		// TODO apply login form validation here...........
    		logger.info("errors exist on login request form");
    		return new ModelAndView("login");
    	}    	
    	
    	// TODO get this from the database with username and pass via service class
    	User validUser = new User(); 
    	if (validUser == null) {
    		// bind a user object for the current user to sign in
    		User user = new User();
    		user.setRoleLevel(RoleLevel.USER);
    		model.addAttribute("user", user);
    		return new ModelAndView("login");
    	}
    	
    	logger.info("Username = " + loginUser.getUsername());
    	logger.info("Password = " + loginUser.getPassword());
    	
    	// add static full station list to session
    	request.getSession().setAttribute("stationList", travelInfoService.getStationList());

    	
    	validUser.setRoleLevel(RoleLevel.ADMIN);   // TODO  remove this eventually as it will be set from the database
    	request.getSession().setAttribute("shopping", new Basket(validUser));  // TODO this needs to be changed to AnstractShopping............

    	// set up the new route query, at this stage it is just a query, so is not part of the shopping session variable
    	model.addAttribute("routeQuery", new RouteQuery());
    	
    	return new ModelAndView("query");
    }

    
    
    
    // returning after initial search
    @PostMapping("/new-search")  // 
    public ModelAndView findAnotherRoute(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute User loginUser, BindingResult errors) {
    	
    	// get user object from the basket
    	
    	// delete the existing basket and create a new one from scratch 
    	
    	
    	// TODO test only
		User user = new User();
		user.setRoleLevel(RoleLevel.USER);
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