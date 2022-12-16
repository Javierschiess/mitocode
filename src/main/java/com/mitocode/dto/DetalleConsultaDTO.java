package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleConsultaDTO {

    private Integer idDetalle;

    @JsonIgnore
    private ConsultaDTO consulta;

    private String diagnostico;

    private String tratamiento;


}
