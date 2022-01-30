package com.projetjee.gestionConge.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class LoginController {
	
	@GetMapping("/login")
    public String login(Model model) {
    	String login = "login", password = "password";
    	model.addAttribute("login", login);
    	model.addAttribute("password", password);
    	return "sign-in";
    }
    
    @GetMapping("/connexion")
    public String connexion() {
    	
    	return "salarie-home";
    }

}
