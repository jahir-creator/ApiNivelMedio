package com.api.nivelmedio.Services;

public interface LibroBaseService <E>{

    //Agregar libro
    public E save(Long autorId,E entity) throws Exception;
}
