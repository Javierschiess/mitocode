package com.mitocode.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "detalle_consulta")
public class DetalleConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false, foreignKey = @ForeignKey(name = "FK_detalle_consulta"))
    private Consulta consulta;

    @Column(name = "disgnostico", nullable = false, length = 70)
    private String diagnostico;

    @Column(name = "tratamiento", nullable = false, length = 300)
    private String tratamiento;
}
