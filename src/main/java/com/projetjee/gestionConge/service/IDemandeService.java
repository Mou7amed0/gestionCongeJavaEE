package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.DemandeConge;

import java.util.List;

public interface IDemandeService {
    DemandeConge addDemandeConge(DemandeConge demandeConge);
    void removeDemandeConge(DemandeConge demandeConge);
    DemandeConge updateDemandeConge(DemandeConge  demandeConge);
    DemandeConge getDemandeCongeById(Long id);
    List<DemandeConge> listDemandeConge();
    List<DemandeConge>listDemandeCongeByGroupe(Long id);
    DemandeConge validerDemandeConge(Long id);
    DemandeConge approuverDemandeConge(Long id);
    DemandeConge refuserDemandeConge(Long id);
}
