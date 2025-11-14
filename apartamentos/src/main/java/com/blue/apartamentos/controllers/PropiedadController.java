package com.blue.apartamentos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.service.PropiedadService;

@RestController
@RequestMapping("/api/propiedades")
@CrossOrigin(origins = "*")
public class PropiedadController {
    
    @Autowired
    private PropiedadService propiedadService;
    
    @GetMapping
    public List<PropiedadModel> getAllPropiedades() {
        return propiedadService.getAllPropiedades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropiedadModel> getPropiedadesById(@PathVariable Long id) {
        Optional<PropiedadModel> propiedad = propiedadService.getPropiedadById(id);
        return propiedad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PropiedadModel> createPropiedad(@RequestBody PropiedadModel propiedad) {
        try {
            PropiedadModel savedPropiedad = propiedadService.savePropiedad(propiedad);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPropiedad);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropiedadModel> updatePropiedad(@PathVariable Long id, @RequestBody PropiedadModel propiedadDetails) {
        Optional<PropiedadModel> propiedadOptional = propiedadService.getPropiedadById(id);
        if (propiedadOptional.isPresent()) {
            PropiedadModel propiedadToUpdate = propiedadOptional.get();
            
            if (propiedadDetails.getDireccion() != null) {
                propiedadToUpdate.setDireccion(propiedadDetails.getDireccion());
            }
            if (propiedadDetails.getTipo() != null) {
                propiedadToUpdate.setTipo(propiedadDetails.getTipo());
            }
            if (propiedadDetails.getPrecio_noche() != null) {
                propiedadToUpdate.setPrecio_noche(propiedadDetails.getPrecio_noche());
            }
            if (propiedadDetails.getPropietarioId() != null) {
                propiedadToUpdate.setPropietarioId(propiedadDetails.getPropietarioId());
            }

            PropiedadModel updatedPropiedad = propiedadService.savePropiedad(propiedadToUpdate);
            return ResponseEntity.ok(updatedPropiedad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropiedad(@PathVariable Long id) {
        Optional<PropiedadModel> propiedad = propiedadService.getPropiedadById(id);
        if (propiedad.isPresent()) {
            propiedadService.deletePropiedad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/tipo/{tipo}")
    public List<PropiedadModel> getPropiedadesByTipo(@PathVariable String tipo) {
        return propiedadService.getPropiedadesByTipo(tipo);
    }

    @GetMapping("/propietario/{propietarioId}")
    public List<PropiedadModel> getPropiedadesByPropietario(@PathVariable Long propietarioId) {
        return propiedadService.getPropiedadesByPropietario(propietarioId);
    }
}
