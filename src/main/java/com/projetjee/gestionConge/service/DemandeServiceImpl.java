package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.*;
import com.projetjee.gestionConge.repository.CongeRepository;
import com.projetjee.gestionConge.repository.DemandeCongeRepository;
import com.projetjee.gestionConge.repository.EventRepository;
import com.projetjee.gestionConge.repository.SalarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final EventRepository eventRepository;

    public DemandeServiceImpl(DemandeCongeRepository demandeCongeRepository,SalarieRepository salarieRepository,CongeRepository congeRepository,EventRepository eventRepository) {
        this.demandeCongeRepository = demandeCongeRepository;
        this.salarieRepository=salarieRepository;
        this.congeRepository=congeRepository;
        this.eventRepository=eventRepository;
    }
    public DemandeConge prepareDemandeConge(DemandeConge demande){
        return this.demandeCongeRepository.save(demande);
    }
    @Override
    public DemandeConge addDemandeConge(DemandeConge demandeConge) {
        Date datenow= new Date();
        demandeConge.setDate_creation(datenow);
        demandeConge.setEtat(Etat.created);
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
            return null;

        }

    }
    public DemandeConge addDemandeConge(DemandeConge demandeConge,Conge conge) {
        Date datenow= new Date();
        demandeConge.setDate_creation(datenow);
        demandeConge.setEtat(Etat.created);
        demandeConge.setConge(conge);
        Salarie salarie =demandeConge.getSalarie();
        LocalDate firstDate=conge.getDate_debut();
        LocalDate secondDate=conge.getDate_fin();
        Duration diff = Duration.between(firstDate.atStartOfDay(), secondDate.atStartOfDay());
        long diffDays = diff.toDays();
        int newSolde=(int) (salarie.getSolde()-diffDays);
        if(newSolde>=0) {
            salarie.setSolde((int) (salarie.getSolde() - diffDays));
            salarieRepository.save(salarie);
            congeRepository.save(conge);
            return demandeCongeRepository.save(demandeConge);

        }
        else{
            return null;

        }

    }
    public DemandeConge addDemandeConge(DemandeConge demandeConge,Conge conge,Salarie salarie) {

        demandeConge.setDate_creation(null);
        demandeConge.setEtat(Etat.created);
        demandeConge.setConge(conge);
        demandeConge.setSalarie(salarie);
        return demandeCongeRepository.save(demandeConge);

        }


    public DemandeConge addDemandeConge(DemandeConge demandeConge,Salarie salarie) {
        Date datenow= new Date();
        demandeConge.setDate_creation(datenow);
        demandeConge.setEtat(Etat.created);
        Conge conge=demandeConge.getConge();
        demandeConge.setSalarie(salarie);
        salarie.getDemandeConges().add(demandeConge);
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
            return null;

        }

    }

    @Override
    public List<DemandeConge> findDemandeCongeByidSalarie(long id) {
        return demandeCongeRepository.findDemandeCongeByidSalarie(id);
    }


    @Override
    public void removeDemandeConge(DemandeConge demandeConge) {
        if(demandeConge.getEtat().equals(Etat.refused)) return ;
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
        //dans le cas de approved ou validated or refused personnel n'a pas le droit de modifier
        //son demande ça veut dire dans l'interface n'a plus le droit au button update
        if(!demandeConge.getEtat().equals(Etat.created)) return null;

        DemandeConge demandeConge1 =getDemandeCongeById(demandeConge.getId_demande());
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

   // @Override
   public List<DemandeConge> listDemandeCongeByGroupe(Long id) {
        return demandeCongeRepository.findDemandeCongeByGroup(id);
    }

    @Override
    public DemandeConge validerDemandeConge(Long id) {
        DemandeConge demandeq=demandeCongeRepository.findById(id).get();
        demandeq.setEtat(Etat.validated);
        Event event=new Event("congé de "+demandeq.getSalarie().getNom()+" "+demandeq.getSalarie().getPrenom(),(LocalDateTime) demandeq.getConge().getDate_debut().atStartOfDay(),(LocalDateTime)demandeq.getConge().getDate_fin().atStartOfDay());
        eventRepository.save(event);
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
