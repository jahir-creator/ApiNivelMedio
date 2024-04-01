package com.api.nivelmedio.Controllers;

import com.api.nivelmedio.Entities.Autor;
import com.api.nivelmedio.Services.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/libreria")
public class AutorController {
    //Inyeccion de dependencias
    @Autowired
    private AutorService autorService;

    //Mostrar Todos
    @GetMapping("/autor")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(autorService.findByEstado(1));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Advertencia\":\"No hay autores por mostrar\"}");
        }
    }

    //Mostrar detalle
    @GetMapping("/autor/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        try {
            Long numericId = Long.parseLong(id);
            if(numericId > 0){
                return ResponseEntity.status(HttpStatus.OK).body(autorService.findById(numericId));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Advertencia\":\"ID debe ser un número positivo\"}");
            }
        }catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Advertencia\":\"Ruta no encontrada\"}");
        } catch ( Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Advertencia\":\"No se encontró ningún libro con el id\"}");
        }
    }

    //Crear nuevo Autor
    @PostMapping("/autor")
    public ResponseEntity<?> saveAutor(@Valid @RequestBody Autor entity, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                String errorMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + errorMessage + "\"}");
            } else {
                Autor savedAutor = autorService.save(entity, 1);  // 1 es el estado predeterminado
                return ResponseEntity.status(HttpStatus.CREATED).body(savedAutor);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde\"}");
        }
    }

    //Actualizar Registro
    @PutMapping("/autor/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Autor entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(autorService.update(id, entity));
        }catch (Exception e){
            String errorMessage = "{\"error\":\"" + e.getMessage() + "\"}";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    //Eliminar autor
    @DeleteMapping("/autor/{id}")
    public ResponseEntity<?> eliminarAutor(@PathVariable Long id) {
        try {
            Autor savedAutor = autorService.updateEstado(id);
            return ResponseEntity.status(HttpStatus.OK).body(savedAutor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

}
