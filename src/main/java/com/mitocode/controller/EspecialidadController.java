package com.mitocode.controller;

import com.mitocode.dto.EspecialidadDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Especialidad;
import com.mitocode.service.IEspecialidadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private IEspecialidadService especialidadService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EspecialidadDTO>> listar ()throws Exception{
        List<EspecialidadDTO> lista = especialidadService.listar().stream().map(e -> mapper.map(e, EspecialidadDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> listarPorId(@PathVariable("id") Integer id)throws Exception{
        Especialidad especialidad = especialidadService.listarPorId(id);

        if (especialidad == null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }

        EspecialidadDTO especialidadDTO = mapper.map(especialidad, EspecialidadDTO.class);

        return new ResponseEntity<>(especialidadDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EspecialidadDTO> registrar (@RequestBody EspecialidadDTO especialidadDTO)throws Exception{
        Especialidad especialidad = mapper.map(especialidadDTO, Especialidad.class);
        Especialidad espe = especialidadService.registrar(especialidad);
        return new ResponseEntity<>(especialidadDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EspecialidadDTO> moficiar (@RequestBody EspecialidadDTO especialidadDTO)throws Exception{
        Especialidad especialidad = especialidadService.listarPorId(especialidadDTO.getIdEspecialidad());

        if (especialidad == null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+especialidadDTO.getIdEspecialidad());
        }

        Especialidad espe = mapper.map(especialidadDTO, Especialidad.class);
        Especialidad e = especialidadService.modificar(espe);

        return new ResponseEntity<>(especialidadDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar (@PathVariable("id") Integer id)throws Exception{
        Especialidad especialidad = especialidadService.listarPorId(id);

        if(especialidad == null){
            throw new ModelNotFoundException("ID NO ENCONTRADO " + id);
        }

        especialidadService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
