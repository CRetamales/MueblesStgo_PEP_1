package cl.MSapp.controllers;

import cl.MSapp.entities.Reportes;
import cl.MSapp.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/reportes")
public class ReportesController {

    @Autowired
    private ReportesService reportesService;

    @GetMapping
    public String listAllReportes(Model model){
        List<Reportes> reportes = reportesService.listAllReportes();
        ResponseEntity.ok(reportes);
        model.addAttribute("reportes", reportes);
        return "reportes";
    }
}
