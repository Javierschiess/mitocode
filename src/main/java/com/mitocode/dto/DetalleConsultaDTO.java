package com.mitocode.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleConsultaDTO {

    private Integer idDetalle;

    private ConsultaDTO consulta;

    private String diagnostico;

    private String tratamiento;


}
