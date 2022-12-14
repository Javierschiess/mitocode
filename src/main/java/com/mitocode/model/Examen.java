package com.mitocode.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_examen")
    private Integer idExamen;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = true, length = 150)
    private String descripcion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Examen)) return false;
        Examen examen = (Examen) o;
        return getIdExamen().equals(examen.getIdExamen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdExamen());
    }
}
