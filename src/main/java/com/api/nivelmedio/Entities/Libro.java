package com.api.nivelmedio.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table( name = "libro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotBlank( message = "El nombre del libro no puede estar vacio")
    @NotNull( message = "El nombre del libro no puede ser nulo")
    @Pattern( regexp = "^[A-Z ]+$", message = "El nombre del libro debe contener solo letras mayúsculas")
    @Size( max = 20, message = "El nombre del libro no debe contener  mas de 20 caracteres")
    @Column( name = "nombreLibro")
    private String nombreLibro;

    @NotBlank( message = "La editorial no puede estar vacia")
    @NotNull( message = "La editorial no puede ser nulo")
    @Pattern( regexp = "^[A-Z ]+$", message = "La editorial debe contener solo letras mayúsculas")
    @Size( max = 20, message = "La editorial no debe contener  mas de 20 caracteres")
    @Column( name = "editorial" )
    private String editorial;

    @NotBlank( message = "La descripcion no puede estar vacia")
    @NotNull( message = "La descripcion no puede ser nula ")
    @Pattern(regexp = "^[A-Z0-9 ]+$", message = "La descripcion debe contener solo letras mayúsculas o números")
    @Column( name = "descripcion" )
    private String descripcion;


    @NotNull( message = "El numero de paginas no puede ser nulo")
    @Min(value = 1, message = "El número de páginas debe ser mayor que cero")
    @Digits(integer = 10, fraction = 0, message = "El número de páginas debe ser un valor numérico sin decimales")
    @Column( name = "noPags")
    private Integer noPags;

    //RELACION DE MUCHOS A UNO
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "autor_id",nullable = false)  // Nombre de la columna que será la clave foránea en la tabla Libro
    private Autor autor;

/*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNoPags() {
        return noPags;
    }

    public void setNoPags(int noPags) {
        this.noPags = noPags;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;

    }

    public Autor getAutor() {
        return this.autor;
    }

    public Libro(Long id, String nombreLibro, String editorial, String descripcion, Integer noPags, Autor autor) {
        this.id = id;
        this.nombreLibro = nombreLibro;
        this.editorial = editorial;
        this.descripcion = descripcion;
        this.noPags = noPags;
        this.autor = autor;
    }

    public Libro() {
    }*/
}
