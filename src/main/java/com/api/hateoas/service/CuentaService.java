package com.api.hateoas.service;

import com.api.hateoas.dto.CuentaDto;
import com.api.hateoas.model.Cuenta;
import com.api.hateoas.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> listAll(){
        return cuentaRepository.findAll();
    }

    public Cuenta get(Integer id){
        return cuentaRepository.findById(id).get();
    }

    public Cuenta save(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    public Cuenta save2(CuentaDto cuentaDto){
        Cuenta cuenta = new Cuenta(cuentaDto.getNumeroDeCuenta(), cuentaDto.getMonto());
        return cuentaRepository.save(cuenta);
    }

    public void delete(Integer id){
        cuentaRepository.deleteById(id);
    }

}

