package com.projetjee.gestionConge.web;

import com.projetjee.gestionConge.entities.Login;
import com.projetjee.gestionConge.entities.Salarie;
import com.projetjee.gestionConge.repository.LoginRepository;
import com.projetjee.gestionConge.repository.SalarieRepository;
import com.projetjee.gestionConge.service.SalarieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetjee.gestionConge.entities.Fonction;

import com.projetjee.gestionConge.entities.Fonction;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private SalarieRepository salarieRepository;



	@GetMapping("/sign-in")
    public String login(Model model) {
		Login login = new Login();
		model.addAttribute("login", login);
    	return "login";
    }

	@GetMapping("/connexion")
	public String connexion(@ModelAttribute("login") Login login, Model model, HttpServletRequest request){

		Salarie salarie = salarieRepository.findByIdLogin(login.getUsername());
		model.addAttribute("salarie", salarie);
		request.getSession().setAttribute("profile", salarie);
		if (salarie.getFonction().getLabel().equals("RESPONSABLERH"))
			return "redirect:/RHhome";
		else if(salarie.getFonction().getLabel().equals("CHEFPROJET"))
			return "redirect:/Chefhome";
		else
			return "redirect:/Standardhome";

	}

	@GetMapping("/logout")
	public String logout(){

		return "redirect:/sign-in";
	}

}
