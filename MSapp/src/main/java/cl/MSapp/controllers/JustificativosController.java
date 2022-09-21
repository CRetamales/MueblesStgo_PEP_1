package cl.MSapp.controllers;

import cl.MSapp.entities.Justificativos;
import cl.MSapp.services.JustificativosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/justificativos")
public class JustificativosController {

    @Autowired
    private JustificativosService justificativosService;

    @GetMapping
    public String listAllJustificativos(Model model){
        List<Justificativos> justificativos = justificativosService.listAllJustificativos();
        if(justificativos.isEmpty()){
            ResponseEntity.noContent().build();
            return "justificativos";
        }
        ResponseEntity.ok(justificativos);
        model.addAttribute("justificativos", justificativos);
        return "justificativos";
    }
}
