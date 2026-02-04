package com.api.hateoas.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
@Table(name = "cuentas")
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta extends RepresentationModel<Cuenta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20,nullable = false, unique = true)
    private String numeroDeCuenta;

    private float monto;

    public void setMonto(Float monto) { this.monto = monto; }


    public Cuenta(String numeroDeCuenta, float monto) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.monto = monto;}
}











