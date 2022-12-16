package com.mitocode.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ConsultaListaExamenDTO {

    private ConsultaDTO consulta;

    private List<ExamenDTO> lstExamen;

}
