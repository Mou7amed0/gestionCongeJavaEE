package com.projetjee.gestionConge.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetjee.gestionConge.entities.Fonction;

import com.projetjee.gestionConge.entities.Fonction;

@Controller
@RequestMapping(path = "/")
public class LoginController {
	
	private final String ADMIN = "responsableRH";
	private final String CHIEF = "chefDeProjet";
	
	@GetMapping("/login")
    public String login() {
		//    	String login = "login", password = "password";
		//    	model.addAttribute("login", login);
		//    	model.addAttribute("password", password);
    	return "login";
    }
    
//    @GetMapping("/connexion")
//    public String connexion() {
//    	String role = "admin";
//    	switch(role) {
//    	case "admin":
//    		return "salarie";
//    	case "chief":
//    		return "chief-home";
//    	default:
//    		return "salarie-home";
//    	}
//    }

}
