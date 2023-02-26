package com.nttd.dto;

import lombok.Data;

@Data
public class OperacionDto {
    private String idOperacion;
    private String codigoBilletera;
    private String celular;
    private Double monto;
    private String descripcion;
    private String codigoPromocion;
    private String fechaOperacion;
}
