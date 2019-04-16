package com.routeplanner.ctrl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

import com.routeplanner.dm.IRouteMap;
import com.routeplanner.ex.InvalidNetworkException;
import com.routeplanner.load.RouteMapReader;
import com.routeplanner.shopping.RoleLevel;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.User;

// import com.routeplanner.dm.User;


@Controller
@RequestMapping("/routeplanner")
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
		
	// TODO move this with the route engine
	final static ResourceBundle mybundle = ResourceBundle.getBundle("application");
	
	
	@GetMapping("/")
	public String welcomeToApp(HttpServletRequest request, Model model) {
		return greetingForm(request, model);
	}
    
	
	
	@GetMapping("/login")
    public String greetingForm(HttpServletRequest request, Model model) {
		String currLocation = System.getProperty("user.dir");    	
		logger.info("logging from tomcat with slf4j - current location: " + currLocation);
		logger.info("in the get request for login");
		

		// TODO test only
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
    	logger.info("in the post request for login");
    	if (errors.hasErrors() || pageHasBlankMandatoryFields(loginUser)) {
    		System.out.println("ERRORS ON LOGIN FORM!!!!!!!");
    		logger.info("errors exist on login request form");
    		return new ModelAndView("login");
    	}    	
    	
    	logger.info("Username = " + loginUser.getUsername());
    	logger.info("Password = " + loginUser.getPassword());
    	
    	// TODO request.getSession().setAttribute("xxx",zzz);
    	loginUser.setRoleLevel(RoleLevel.ADMIN);   // TODO  set this up properly here.................
    	request.getSession().setAttribute("user", loginUser);  // TODO this needs to be changed to AnstractShopping............
    	
    	//${session.user.roleLevel.id}
    	
    	
    	
    	List<String> stationList = getStationList(); //Arrays.asList("Station1", "Station2", "Station3");
    	request.getSession().setAttribute("stationList", stationList);

    	model.addAttribute("routeQuery", new RouteQuery());
    	
    	ModelAndView mv = new ModelAndView("query");
    	
    	return mv;
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
	
	
	  // TODO move this................	
	public List<String> getStationList() {
		IRouteMap mapData;
		try {
			mapData = loadSystemData();
			RouteMapReader reader = new RouteMapReader();
			List<String> stationList = reader.getListAllStations(mapData);
			return stationList;
		} catch(FileNotFoundException e) {
			logger.info("File not found\n");
			logger.info("");
		} catch(IOException e) {
			logger.info("IOException \n");
			logger.info(e.getMessage());
		} catch(InvalidNetworkException e) {
			logger.info("Invalid Network\n");
			logger.info(e.getMessage());
		} 
		
		return new ArrayList<String>();
	}    

  

  // TODO move this................
	public IRouteMap loadSystemData() throws IOException, FileNotFoundException, InvalidNetworkException {
		RouteMapReader reader = new RouteMapReader();
		return reader.buildIRouteMap(mybundle.getString("route.map.xml"));
	}
   
  
}