package com.projetjee.gestionConge.web;
import com.projetjee.gestionConge.entities.DemandeConge;
import com.projetjee.gestionConge.service.IDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DemandeController {
    private final IDemandeService iDemandeService;
    @Autowired
    public DemandeController(IDemandeService iDemandeService) {
        this.iDemandeService = iDemandeService;
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
        model.addAttribute("listDemandeConge",listDemandeConge);
        return "RH/RHdemands";
    }
}
