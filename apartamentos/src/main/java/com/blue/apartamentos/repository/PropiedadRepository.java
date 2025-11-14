package com.blue.apartamentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.PropiedadModel;

@Repository
public interface PropiedadRepository extends JpaRepository<PropiedadModel, Long> {
    List<PropiedadModel> findByTipo(String tipo);
    List<PropiedadModel> findByPropietarioId(Long propietarioId);
}
