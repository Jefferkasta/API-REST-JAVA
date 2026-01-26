package com.api.hateoas.controller;
import com.api.hateoas.dto.CuentaDto;
import com.api.hateoas.dto.DepositarRequest;
import com.api.hateoas.model.Cuenta;
import com.api.hateoas.service.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {this.cuentaService = cuentaService;}

    @PostMapping("/guardar-con-dto")
    public ResponseEntity guardarCuenta(@RequestBody CuentaDto cuentaDto){
        Cuenta cuentaBBDD = cuentaService.save(cuentaDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cuentaBBDD);
    }

    @GetMapping("/listar-cuentas")
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        List<Cuenta> cuentas = cuentaService.listAll();
        if (cuentas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cuentas);
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



    @PutMapping("/editar-cuenta")
    public ResponseEntity<Cuenta> editarCuenta(@RequestBody CuentaDto cuentaDto){
        Cuenta cuentaBBDD = cuentaService.save(cuentaDto);
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

    @PutMapping("/depositar")
    public ResponseEntity depositar(@RequestBody DepositarRequest depositarRequest){
        cuentaService.depositar(depositarRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Deposito exitoso");
    }


}
