package com.projetjee.gestionConge.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CalenderController {
    @GetMapping(path = "/RHcalender")
    public String listSalaries(Model model) {
        return "RH/RHcalender";
    }
}
