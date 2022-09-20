package cl.MSapp.controllers;

import cl.MSapp.entities.Empleados;
import cl.MSapp.services.EmpleadosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequestMapping(value = "/empleados")
@Controller
public class EmpleadosController {

    @Autowired
    private EmpleadosService empleadosService;
    
    @GetMapping
    public String listAllEmpleados(Model model){
        List<Empleados> empleados = empleadosService.listAllEmpleados();
        if(empleados.isEmpty()){
            ResponseEntity.noContent().build();
            return "empleados";
        }
        ResponseEntity.ok(empleados);
        model.addAttribute("empleados", empleados);
        return "empleados";
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Empleados> getEmpleadosById(@PathVariable Long id){
        Empleados empleados = empleadosService.getEmpleadosById(id);
        if(empleados == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleados);
    }

    @PostMapping
    public ResponseEntity<Empleados> createEmpleados(@Valid @RequestBody Empleados empleado, BindingResult result) {
        if(result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Empleados empleadoNuevo = empleadosService.createEmpleados(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoNuevo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Empleados> updateEmpleados(@PathVariable Long id, @RequestBody Empleados empleado) {
        Empleados empleadoNuevo = empleadosService.updateEmpleados(empleado);
        if(empleadoNuevo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleadoNuevo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEmpleados(@PathVariable Long id) {
        Empleados empleadoEliminar = empleadosService.getEmpleadosById(id);
        if(empleadoEliminar == null) {
            return ResponseEntity.notFound().build();
        }
        empleadosService.deleteEmpleados(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/rut/{rut}")
    public ResponseEntity<List<Empleados>> findEmpleadosByRut(@PathVariable String rut) {
        Empleados empleado = empleadosService.findEmpleadosByRut(rut);
        if(empleado == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(List.of(empleado));
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }


}
