package com.routeplanner.ctrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	
	@GetMapping("/logout")
	public String logout() {
		return "login";
	}
	
	
	@GetMapping("/purchase_history")
	public String purchaseHistory() {
		return "purchase_history";
	}
	
	
	@GetMapping("/view_basket")
	public String viewBasket() {
		return "view_basket";
	}
	
}
