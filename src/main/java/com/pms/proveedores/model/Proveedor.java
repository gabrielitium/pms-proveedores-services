package com.pms.proveedores.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "regimen", nullable = false, length = 10)
    private String regimen;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDate fechaCreacion = LocalDate.now();

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion = LocalDate.now();

    public Proveedor(String nombre, String descripcion, String regimen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.regimen = regimen;
    }

}