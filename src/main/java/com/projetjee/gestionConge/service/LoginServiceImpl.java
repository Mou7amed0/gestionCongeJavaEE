package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.Login;
import com.projetjee.gestionConge.entities.Salarie;

public class LoginServiceImpl implements ILoginService {

	@Override
	public Salarie connextion(Login lp) {
		
		return null;
	}

	@Override
	public String deconnextion() {
		
		
		return "redirect:/login";
	}

}
