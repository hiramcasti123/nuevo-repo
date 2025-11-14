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
import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public List<ClienteModel> getAllClientes() {
        return clienteService.getAllClientes();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> getClienteById(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteService.getClienteById(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ClienteModel> createCliente(@RequestBody ClienteModel cliente) {
        try {
            ClienteModel savedCliente = clienteService.saveCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> updateCliente(@PathVariable Long id, @RequestBody ClienteModel clienteDetails) {
        Optional<ClienteModel> clienteOptional = clienteService.getClienteById(id);
        if (clienteOptional.isPresent()) {
            ClienteModel clienteToUpdate = clienteOptional.get();
            
            if (clienteDetails.getNombre() != null) {
                clienteToUpdate.setNombre(clienteDetails.getNombre());
            }
            if (clienteDetails.getApellido() != null) {
                clienteToUpdate.setApellido(clienteDetails.getApellido());
            }
            if (clienteDetails.getEmail() != null) {
                clienteToUpdate.setEmail(clienteDetails.getEmail());
            }
            if (clienteDetails.getTelefono() != null) {
                clienteToUpdate.setTelefono(clienteDetails.getTelefono());
            }
            if (clienteDetails.getDocumento() != null) {
                clienteToUpdate.setDocumento(clienteDetails.getDocumento());
            }
            
            ClienteModel updatedCliente = clienteService.saveCliente(clienteToUpdate);
            return ResponseEntity.ok(updatedCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteService.getClienteById(id);
        if (cliente.isPresent()) {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteModel> getClienteByEmail(@PathVariable String email) {
        Optional<ClienteModel> cliente = clienteService.getClienteByEmail(email);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nombre/{nombre}")
    public List<ClienteModel> getClientesByNombre(@PathVariable String nombre) {
        return clienteService.getClientesByNombre(nombre);
    }
}
