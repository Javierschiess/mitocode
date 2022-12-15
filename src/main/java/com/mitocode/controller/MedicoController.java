package com.mitocode.controller;

import com.mitocode.dto.MedicoDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Medico;
import com.mitocode.service.IMedicoService;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService medicoService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listar()throws Exception{
        List<MedicoDTO> lista = medicoService.listar().stream().map(p -> mapper.map(p, MedicoDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> listarPorId(@PathVariable("id") Integer id)throws Exception{
        Medico medico = medicoService.listarPorId(id);

        if(medico == null){
            throw new ModelNotFoundException("ID NO ENCONTRADO " +id);
        }

        MedicoDTO medicoDTO = mapper.map(medico, MedicoDTO.class);

        return new ResponseEntity<>(medicoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody MedicoDTO medicoDTO)throws Exception{
        Medico dto = mapper.map(medicoDTO, Medico.class);
        Medico medico = medicoService.registrar(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medico.getIdMedico()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<MedicoDTO> modificar (@RequestBody MedicoDTO medicoDTO)throws Exception{
        Medico medico = medicoService.listarPorId(medicoDTO.getIdMedico());

        if (medico == null){
            throw new ModelNotFoundException("ID NO ENCONTRADO " + medicoDTO.getIdMedico());
        }

        Medico medic = mapper.map(medicoDTO, Medico.class);
        Medico dto = medicoService.modificar(medic);
        MedicoDTO dtoResponse = mapper.map(dto, MedicoDTO.class);

        return new ResponseEntity<>(dtoResponse,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id)throws Exception{
        Medico medico = medicoService.listarPorId(id);

        if (medico == null) {

            throw new ModelNotFoundException("ID NO ENCONTRADO " + id);
        }

        medicoService.eliminar(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
