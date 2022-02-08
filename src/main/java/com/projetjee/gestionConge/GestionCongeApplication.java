package com.projetjee.gestionConge;

import com.projetjee.gestionConge.repository.DemandeCongeRepository;
import com.projetjee.gestionConge.service.IDemandeService;
import com.projetjee.gestionConge.service.IdemandeCongeInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionCongeApplication implements CommandLineRunner{
	@Autowired
	private DemandeCongeRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionCongeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(repo.dadado(2));

	}


}
