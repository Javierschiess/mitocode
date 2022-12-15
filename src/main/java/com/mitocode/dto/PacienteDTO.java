package com.mitocode.dto;

import lombok.Data;

@Data
public class PacienteDTO {

    private Integer idPaciente;

    private String nombres;

    private String apellidos;

    private String dni;

    private String direccion;

    private String telefono;

    private String email;


}
