package com.mitocode.serviceImpl;

import com.mitocode.model.Consulta;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta, Integer> implements IConsultaService {

    @Autowired
    private IConsultaRepo repo;

    @Override
    protected IGenericRepo<Consulta, Integer> getRepo() {
        return repo;
    }
}
