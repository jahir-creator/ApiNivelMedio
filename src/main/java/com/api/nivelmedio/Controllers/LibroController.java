package com.api.nivelmedio.Controllers;

import com.api.nivelmedio.Entities.Libro;
import com.api.nivelmedio.Services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/libreria")
public class LibroController {
    @Autowired
    private LibroService libroService;
    @PostMapping("/libro/{autorId}")
    public ResponseEntity<?> saveLibro(@PathVariable Long autorId, @RequestBody Libro entity) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(libroService.save(autorId, entity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde\"}");
        }
    }


}
