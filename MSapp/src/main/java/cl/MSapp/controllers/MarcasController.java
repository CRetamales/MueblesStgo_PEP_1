package cl.MSapp.controllers;

import cl.MSapp.entities.Empleados;
import cl.MSapp.entities.Marcas;
import cl.MSapp.services.MarcasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
public class MarcasController {

    @Autowired
    private MarcasService marcasService;

    private static final String UPLOAD_DIR = "./src/main/resources/static/uploads/";

    /**
     * Muestra el formulario de carga de archivos
     * @return upload.html
     */
    @GetMapping("/marcas/upload")
    public String index() {
        return "upload";
    }

    @GetMapping(value = "/marcas")
    public String listAllMarcas(Model model){
        List<Marcas> marcas = marcasService.listAllMarcas();
        if(marcas.isEmpty()){
            ResponseEntity.noContent().build();
            return "marcas";
        }
        ResponseEntity.ok(marcas);
        model.addAttribute("marcas", marcas);
        return "marcas";
    }

    @PostMapping("/marcas/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // Se comprueba si el archivo está vacío, en caso de estarlo se redirige al formulario
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Porfavor elegir su archivo a subir.");
            return "redirect:/marcas/upload";
        }

        // Se obtiene el nombre del archivo
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        //System.out.println("fileName: " + fileName + " extension: " + fileName.substring(fileName.lastIndexOf(".")));

        // Se comprueba si el archivo tiene una extensión válida
        if (!fileName.endsWith(".txt")) {
            attributes.addFlashAttribute("message", "Solo se permiten archivos con extensión .txt");
            return "redirect:/marcas/upload";
        }

        // Se guarda el archivo en el path indicado
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Se retorna un mensaje de éxito
        attributes.addFlashAttribute("message", "Usted a subido satisfactoriamente el archivo: " + fileName + '!');


        //Se lee el archivo, para luego guardar los datos en la base de datos
        File archivo = new File(UPLOAD_DIR + fileName);
        try{
            Scanner lector = new Scanner(archivo);
            while(lector.hasNextLine()) {
                String linea = lector.nextLine();
                String partes[] = linea.split(";");

                if(partes.length == 3){
                    Marcas marcas = new Marcas();
                    marcas.setRut(partes[0]);
                    marcas.setFecha(partes[1]);
                    marcas.setHora(partes[2]);
                    marcasService.createMarcas(marcas);
                }
                else {
                    attributes.addFlashAttribute("message", "El archivo no tiene el formato correcto");
                    return "redirect:/marcas/upload";
                }
            }
            lector.close();
        }catch (Exception e){
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }



        // Se redirige al formulario para subir otro archivo en caso de ser necesario
        return "redirect:/marcas/upload";
    }


    public ResponseEntity<Marcas> createMarcas(@Valid @RequestBody Marcas marcas, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Marcas marcasCreate = marcasService.createMarcas(marcas);
        return ResponseEntity.status(HttpStatus.CREATED).body(marcasCreate);
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
