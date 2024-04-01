package com.api.nivelmedio.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
//nombre de la tabla
@Table( name = "autor")
//constructor vacio

@NoArgsConstructor
//Constructor
@AllArgsConstructor
//GETTER & SETTER
@Getter
@Setter
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "El id no puede ser nulo")
    private Long id;
    @NotBlank(message = "El auto no puede estar vacio")
    @NotNull(message = "El autor no puede ser nulo")
    @Pattern(regexp = "^[A-Z]+$", message = "El nombre del autor debe contener solo letras mayúsculas")
    @Size(max = 20, message = "El autor no debe contener  mas de 20 caracteres")
    @Column(name = "nombre_autor")
    private String nombre_autor;
    @NotBlank(message = "El apellido paterno no debe estar vacio")
    @NotNull(message = "El apellido paterno no debe se nulo")
    @Pattern(regexp = "^[A-Z]+$", message = "El apellido paterno debe contener solo letras mayúsculas")
    @Size(max = 20, message = "El apellido paterno no debe contener mas de 20 caracteres")
    @Column(name = "app")
    private String app;
    @NotBlank(message = "El apellido materno no debe estar vacio")
    @NotNull(message = "El apellido materno no debe ser nulo")
    @Pattern(regexp = "^[A-Z]+$", message = "El apellido materno debe contener solo letras mayúsculas")
    @Size(max = 20, message = "El apellido materno no debe tener mas de 20 caracteres")
    @Column(name = "apm")
    private String apm;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @Column(name = "fecha_nacimiento")
    private LocalDate fecha_nacimiento;

    //Creacion de metodos si da error lombok
    @Column(name = "estado")
    private Integer estado;

    //RELACION DE UNO A MUCHOS
    @JsonManagedReference
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

/*
    public Integer getEstado() {
        return estado;
    }



    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getNombre_autor() {
        return nombre_autor;
    }

    public void setNombre_autor(String nombre_autor) {
        this.nombre_autor = nombre_autor;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }

    public void setApm(String apm) {
        this.apm = apm;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Long getId() {
        return id;
    }

    public Autor(Long id, String nombre_autor, String app, String apm, LocalDate fecha_nacimiento, Integer estado, List<Libro> libros) {
        this.id = id;
        this.nombre_autor = nombre_autor;
        this.app = app;
        this.apm = apm;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estado = estado;
        this.libros = libros;
    }
    public Autor() {
    }*/
}
