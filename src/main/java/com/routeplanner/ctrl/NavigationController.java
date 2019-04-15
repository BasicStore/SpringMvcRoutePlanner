package com.routeplanner.ctrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/routeplanner")
public class NavigationController {

	public NavigationController() {
	
	}

	
	@GetMapping("/register")
	public String register() {
		return "register";
	}	
	
	
	@GetMapping("/admin_corner")
	public String adminCorner() {
		return "admin_menu";
	}
	
	
}
