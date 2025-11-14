package com.blue.apartamentos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.PropietarioModel;
import com.blue.apartamentos.repository.PropietarioRepository;

@Service
public class PropietarioService {
    
    @Autowired
    private PropietarioRepository propietarioRepository;
    
    public List<PropietarioModel> getAllPropietarios() {
        return propietarioRepository.findAll();
    }
    
    public Optional<PropietarioModel> getPropietarioById(Long id) {
        return propietarioRepository.findById(id);
    }
    
    public PropietarioModel savePropietario(PropietarioModel propietario) {
        return propietarioRepository.save(propietario);
    }
    
    public void deletePropietario(Long id) {
        propietarioRepository.deleteById(id);
    }
    
    public Optional<PropietarioModel> getPropietarioByEmail(String email) {
        return propietarioRepository.findByEmail(email);
    }
    
    public List<PropietarioModel> getPropietariosByNombre(String nombre) {
        return propietarioRepository.findByNombreContaining(nombre);
    }
}
