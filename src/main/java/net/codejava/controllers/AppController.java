package net.codejava.controllers;

import java.util.List;

import net.codejava.entity.Product;
import net.codejava.entity.User;
import net.codejava.reposotory.UserRepository;
import net.codejava.service.ProductService;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@Autowired
	private UserService service;

	@Autowired
	private ProductService serviceProduct;

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("")
	public String viewHomePage(Model model) {
		List<Product> listProducts = serviceProduct.listAll();
		model.addAttribute("listProducts", listProducts);

		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		service.registerDefaultUser(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
}
