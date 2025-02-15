package com.pms.proveedores.service;

import com.pms.proveedores.dto.ProveedorDTO;
import com.pms.proveedores.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    List<Proveedor> getAllProveedores();
    Optional<Proveedor> getProveedorById(Integer id);
    Proveedor createProveedor(ProveedorDTO proveedorDTO);
    Proveedor updateProveedor(Integer id, ProveedorDTO proveedorDTO);
    void deleteProveedor(Integer id);
}
