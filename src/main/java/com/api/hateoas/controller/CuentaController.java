package com.api.hateoas.controller;

import com.api.hateoas.dto.CuentaDto;
import com.api.hateoas.model.Cuenta;
import com.api.hateoas.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/listar-cuentas")
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        List<Cuenta> cuentas = cuentaService.listAll();

        if (cuentas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }


    @GetMapping("/listar-id")
    public ResponseEntity<Cuenta> listarCuenta(@RequestParam(value = "id") Integer id){
        try{
            Cuenta cuenta = cuentaService.get(id);
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        }catch (Exception exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar-sin-dto")
    public ResponseEntity<Cuenta> guardarCuenta(@RequestBody Cuenta cuenta){
        Cuenta cuentaBBDD = cuentaService.save(cuenta);
        return new ResponseEntity<>(cuenta, HttpStatus.CREATED);
    }

    //VERSION 2
    @PostMapping("/guardar-con-dto")
    public ResponseEntity guardarCuenta2(@RequestBody CuentaDto cuentaDto){
        Cuenta cuentaBBDD = cuentaService.save2(cuentaDto);
        return new ResponseEntity<>(cuentaBBDD, HttpStatus.CREATED);
    }

    @PutMapping("/editar-cuenta")
    public ResponseEntity<Cuenta> editarCuenta(@RequestBody Cuenta cuenta){
        Cuenta cuentaBBDD = cuentaService.save(cuenta);
        return ResponseEntity.status(HttpStatus.OK).body(cuentaBBDD);
    }

    @DeleteMapping("/eliminar-cuenta")
    public ResponseEntity<?> eliminarCuenta(@RequestParam(value = "id") Integer id){
        try{
            cuentaService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception exception){
            return ResponseEntity.notFound().build();
        }
    }


}
