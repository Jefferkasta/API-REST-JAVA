package com.api.hateoas.service;

import com.api.hateoas.dto.CuentaDto;
import com.api.hateoas.dto.DepositarRequest;
import com.api.hateoas.exceptions.DataCuentaNullException;
import com.api.hateoas.model.Cuenta;
import com.api.hateoas.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta save(CuentaDto cuentaDto){
        Cuenta cuenta = new Cuenta(cuentaDto.getNumeroDeCuenta(), cuentaDto.getMonto());
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> listAll(){
        return cuentaRepository.findAll();
    }

    public Cuenta get(Integer id){
        return cuentaRepository.findById(id).get();
    }

    public void delete(Integer id){
        cuentaRepository.deleteById(id);
    }

    public void depositar (DepositarRequest depositarRequest){

        Optional<Cuenta> capturaCuenta = cuentaRepository
                .findByNumeroDeCuenta(depositarRequest.getNumCuenta());

        if (capturaCuenta.isEmpty()){
            throw new DataCuentaNullException("Cuenta no encontrada");
        }
        Cuenta cuenta = capturaCuenta.get();
        float mount = cuenta.getMonto();
        float mountUpdate = mount + depositarRequest.getMonto();
        cuenta.setMonto(mountUpdate);

        //BORRAR
        cuentaRepository.save(cuenta);
        System.out.println(capturaCuenta.get());
    }

}

