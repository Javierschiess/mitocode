package com.mitocode.controller;

import com.mitocode.dto.ExamenDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Examen;
import com.mitocode.service.IExamenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/examenes")
public class ExamenController {

    @Autowired
    private IExamenService examenService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ExamenDTO>> listar()throws Exception{
        List<ExamenDTO> lista = examenService.listar().stream().map(e -> mapper.map(e, ExamenDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamenDTO> listarPorId(@PathVariable("id") Integer id)throws Exception{
        Examen examen = examenService.listarPorId(id);

        if (examen == null){
            throw new ModelNotFoundException("ID NO ENCONTRADO " + id);
        }

        ExamenDTO examenDTO = mapper.map(examen, ExamenDTO.class);
        return new ResponseEntity<>(examenDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExamenDTO> registrar (@RequestBody ExamenDTO examenDTO)throws Exception{
        Examen examen = mapper.map(examenDTO, Examen.class);
        Examen ex = examenService.registrar(examen);
        return new ResponseEntity<>(examenDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ExamenDTO> modificar (@RequestBody ExamenDTO examenDTO)throws Exception{
        Examen examen = examenService.listarPorId(examenDTO.getIdExamen());

        if (examen == null){
            throw new ModelNotFoundException("ID NO ENCONTRADO " + examenDTO.getIdExamen());
        }
        Examen ex = mapper.map(examenDTO, Examen.class);
        Examen exame = examenService.modificar(ex);

        return new ResponseEntity<>(examenDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar (@PathVariable("id") Integer id)throws Exception{
        Examen examen = examenService.listarPorId(id);

        if (examen == null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }

        examenService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
