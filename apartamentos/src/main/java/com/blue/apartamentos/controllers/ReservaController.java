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
import com.blue.apartamentos.models.ReservaModel;
import com.blue.apartamentos.service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;
    
    @GetMapping
    public List<ReservaModel> getAllReservas() {
        return reservaService.getAllReservas();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReservaModel> getReservaById(@PathVariable Long id) {
        Optional<ReservaModel> reserva = reservaService.getReservaById(id);
        return reserva.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ReservaModel> createReserva(@RequestBody ReservaModel reserva) {
        try {
            ReservaModel savedReserva = reservaService.saveReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ReservaModel> updateReserva(@PathVariable Long id, @RequestBody ReservaModel reservaDetails) {
        Optional<ReservaModel> reservaOptional = reservaService.getReservaById(id);
        if (reservaOptional.isPresent()) {
            ReservaModel reservaToUpdate = reservaOptional.get();
            
            if (reservaDetails.getClienteId() != null) {
                reservaToUpdate.setClienteId(reservaDetails.getClienteId());
            }
            if (reservaDetails.getPropiedadId() != null) {
                reservaToUpdate.setPropiedadId(reservaDetails.getPropiedadId());
            }
            if (reservaDetails.getFecha_inicio() != null) {
                reservaToUpdate.setFecha_inicio(reservaDetails.getFecha_inicio());
            }
            if (reservaDetails.getFecha_fin() != null) {
                reservaToUpdate.setFecha_fin(reservaDetails.getFecha_fin());
            }
            if (reservaDetails.getPrecio_total() != null) {
                reservaToUpdate.setPrecio_total(reservaDetails.getPrecio_total());
            }
            if (reservaDetails.getEstado() != null) {
                reservaToUpdate.setEstado(reservaDetails.getEstado());
            }
            
            ReservaModel updatedReserva = reservaService.saveReserva(reservaToUpdate);
            return ResponseEntity.ok(updatedReserva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        Optional<ReservaModel> reserva = reservaService.getReservaById(id);
        if (reserva.isPresent()) {
            reservaService.deleteReserva(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/cliente/{clienteId}")
    public List<ReservaModel> getReservasByCliente(@PathVariable Long clienteId) {
        return reservaService.getReservasByCliente(clienteId);
    }
    
    @GetMapping("/propiedad/{propiedadId}")
    public List<ReservaModel> getReservasByPropiedad(@PathVariable Long propiedadId) {
        return reservaService.getReservasByPropiedad(propiedadId);
    }
    
    @GetMapping("/estado/{estado}")
    public List<ReservaModel> getReservasByEstado(@PathVariable String estado) {
        return reservaService.getReservasByEstado(estado);
    }
}
