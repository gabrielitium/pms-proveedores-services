package com.pms.proveedores.service.impl;

import com.pms.proveedores.dto.ProveedorDTO;
import com.pms.proveedores.model.Proveedor;
import com.pms.proveedores.repository.ProveedorRepository;
import com.pms.proveedores.service.ProveedorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> getProveedorById(Integer id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor createProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = new Proveedor(
                proveedorDTO.getNombre(),
                proveedorDTO.getDescripcion(),
                proveedorDTO.getRegimen()
        );
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id, ProveedorDTO proveedorDTO) {
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    proveedor.setNombre(proveedorDTO.getNombre());
                    proveedor.setDescripcion(proveedorDTO.getDescripcion());
                    proveedor.setRegimen(proveedorDTO.getRegimen());
                    proveedor.setFechaActualizacion(LocalDate.now());
                    return proveedorRepository.save(proveedor);
                }).orElseThrow(() -> new RuntimeException("Proveedor not found"));
    }

    @Override
    public void deleteProveedor(Integer id) {
        proveedorRepository.deleteById(id);
    }
}