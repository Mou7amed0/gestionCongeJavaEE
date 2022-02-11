package com.projetjee.gestionConge.web;

import com.projetjee.gestionConge.entities.DemandeConge;
import com.projetjee.gestionConge.entities.Groupe;
import com.projetjee.gestionConge.entities.Salarie;
import com.projetjee.gestionConge.service.IDemandeService;
import com.projetjee.gestionConge.service.IGroupeService;
import com.projetjee.gestionConge.service.ISalarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("session")
public class SalarieController {
	
    private final ISalarieService iSalarieService;
    private final IDemandeService iDemandeService;
    private final IGroupeService iGroupeService;

    @Autowired
    public SalarieController(ISalarieService iSalarieService, IDemandeService iDemandeService, IGroupeService iGroupeService) {
        this.iSalarieService = iSalarieService;
        this.iDemandeService = iDemandeService;
        this.iGroupeService = iGroupeService;
    }
    
    @GetMapping("/salarie/ajouterNouveauSalarie")
    public String ajouter(Model model){
        Salarie salarie = new Salarie();
        model.addAttribute("salarie", salarie);
        return "RH/RHaddEmp";
    }
    
    @GetMapping("/salarie/ajouterSalarie")
    public String ajouterSalarie(@ModelAttribute("salarie") Salarie salarie){
        // Ajouter le salarié à la base de données
        iSalarieService.addSalarie(salarie);
        return "redirect:/RHhome";
    }
    
    @GetMapping("/salarie/modifierSalarie/{id}")
    public String modifier(@PathVariable (value = "id") long id, Model model){
        Salarie salarie = iSalarieService.getSalarieById(id);
        model.addAttribute("salarie", salarie);
        return "modifierSalarie";
    }
    
    @GetMapping("/salarie/modifierSalarie")
    public String modifierSalarie(@ModelAttribute("salarie") Salarie salarie){
        iSalarieService.updateSalarie(salarie);
        return "redirect:/RHhome";
    }

    @GetMapping(path = "/salarie/remove/{id}")
    public String removeSalarie(@PathVariable(name="id")Long id){
        iSalarieService.removeSalarie(iSalarieService.getSalarieById(id));
        return "redirect:/RHhome";
    }

    @GetMapping(path="/RHhome")
    public String listSalaries(@ModelAttribute("salarie") Salarie salarie, @SessionAttribute("profile") Salarie profile,Model model){
        List<Salarie> listSalarie= iSalarieService.listSalarie();
        model.addAttribute("profile", profile);
        model.addAttribute("employes",listSalarie);
        List<Groupe> groupeList=iGroupeService.listGroupe();
        model.addAttribute("groupes",groupeList);
        return "RH/RHhome";
    }

    @GetMapping("/Standardhome")
    public String stdHome( @SessionAttribute("profile") Salarie profile,Model model){

        List<DemandeConge> demandesConge=iDemandeService.findDemandeCongeByidSalarie(profile.getId_salarie());

        model.addAttribute("profile", profile);
        model.addAttribute("demandes", demandesConge);
        return "Standard/Standardhome";
    }
    @GetMapping("/Chefhome")
    public String chefhome( @SessionAttribute("profile") Salarie profile,Model model){

        List<DemandeConge> demandesConge=iDemandeService.listDemandeCongeByGroupe(profile.getGroupe().getId_groupe());

        model.addAttribute("profile", profile);
        model.addAttribute("demandes", demandesConge);
        return "Chef/Chefdemands";
    }



}

