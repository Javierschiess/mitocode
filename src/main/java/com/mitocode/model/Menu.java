package com.mitocode.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "id_menu")
    private Integer idMenu;

    @Column(name = "icono", length = 20)
    private String icono;

    @Column(name = "nombre", length = 20)
    private String nombre;

    @Column(name = "url", length = 50)
    private String url;
}
