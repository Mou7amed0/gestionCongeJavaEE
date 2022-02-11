package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.Conge;
import com.projetjee.gestionConge.entities.DemandeConge;
import com.projetjee.gestionConge.entities.Salarie;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDemandeService {
    DemandeConge addDemandeConge(DemandeConge demandeConge);
    public DemandeConge addDemandeConge(DemandeConge demandeConge, Conge conge);
    public DemandeConge addDemandeConge(DemandeConge demandeConge, Conge conge, Salarie salarie);
    public DemandeConge addDemandeConge(DemandeConge demandeConge, Salarie salarie);
    List<DemandeConge> findDemandeCongeByidSalarie(long id);
    void removeDemandeConge(DemandeConge demandeConge);
    DemandeConge updateDemandeConge(DemandeConge  demandeConge);
    DemandeConge getDemandeCongeById(Long id);
    List<DemandeConge> listDemandeConge();
    List<DemandeConge>listDemandeCongeByGroupe(Long id);
    DemandeConge validerDemandeConge(Long id);
    DemandeConge approuverDemandeConge(Long id);
    DemandeConge refuserDemandeConge(Long id);
}
