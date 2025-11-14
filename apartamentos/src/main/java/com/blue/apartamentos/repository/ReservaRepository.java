package com.blue.apartamentos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blue.apartamentos.models.ReservaModel;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    List<ReservaModel> findByClienteId(Long clienteId);
    List<ReservaModel> findByPropiedadId(Long propiedadId);
    List<ReservaModel> findByEstado(String estado);
}
