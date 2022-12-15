package com.mitocode.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteDTO {

    private Integer idPaciente;

    private String nombres;

    private String apellidos;

    private String dni;

    private String direccion;

    private String telefono;

    private String email;


}
