package com.blue.apartamentos.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blue.apartamentos.models.PropietarioModel;

@Repository
public interface PropietarioRepository extends JpaRepository<PropietarioModel, Long> {
    Optional<PropietarioModel> findByEmail(String email);
    List<PropietarioModel> findByNombreContaining(String nombre);
}
