package com.mitocode.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Integer idEspecialidad;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = true, length = 50)
    private String descripcion;
}
