package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.Conge;
import com.projetjee.gestionConge.entities.DemandeConge;
import com.projetjee.gestionConge.entities.Etat;
import com.projetjee.gestionConge.entities.Salarie;
import com.projetjee.gestionConge.repository.CongeRepository;
import com.projetjee.gestionConge.repository.DemandeCongeRepository;
import com.projetjee.gestionConge.repository.SalarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class DemandeServiceImpl implements IDemandeService{
    private final DemandeCongeRepository demandeCongeRepository;
    private final SalarieRepository salarieRepository;
    private final CongeRepository congeRepository;
    @Autowired
    public DemandeServiceImpl(DemandeCongeRepository demandeCongeRepository,SalarieRepository salarieRepository,CongeRepository congeRepository) {
        this.demandeCongeRepository = demandeCongeRepository;
        this.salarieRepository=salarieRepository;
        this.congeRepository=congeRepository;
    }

    @Override
    public DemandeConge addDemandeConge(DemandeConge demandeConge) {
        Date datenow= new Date();
        demandeConge.setDate_creation(datenow);
        Conge conge=demandeConge.getConge();
        Salarie salarie =demandeConge.getSalarie();
        LocalDate firstDate=conge.getDate_debut();
        LocalDate secondDate=conge.getDate_fin();
        Duration diff = Duration.between(firstDate.atStartOfDay(), secondDate.atStartOfDay());
        long diffDays = diff.toDays();
        int newSolde=(int) (salarie.getSolde()-diffDays);
        if(newSolde>=0) {
            salarie.setSolde((int) (salarie.getSolde() - diffDays));
            salarieRepository.save(salarie);
            return demandeCongeRepository.save(demandeConge);

        }
        else{
            return demandeConge;

        }

    }

    @Override
    public void removeDemandeConge(DemandeConge demandeConge) {
        Conge conge=demandeConge.getConge();
        Salarie salarie =demandeConge.getSalarie();
        LocalDate firstDate=conge.getDate_debut();
        LocalDate secondDate=conge.getDate_fin();
        Duration diff = Duration.between(firstDate.atStartOfDay(), secondDate.atStartOfDay());
        long diffDays = diff.toDays();
        salarie.setSolde((int)(salarie.getSolde()+diffDays));
        salarieRepository.save(salarie);
        demandeCongeRepository.delete(demandeConge);

    }

    @Override
    public DemandeConge updateDemandeConge(DemandeConge demandeConge) {
        DemandeConge demandeConge1 =demandeCongeRepository.findById(demandeConge.getId_demande()).get();
        Conge conge1=demandeConge1.getConge();
        LocalDate firstDate1=conge1.getDate_debut();
        LocalDate secondDate1=conge1.getDate_fin();
        Duration diff1 = Duration.between(firstDate1.atStartOfDay(), secondDate1.atStartOfDay());
        long diffDays1 = diff1.toDays();
        Conge conge=demandeConge.getConge();
        LocalDate firstDate=conge.getDate_debut();
        LocalDate secondDate=conge.getDate_fin();
        Duration diff = Duration.between(firstDate.atStartOfDay(), secondDate.atStartOfDay());
        long diffDays = diff.toDays();
        int difference=(int)(diffDays-diffDays1);
        Salarie salarie=demandeConge.getSalarie();
        if(difference!=0){
            salarie.setSolde(salarie.getSolde()+difference);
            salarieRepository.save(salarie);
        }
        return demandeCongeRepository.save(demandeConge);
    }

    @Override
    public DemandeConge getDemandeCongeById(Long id) {
        return demandeCongeRepository.findById(id).get();
    }

    @Override
    public List<DemandeConge> listDemandeConge() {
        return demandeCongeRepository.findAll();
    }

    @Override
    public List<DemandeConge> listDemandeCongeByGroupe(Long id) {
        return demandeCongeRepository.findByGroupe(id);
    }

    @Override
    public DemandeConge validerDemandeConge(Long id) {
        DemandeConge demandeq=demandeCongeRepository.findById(id).get();
        demandeq.setEtat(Etat.validated);
        return demandeCongeRepository.save(demandeq);

    }

    @Override
    public DemandeConge approuverDemandeConge(Long id) {
        DemandeConge demandeq=demandeCongeRepository.findById(id).get();
        demandeq.setEtat(Etat.approuved);
        return demandeCongeRepository.save(demandeq);
    }

    @Override
    public DemandeConge refuserDemandeConge(Long id) {
        DemandeConge demandeq=demandeCongeRepository.findById(id).get();
        demandeq.setEtat(Etat.refused);
        Salarie salarie =demandeq.getSalarie();
        Conge conge=demandeq.getConge();
        LocalDate firstDate=conge.getDate_debut();
        LocalDate secondDate=conge.getDate_fin();
        Duration diff = Duration.between(firstDate.atStartOfDay(), secondDate.atStartOfDay());
        long diffDays = diff.toDays();
        salarie.setSolde((int)(salarie.getSolde()+diffDays));
        salarieRepository.save(salarie);
        return demandeCongeRepository.save(demandeq);
    }


}
