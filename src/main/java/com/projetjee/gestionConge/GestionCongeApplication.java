package com.projetjee.gestionConge;

import com.projetjee.gestionConge.service.IdemandeCongeInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionCongeApplication implements CommandLineRunner{
	@Autowired
	private IdemandeCongeInit idemandeCongeInit;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionCongeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//idemandeCongeInit.initGroupe();
		//idemandeCongeInit.initSalarie();
		//idemandeCongeInit.initDemande();

	}

    
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
