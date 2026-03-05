package com.celularinventario.inventario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="celulares")
public class Phone {
    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="marca")
    private String marca;

    @Column(name="modelo")
    private String modelo;

    @Column(name="anio")
    private String anio;

    @Column(name="stock")
    private Integer stock;

    @Column(name="precio")
    private Double precio;

    @Column(name="disponibilidad")
    private Boolean disponibilidad;
}
