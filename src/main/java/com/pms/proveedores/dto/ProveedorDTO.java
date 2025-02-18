package com.pms.proveedores.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProveedorDTO{

    private Integer id;
    private String nombre;
    private String descripcion;
    private String regimen;

    public ProveedorDTO(String nombreProveedor, String descripcion, String regimen) {
        this.nombre = nombreProveedor;
        this.descripcion = descripcion;
        this.regimen = regimen;
    }
}