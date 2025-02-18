package com.pms.proveedores.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import com.pms.proveedores.dto.ProveedorDTO;
import com.pms.proveedores.model.Proveedor;
import com.pms.proveedores.repository.ProveedorRepository;
import com.pms.proveedores.service.impl.ProveedorServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl proveedorService;

    private Proveedor proveedor;
    private ProveedorDTO proveedorDTO;

    @BeforeEach
    void setUp() {
        proveedor = new Proveedor("Proveedor A", "", "Persona Fisica");
        proveedor.setId(1);
        proveedor.setFechaCreacion(LocalDate.now());
        proveedor.setFechaActualizacion(LocalDate.now());

        proveedorDTO = new ProveedorDTO(1, "Proveedor A", "", "Persona Fisica");
    }

    @Test
    void shouldReturnAllProveedores() {
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));

        List<Proveedor> proveedores = proveedorService.getAllProveedores();

        assertThat(proveedores).hasSize(1);
        assertThat(proveedores.get(0).getId()).isEqualTo(proveedor.getId());

        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnProveedorById() {
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));

        Optional<Proveedor> foundProveedor = proveedorService.getProveedorById(1);

        assertThat(foundProveedor).isPresent();
        assertThat(foundProveedor.get().getId()).isEqualTo(proveedor.getId());

        verify(proveedorRepository, times(1)).findById(1);
    }

    @Test
    void shouldReturnEmptyWhenProveedorNotFound() {
        when(proveedorRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Proveedor> foundProveedor = proveedorService.getProveedorById(99);

        assertThat(foundProveedor).isEmpty();

        verify(proveedorRepository, times(1)).findById(99);
    }

    @Test
    void shouldCreateProveedorSuccessfully() {
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedor);


        Proveedor savedProveedor = proveedorService.createProveedor(proveedorDTO);

        assertThat(savedProveedor).isNotNull();
        assertThat(savedProveedor.getNombre()).isEqualTo(proveedorDTO.getNombre());
        assertThat(savedProveedor.getRegimen()).isEqualTo(proveedorDTO.getRegimen());

        verify(proveedorRepository, times(1)).save(any(Proveedor.class));
    }

    @Test
    void shouldUpdateProveedorSuccessfully() {
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedor);

        Proveedor updatedProveedor = proveedorService.updateProveedor(1, proveedorDTO);

        assertThat(updatedProveedor).isNotNull();
        assertThat(updatedProveedor.getNombre()).isEqualTo(proveedorDTO.getNombre());
        assertThat(updatedProveedor.getDescripcion()).isEqualTo(proveedorDTO.getDescripcion());
        assertThat(updatedProveedor.getRegimen()).isEqualTo(proveedorDTO.getRegimen());

        verify(proveedorRepository, times(1)).findById(1);
        verify(proveedorRepository, times(1)).save(any(Proveedor.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonexistentProveedor() {
        when(proveedorRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> proveedorService.updateProveedor(99, proveedorDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Proveedor not found");

        verify(proveedorRepository, times(1)).findById(99);
        verify(proveedorRepository, never()).save(any(Proveedor.class));
    }

    @Test
    void shouldDeleteProveedorSuccessfully() {
        doNothing().when(proveedorRepository).deleteById(1);

        proveedorService.deleteProveedor(1);

        verify(proveedorRepository, times(1)).deleteById(1);
    }
}
