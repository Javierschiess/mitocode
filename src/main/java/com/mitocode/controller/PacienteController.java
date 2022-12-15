package com.mitocode.controller;


import com.mitocode.dto.PacienteDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar() throws Exception {
        List<PacienteDTO>  lista = service.listar().stream().map(p -> mapper.map(p, PacienteDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PacienteDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Paciente paciente = service.listarPorId(id);

        if (paciente == null){

            throw new ModelNotFoundException(" ID NO ENCONTRADO " + id);
        }

        PacienteDTO dto =mapper.map(paciente, PacienteDTO.class);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registrar (@RequestBody PacienteDTO dto) throws Exception {
        Paciente p = mapper.map(dto, Paciente.class);

        Paciente paciente = service.registrar(p);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paciente.getIdPaciente()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<PacienteDTO> modificar (@RequestBody PacienteDTO dto) throws Exception {
        Paciente paciente = service.listarPorId(dto.getIdPaciente());

        if (paciente == null){

            throw new ModelNotFoundException(" ID NO ENCONTRADO " + dto.getIdPaciente());
        }

        Paciente p = mapper.map(dto, Paciente.class );
        Paciente pac = service.modificar(p);
        PacienteDTO dtoResponse = mapper.map(pac, PacienteDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
       Paciente paciente = service.listarPorId(id);
        if (paciente == null){
            throw new ModelNotFoundException(" ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
