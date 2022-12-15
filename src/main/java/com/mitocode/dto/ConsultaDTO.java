package com.mitocode.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConsultaDTO {

    private Integer idConsulta;

    private PacienteDTO paciente;

    private MedicoDTO medico;

    private EspecialidadDTO especialidad;

    private String numConsultorio;

}
