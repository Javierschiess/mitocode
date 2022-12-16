package com.mitocode.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ConsultaDTO {

    private Integer idConsulta;

    private PacienteDTO paciente;

    private MedicoDTO medico;

    private EspecialidadDTO especialidad;

    private String numConsultorio;

    private LocalDateTime fecha;

    private List<DetalleConsultaDTO> detalleConsulta;

}
