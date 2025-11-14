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
import com.blue.apartamentos.models.PropietarioModel;
import com.blue.apartamentos.service.PropietarioService;

@RestController
@RequestMapping("/api/propietarios")
@CrossOrigin(origins = "*")
public class PropietarioController {
    
    @Autowired
    private PropietarioService propietarioService;
    
    @GetMapping
    public List<PropietarioModel> getAllPropietarios() {
        return propietarioService.getAllPropietarios();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PropietarioModel> getPropietarioById(@PathVariable Long id) {
        Optional<PropietarioModel> propietario = propietarioService.getPropietarioById(id);
        return propietario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<PropietarioModel> createPropietario(@RequestBody PropietarioModel propietario) {
        try {
            PropietarioModel savedPropietario = propietarioService.savePropietario(propietario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPropietario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PropietarioModel> updatePropietario(@PathVariable Long id, @RequestBody PropietarioModel propietarioDetails) {
        Optional<PropietarioModel> propietarioOptional = propietarioService.getPropietarioById(id);
        if (propietarioOptional.isPresent()) {
            PropietarioModel propietarioToUpdate = propietarioOptional.get();
            
            if (propietarioDetails.getNombre() != null) {
                propietarioToUpdate.setNombre(propietarioDetails.getNombre());
            }
            if (propietarioDetails.getApellido() != null) {
                propietarioToUpdate.setApellido(propietarioDetails.getApellido());
            }
            if (propietarioDetails.getEmail() != null) {
                propietarioToUpdate.setEmail(propietarioDetails.getEmail());
            }
            if (propietarioDetails.getTelefono() != null) {
                propietarioToUpdate.setTelefono(propietarioDetails.getTelefono());
            }
            if (propietarioDetails.getCuenta_bancaria() != null) {
                propietarioToUpdate.setCuenta_bancaria(propietarioDetails.getCuenta_bancaria());
            }
            
            PropietarioModel updatedPropietario = propietarioService.savePropietario(propietarioToUpdate);
            return ResponseEntity.ok(updatedPropietario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropietario(@PathVariable Long id) {
        Optional<PropietarioModel> propietario = propietarioService.getPropietarioById(id);
        if (propietario.isPresent()) {
            propietarioService.deletePropietario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<PropietarioModel> getPropietarioByEmail(@PathVariable String email) {
        Optional<PropietarioModel> propietario = propietarioService.getPropietarioByEmail(email);
        return propietario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nombre/{nombre}")
    public List<PropietarioModel> getPropietariosByNombre(@PathVariable String nombre) {
        return propietarioService.getPropietariosByNombre(nombre);
    }
}
