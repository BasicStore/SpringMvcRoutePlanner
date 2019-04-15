package com.routeplanner.ctrl;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.routeplanner.front.dm.User;


@Controller
@RequestMapping("/routeplanner")
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
		
	
	@GetMapping("/")
	public String welcomeToApp(HttpServletRequest request, Model model) {
		return greetingForm(request, model);
	}
    
	
	@GetMapping("/login")
    public String greetingForm(HttpServletRequest request, Model model) {
		String currLocation = System.getProperty("user.dir");    	
		logger.info("logging from tomcat with slf4j - current location: " + currLocation);
		logger.info("in the get request for login");

		
		model.addAttribute("user", new User());
    	
    	// SESSION tests [start] ------------------------------------
    	//ShoppingCart cart = (ShoppingCart)request.getSession().setAttribute("cart",value);
//    	Incident mockInc = new Incident();
//    	mockInc.setReporter(new Person("authFirstName", "auth2ndName", "authAddress"));
//    	mockInc.setId(104);
//    	request.getSession().setAttribute("mockInc", mockInc);
    	// Session tests [end] ------------------------------------    	
    	
    	
    	
    	// RESOURCE FILE: testing accessing a configuration file which can be done from any java class
    	ResourceBundle rb = ResourceBundle.getBundle("config.sysprops");
    	String value = rb.getString("database.name");
    	System.out.println("working with database:  " + value);
    	
    	
        return "login";
    }


    
    @PostMapping("/login")
    public ModelAndView greetingSubmit(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute User login, BindingResult errors) {
    	logger.info("in the post request for login");
    	
    	if (errors.hasErrors() || pageHasBlankMandatoryFields(login)) {
    		System.out.println("ERRORS ON LOGIN FORM!!!!!!!");
    		logger.info("errors exist on login request form");
    		return new ModelAndView("login");
    	}    	
    	
    	// ENUM
    	// model.addAttribute("mStatusList", MaritalStatus.values());
    	
    	
    	// ADD OBJECT TO MODELANDVIEW + VIEW NAME 
    	// Person author = getPersonFromDb(login);
    	//Person author = new Person("MyFirstName", "MyLastname", "MyAddress");
    	//ModelAndView mv = new ModelAndView("person");
    	//mv.addObject("person", author);
    	
    	
    	ModelAndView mv = new ModelAndView("?????");
    	
    	
    	return mv;
    }

    

  private static boolean pageHasBlankMandatoryFields(User login) {
	  //return login.getUsername().isEmpty() || login.getPass().isEmpty() ? true : false;
	  return true;
  }

  
  //   
//  
//  private Person getPersonFromDb(User login) {
//	  String userName = login.getUsername();
//  	  String userPass = login.getPass();
//  	  System.out.println("TODO  Get person object from storage: user/pass = " + userName + "/" + userPass);
//  	  
//	  Person p = new Person();
//	  p.setFirstName("[UserPlaceholder]  username");
//  	  p.setLastName("[UserPlaceholder] pass");
//  	  
//  	  LocalDate dateOfBirth = LocalDate.of(1950, 4, 14);
//  	  p.setDateOfBirth(dateOfBirth);
//  	  
//  	  
//  	  return p;
//  }
}