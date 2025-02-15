package com.pms.proveedores.dto;

import lombok.Builder;

@Builder
public record ProveedorDTO(
        Long id,
        String nombre,
        String descripcion,
        String regimen
) {}