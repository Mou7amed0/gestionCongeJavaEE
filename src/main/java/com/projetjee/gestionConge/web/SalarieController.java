package com.projetjee.gestionConge.web;

import com.projetjee.gestionConge.entities.Salarie;
import com.projetjee.gestionConge.service.ISalarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SalarieController {
	
    private final ISalarieService iSalarieService;
    
    @Autowired
    public SalarieController(ISalarieService iSalarieService) {
        this.iSalarieService = iSalarieService;
    }
    
    @GetMapping("/salarie/ajouterNouveauSalarie")
    public String ajouter(Model model){
        Salarie salarie = new Salarie();
        LocalDate date = LocalDate.now();
        salarie.setDate_embauche(date);
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
    public String listSalaries(Model model){
        List<Salarie> listSalarie= iSalarieService.listSalarie();
        model.addAttribute("employes",listSalarie);
        return "RH/RHhome";
    }

}

