package com.celularinventario.inventario.dto;

import lombok.Data;

@Data
public class PhoneRequestDTO {
    
    private Integer id;
    private String marca;
    private String modelo;
    private String anio;
    private Integer stock;
    private Double precio;
    private Boolean disponibilidad;

}
