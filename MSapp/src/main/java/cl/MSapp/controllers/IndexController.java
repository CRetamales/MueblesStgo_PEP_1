package cl.MSapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    //Pagina de inicio

    @GetMapping({"/", "/index"})
    public String index(){
        return "index";
    }
}
