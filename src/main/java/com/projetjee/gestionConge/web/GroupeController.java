package com.projetjee.gestionConge.web;

import com.projetjee.gestionConge.entities.Groupe;
import com.projetjee.gestionConge.entities.Salarie;
import com.projetjee.gestionConge.service.IGroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "/groupe")
public class GroupeController {

    private final IGroupeService iGroupeService;
    @Autowired
    public GroupeController(IGroupeService iGroupeService) {
        this.iGroupeService = iGroupeService;
    }

    @PostMapping(path = "/add")
    public Groupe addGroupe(@RequestBody Groupe groupe){
        return iGroupeService.addGroupe(groupe);
    }

    @GetMapping("/ajouterNouveauGroupe")
    public String ajouter(Model model){
        Groupe groupe = new Groupe();
        model.addAttribute("groupe", groupe);
        return "ajouterGroupe";
    }

    @GetMapping("/ajouterGroupe")
    public String ajouterGroupe(@ModelAttribute("groupe") Groupe groupe){

        iGroupeService.addGroupe(groupe);
        return "redirect:/groupe/listGroupe";

    }

    @GetMapping(path = "/update")
    public Groupe updateGroupe(@RequestBody Groupe groupe){
        return iGroupeService.updateGroupe(groupe);
    }

    @GetMapping(path = "/remove/{id}")
    public String removeGroupe(@PathVariable(name="id")Long id){
        iGroupeService.removeGroupe(iGroupeService.getGroupeById(id));

        return "redirect:/groupe/listGroupe";
    }
    
   /* @GetMapping(path="/listSalarie")
    public String listSalarie(Model model){
        List<Salarie> listSalarie= iSalarieService.listSalarie();
        model.addAttribute("employes",listSalarie);
        return "employes";
    }*/
    
    @GetMapping(path="/listGroupe")
    public String listGroupe(Model model){
    	List<Groupe> listGroupes= iGroupeService.listGroupe();
        model.addAttribute("groupes",listGroupes);
        return "groupes";
    }
}
