package cl.MSapp.controllers;

import cl.MSapp.entities.Horas;
import cl.MSapp.services.HorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/horas")
public class HorasController {

    @Autowired
    private HorasService horasService;

    @GetMapping
    public String listAllHoras(Model model){
        List<Horas> horas = horasService.listAllHoras();
        if(horas.isEmpty()){
            ResponseEntity.noContent().build();
            return "horas";
        }
        ResponseEntity.ok(horas);
        model.addAttribute("horas", horas);
        return "horas";
    }

}