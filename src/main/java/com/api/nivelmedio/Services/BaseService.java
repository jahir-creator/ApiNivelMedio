package com.api.nivelmedio.Services;

import com.api.nivelmedio.Entities.Autor;

import java.util.List;

public interface BaseService <E>{

    //Mostrar todos los autores
    public List<E> findByEstado(Integer estado) throws Exception;

    //Mostrar detalle de los autores
    public E findById(Long id) throws Exception;

    //Crear nuevo autor
    //public E save(E entity) throws Exception;
    public E save(E entity, Integer estado) throws Exception;
    //Actualizar autor
    public E update(Long id, E entity) throws Exception;

    //Eliminar
    public E updateEstado(Long id) throws  Exception;


}
