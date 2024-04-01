package com.api.nivelmedio.Services;

import com.api.nivelmedio.Entities.Autor;
import com.api.nivelmedio.Repositories.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AutorService implements BaseService<Autor> {

    //Inyeccion de dependencias
    @Autowired
    private AutorRepository autorRepository;

    //Mostrar todos
    @Override
    @Transactional

    public List<Autor> findByEstado(Integer estado) throws Exception {
        try {
            if (autorRepository.existsByEstado(estado)) {
                List<Autor> autores = autorRepository.findByEstado(estado);

                // Para cada autor, cargar los libros asociados
                for (Autor autor : autores) {
                    autor.getLibros().size();  // Forzar la carga de los libros (evita LazyInitializationException)
                }

                return autores;
            } else {
                throw new Exception("No se encontraron autores con el estado proporcionado.");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar autores: " + e.getMessage());
        }
    }



    @Override
    @Transactional
    public Autor findById(Long id) throws Exception {
        try {
            if(autorRepository.existsById(id)) {
                Optional<Autor> entityOptional = autorRepository.findById(id);
                return entityOptional.get();
            } else {
                throw new Exception();
            }

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Autor save(Autor entity, Integer estado) throws Exception {
        try {
            // Establecer el estado predeterminado
            entity.setEstado(estado);
            entity = autorRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public Autor update(Long id, Autor entity) throws Exception {
        try {
            Optional<Autor> entityOptional = autorRepository.findById(id);

            if (entityOptional.isPresent()) {
                Autor existingAutor = entityOptional.get();

                // Actualizar los campos de la entidad existente con los valores de la entidad recibida
                existingAutor.setNombre_autor(entity.getNombre_autor());
                existingAutor.setApp(entity.getApp());
                existingAutor.setApm(entity.getApm());
                existingAutor.setFecha_nacimiento(entity.getFecha_nacimiento());

                // Guardar la entidad actualizada
                existingAutor = autorRepository.save(existingAutor);

                return existingAutor;
            } else {
                String errorMessage = "No se encontró el autor con ID: " + id;
                throw new Exception(errorMessage);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Autor updateEstado(Long id) throws Exception {
        try {
            Optional<Autor> entityOptional = autorRepository.findById(id);

            if (entityOptional.isPresent()) {
                Autor existingAutor = entityOptional.get();

                // Guardar los valores actuales de los campos sujetos a restricciones
                String originalNombreAutor = existingAutor.getNombre_autor();
                String originalApp = existingAutor.getApp();
                String originalApm = existingAutor.getApm();

                // Actualizar solo el estado de la entidad existente
                existingAutor.setEstado(0);

                // Restaurar los valores originales para campos sujetos a restricciones
                existingAutor.setNombre_autor(originalNombreAutor);
                existingAutor.setApp(originalApp);
                existingAutor.setApm(originalApm);

                // Guardar la entidad actualizada
                existingAutor = autorRepository.save(existingAutor);

                return existingAutor;
            } else {
                String errorMessage = "No se encontró el autor con ID: " + id;
                throw new Exception(errorMessage);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
