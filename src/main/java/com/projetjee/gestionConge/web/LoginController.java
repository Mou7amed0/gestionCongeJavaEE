package com.projetjee.gestionConge.web;

import com.projetjee.gestionConge.entities.Salarie;
import com.projetjee.gestionConge.service.SalarieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetjee.gestionConge.entities.Fonction;

import com.projetjee.gestionConge.entities.Fonction;

@Controller
public class LoginController {
	
	private final String ADMIN = "responsableRH";
	private final String CHIEF = "chefDeProjet";

	@GetMapping("/")
	public String home(){


			return "redirect:/RHhome";
	}

	@GetMapping("/login")
    public String login() {
    	return "login";
    }

	@GetMapping("/logout")
	public String logout(){

		return "login";
	}

}
