package com.blue.apartamentos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<ClienteModel> getAllClientes() {
        return clienteRepository.findAll();
    }
    
    public Optional<ClienteModel> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }
    
    public ClienteModel saveCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }
    
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
    
    public Optional<ClienteModel> getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
    
    public List<ClienteModel> getClientesByNombre(String nombre) {
        return clienteRepository.findByNombreContaining(nombre);
    }
}
