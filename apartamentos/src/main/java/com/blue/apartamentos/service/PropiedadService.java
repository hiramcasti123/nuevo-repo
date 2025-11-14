package com.blue.apartamentos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.repository.PropiedadRepository;

@Service
public class PropiedadService {
    
    @Autowired
    private PropiedadRepository propiedadRepository;
    
    public List<PropiedadModel> getAllPropiedades() {
        return propiedadRepository.findAll();
    }
    
    public Optional<PropiedadModel> getPropiedadById(Long id) {
        return propiedadRepository.findById(id);
    }
    
    public PropiedadModel savePropiedad(PropiedadModel propiedad) {
        return propiedadRepository.save(propiedad);
    }
    
    public void deletePropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }
    
    public List<PropiedadModel> getPropiedadesByTipo(String tipo) {
        return propiedadRepository.findByTipo(tipo);
    }
    
    public List<PropiedadModel> getPropiedadesByPropietario(Long propietarioId) {
        return propiedadRepository.findByPropietarioId(propietarioId);
    }
}
