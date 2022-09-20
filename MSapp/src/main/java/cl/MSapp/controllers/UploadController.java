package cl.MSapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Controller
public class UploadController {

    //Save the uploaded file to this folder ..static/uploads

    private static final String UPLOAD_DIR = "./src/main/resources/static/uploads/";

    @GetMapping("/upload")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Porfavor elegir su archivo a subir.");
            //return "redirect:/";
            return "redirect:/upload";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        System.out.println("fileName: " + fileName + " extension: " + fileName.substring(fileName.lastIndexOf(".")));
        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "Usted a subido satisfactoriamente el archivo: " + fileName + '!');

        return "redirect:/upload";
    }

}
