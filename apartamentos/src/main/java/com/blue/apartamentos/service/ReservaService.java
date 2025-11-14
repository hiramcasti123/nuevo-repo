package com.blue.apartamentos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.ReservaModel;
import com.blue.apartamentos.repository.ReservaRepository;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    public List<ReservaModel> getAllReservas() {
        return reservaRepository.findAll();
    }
    
    public Optional<ReservaModel> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }
    
    public ReservaModel saveReserva(ReservaModel reserva) {
        return reservaRepository.save(reserva);
    }
    
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }
    
    public List<ReservaModel> getReservasByCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }
    
    public List<ReservaModel> getReservasByPropiedad(Long propiedadId) {
        return reservaRepository.findByPropiedadId(propiedadId);
    }
    
    public List<ReservaModel> getReservasByEstado(String estado) {
        return reservaRepository.findByEstado(estado);
    }
}
