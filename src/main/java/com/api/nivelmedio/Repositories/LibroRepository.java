package com.api.nivelmedio.Repositories;

import com.api.nivelmedio.Entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
