package com.api.nivelmedio.Services;

import com.api.nivelmedio.Entities.Autor;
import com.api.nivelmedio.Entities.Libro;
import com.api.nivelmedio.Repositories.AutorRepository;
import com.api.nivelmedio.Repositories.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroService implements LibroBaseService<Libro> {
    //INYECCION DE DEPENDENCIAS
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Override
    @Transactional
    public Libro save(Long autorId, Libro entity) throws Exception {
        try {
            System.out.println("ID del Autor: " + autorId);

            Autor autor = autorRepository.findById(autorId)
                    .orElseThrow(() -> new Exception("No se encontró el autor con el ID proporcionado."));

            // Verificar si el libro ya existe
            if (entity.getId() != null && libroRepository.existsById(entity.getId())) {
                // Si existe, recuperar la instancia existente
                Optional<Libro> existingLibroOptional = libroRepository.findById(entity.getId());

                if (existingLibroOptional.isPresent()) {
                    Libro existingLibro = existingLibroOptional.get();

                    // Asignar los valores recibidos a la instancia existente
                    existingLibro.setNombreLibro(entity.getNombreLibro());
                    existingLibro.setEditorial(entity.getEditorial());
                    existingLibro.setDescripcion(entity.getDescripcion());
                    existingLibro.setNoPags(entity.getNoPags());

                    // Cargar completamente la entidad Autor antes de establecer la relación
                    Autor loadedAutor = autorRepository.getOne(autorId);
                    existingLibro.setAutor(loadedAutor);

                    // Guardar el libro en la base de datos
                    Libro savedLibro = libroRepository.save(existingLibro);

                    return savedLibro;
                } else {
                    throw new Exception("No se encontró el libro con ID: " + entity.getId());
                }
            } else {
                // Si no existe, asignar el autor y guardar el libro
                entity.setAutor(autor);
                Libro savedLibro = libroRepository.save(entity);

                return savedLibro;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al guardar el libro: " + e.getMessage());
        }
    }


}
