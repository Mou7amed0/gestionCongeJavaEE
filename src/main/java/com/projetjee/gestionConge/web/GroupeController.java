package com.projetjee.gestionConge.web;

import com.projetjee.gestionConge.entities.Groupe;
import com.projetjee.gestionConge.service.IGroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupeController {

    private final IGroupeService iGroupeService;
    @Autowired
    public GroupeController(IGroupeService iGroupeService) {
        this.iGroupeService = iGroupeService;
    }

    @PostMapping(path = "/groupe/add")
    public Groupe addGroupe(@RequestBody Groupe groupe){
        return iGroupeService.addGroupe(groupe);
    }
    @PutMapping(path = "/groupe/update")
    public Groupe updateGroupe(@RequestBody Groupe groupe){
        return iGroupeService.updateGroupe(groupe);
    }
    @DeleteMapping(path = "/groupe/remove/{id}")
    public void removeGroupe(@PathVariable(name="id")Long id){
        iGroupeService.removeGroupe(iGroupeService.getGroupeById(id));
    }
    @GetMapping(path="/RHgroups")
    public String listGroupe(Model model){
    	List<Groupe> listGroupes= iGroupeService.listGroupe();
        model.addAttribute("groupes",listGroupes);
        return "RH/RHgroups";
    }
}
