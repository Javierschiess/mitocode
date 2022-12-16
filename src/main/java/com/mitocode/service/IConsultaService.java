package com.mitocode.service;

import com.mitocode.model.Consulta;
import com.mitocode.model.Examen;

import java.util.List;

public interface IConsultaService extends ICRUD<Consulta, Integer> {

    Consulta registrarTransaccional(Consulta consulta, List<Examen> examenes)throws Exception;
}
