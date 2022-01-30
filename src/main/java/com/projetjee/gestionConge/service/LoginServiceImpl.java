package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.Login;
import com.projetjee.gestionConge.entities.Salarie;

public class LoginServiceImpl implements ILoginService {

	@Override
	public Salarie connextion(Login landp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deconnextion() {
		
		
		return "redirect:/login";
	}

}
