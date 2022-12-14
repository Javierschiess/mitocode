package com.mitocode.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rol")
public class Rol {

    @Id
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @Column(name = "descripcion", nullable = true, length = 150)
    private String descripcion;

}

