package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.*;
import com.projetjee.gestionConge.repository.CongeRepository;
import com.projetjee.gestionConge.repository.DemandeCongeRepository;
import com.projetjee.gestionConge.repository.GroupeRepository;
import com.projetjee.gestionConge.repository.SalarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Transactional
public class DemandeCongeInitImpl implements IdemandeCongeInit{
    @Autowired
    GroupeRepository groupeRepository;
    @Autowired
    SalarieRepository salarieRepository;
    @Autowired
    CongeRepository congeRepository;
    @Autowired
    DemandeCongeRepository demandeCongeRepository;

    @Override
    public void initGroupe() {

    }

    @Override
    public void initSalarie() {

    }

    @Override
    public void initConge() {

    }

    @Override
    public void initDemande() {

    }
}
