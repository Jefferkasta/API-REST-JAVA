package com.api.hateoas.service;

import com.api.hateoas.dto.CuentaDto;
import com.api.hateoas.dto.DepositarRequest;
import com.api.hateoas.model.Cuenta;
import com.api.hateoas.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Transactional
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public Cuenta save(CuentaDto cuentaDto){
        Cuenta cuenta = new Cuenta(
                cuentaDto.getNumeroDeCuenta(),
                cuentaDto.getMonto());
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> listarCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        if (cuentas.isEmpty()) {
            throw new RuntimeException("No hay cuentas registradas");
        }
        return cuentas;
    }

    public Cuenta getId(Integer id){
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public Cuenta editarCuenta(Integer id, CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede editar: Cuenta no encontrada"));
        cuenta.setMonto(cuentaDto.getMonto());
        return cuentaRepository.save(cuenta);
    }

    public void depositarMonto(DepositarRequest request) {
        Cuenta cuenta = getId(request.getIdCuenta());
        cuenta.setMonto(cuenta.getMonto() + request.getMonto());
        cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Integer id) {
        Cuenta cuenta = getId(id);
        cuentaRepository.delete(cuenta);
    }

}

