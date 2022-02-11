package com.projetjee.gestionConge.web;
import com.projetjee.gestionConge.entities.*;
import com.projetjee.gestionConge.service.IDemandeService;
import com.projetjee.gestionConge.service.IGroupeService;
import com.projetjee.gestionConge.service.ISalarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class DemandeController {
    private final IDemandeService iDemandeService;
    private final IGroupeService iGroupeService;
    private final ISalarieService iSalarieService;

    @Autowired
    public DemandeController(IDemandeService iDemandeService, IGroupeService iGroupeService, ISalarieService iSalarieService) {
        this.iDemandeService = iDemandeService;
        this.iGroupeService = iGroupeService;
        this.iSalarieService = iSalarieService;
    }
    @PostMapping(path = "/add")
    public DemandeConge addDemandeConge(@RequestBody DemandeConge demandeConge){
        return iDemandeService.addDemandeConge(demandeConge);
    }
    @PutMapping(path = "/update")
    public DemandeConge updateDemandeConge(@RequestBody DemandeConge demandeConge){
        return iDemandeService.updateDemandeConge(demandeConge);
    }
    @DeleteMapping(path = "/remove/{id}")
    public void removeDemandeConge(@PathVariable(name="id")Long id){
        iDemandeService.removeDemandeConge(iDemandeService.getDemandeCongeById(id));
    }
    @GetMapping(path="/RHdemands")
    public String listDemandeConge(Model model){
        List<DemandeConge> listDemandeConge= iDemandeService.listDemandeConge();
        List<Groupe> groupeList=iGroupeService.listGroupe();
        model.addAttribute("listDemandeConge",listDemandeConge);
        model.addAttribute("groupes",groupeList);
        return "RH/RHdemands";
    }
    @GetMapping("/demand/NewDemand")
    public String nvdemand(Model model){
        return "Standard/AddDemand";
    }

    @GetMapping("/demand/validate/{id}")
    public String validate(Model model,@PathVariable(name="id")Long id){
        iDemandeService.validerDemandeConge(id);
        return "redirect:/RHdemands";
    }
    @GetMapping("/demand/approuve/{id}")
    public String approuver(Model model,@PathVariable(name="id")Long id){
        iDemandeService.approuverDemandeConge(id);
        return "redirect:/Chefhome";
    }
    @GetMapping("/demand/refuse/{id}")
    public String refuse(Model model,@PathVariable(name="id")Long id){
        iDemandeService.refuserDemandeConge(id);
        return "redirect:/RHdemands";
    }

    @GetMapping("/ajouterNouvelleDemande")
    public String demander(@ModelAttribute("profile") Salarie profile, Model model){
        Conge conge = new Conge();
        model.addAttribute("demande", conge);
        Salarie salarie = profile;
        model.addAttribute("salarie", salarie);
        return "Standard/AddDemand";
    }
    @GetMapping("/ajouterDemande")
    public String ajouterDemande(@ModelAttribute("demande") Conge conge){
        DemandeConge demandeConge = new DemandeConge();
        Salarie salarie=iSalarieService.getSalarieById(28L);
        iDemandeService.addDemandeConge(demandeConge,conge,salarie);
        return "redirect:/Standardhome";
    }

}
