package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.Login;
import com.projetjee.gestionConge.entities.Salarie;
import com.projetjee.gestionConge.repository.SalarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class LoginServiceImpl implements ILoginService {

	@Autowired
	private SalarieRepository salarieRepository;
	@Override
	public Salarie connextion(Login lp) {
		return salarieRepository.findByIdLogin(lp.getUsername());
	}

	@Override
	public String deconnextion() {
		
		return "redirect:/login";
	}

}
