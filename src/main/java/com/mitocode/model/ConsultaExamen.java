package com.mitocode.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "consulta_examen")
@IdClass(ConsultaExamenPK.class)
public class ConsultaExamen {

    @Id
    private Consulta consulta;

    @Id
    private Examen examen;
}
