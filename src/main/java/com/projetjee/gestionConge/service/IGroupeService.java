package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.Groupe;
import com.projetjee.gestionConge.entities.Salarie;

import java.util.List;

public interface IGroupeService {
    Groupe addGroupe(Groupe groupe);
    void removeGroupe(Groupe groupe);
    Groupe updateGroupe(Groupe  groupe);
    Groupe getGroupeById(Long id);
    List<Groupe> listGroupe();
    List<Salarie>listSalarie(Long id);
}
