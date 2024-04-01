package com.api.nivelmedio.Repositories;

import com.api.nivelmedio.Entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByEstado(Integer estado);
    boolean existsByEstado(Integer estado);


}
