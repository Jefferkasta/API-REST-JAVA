package com.api.hateoas.dto;

import lombok.Data;

@Data
public class DepositarRequest {
    private String numCuenta;
    private int monto;

    public Integer getIdCuenta() {
        return 0;
    }
}
