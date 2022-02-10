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
    	return "login";
    }

}
