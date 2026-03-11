package com.celularinventario.inventario.dto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PhoneRequestDTO {
    

    @Size(max = 50, message = "La marca no puede tener más de 50 caracteres")
    private String marca;

    @Size(max = 50, message = "El modelo no puede tener más de 50 caracteres")
    private String modelo;

    @Size(max = 4, message = "El año no puede tener más de 4 caracteres")
    private String anio;

    @Positive(message = "El stock debe ser un número positivo")
    private Integer stock;

    @Positive(message = "El precio debe ser un número positivo")
    private Double precio;

    
    private Boolean disponibilidad;

}
