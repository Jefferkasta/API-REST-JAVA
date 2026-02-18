package com.api.hateoas.dto;



public class CuentaDto {

    private String numeroDeCuenta;
    private float monto;

    public CuentaDto() {}

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }




}
