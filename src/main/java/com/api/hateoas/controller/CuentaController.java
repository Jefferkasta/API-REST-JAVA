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

    @PostMapping("/crearcuenta")
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody CuentaDto cuentaDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cuentaService.save(cuentaDto));
    }

    @GetMapping("/listarcuentas")
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        return ResponseEntity.ok(cuentaService.listarCuentas());
    }

    @GetMapping("/obtenercuenta/{id}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable Integer id) {
        return ResponseEntity.ok(cuentaService.getId(id));
    }

    @PutMapping("/editarcuenta/{id}")
    public ResponseEntity<Cuenta> editarCuenta(
            @PathVariable Integer id,
            @RequestBody CuentaDto cuentaDto) {
        return ResponseEntity.ok(cuentaService.editarCuenta(id, cuentaDto));
    }

    @PutMapping("/depositar/{id}")
    public ResponseEntity<Void> depositarMonto(@RequestBody DepositarRequest request) {
        cuentaService.depositarMonto(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Integer id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }


}
