package com.celularinventario.inventario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneResponseDTO {
    
    private Integer id;
    private String marca;
    private String modelo;
    private String anio;
    private Integer stock;
    private Double precio;
    private Boolean disponibilidad;

    private String message;

}

