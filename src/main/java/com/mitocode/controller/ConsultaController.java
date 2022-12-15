package com.mitocode.controller;

import com.mitocode.dto.ConsultaDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listar ()throws Exception{
        List<ConsultaDTO> list = consultaService.listar().stream().map(c -> mapper.map(c, ConsultaDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> listarPorId (@PathVariable("id") Integer id)throws Exception{
        Consulta consulta = consultaService.listarPorId(id);

        if (consulta == null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }

        ConsultaDTO consultaDTO = mapper.map(consulta, ConsultaDTO.class);
        return new ResponseEntity<>(consultaDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> registrar (@RequestBody ConsultaDTO consultaDTO)throws Exception{
        Consulta consulta = mapper.map(consultaDTO, Consulta.class);
        Consulta consult = consultaService.registrar(consulta);
        return new ResponseEntity<>(consultaDTO, HttpStatus.CREATED);

    }
}
