package com.nolberto.hexagonal.infraestructure.repository;

import com.nolberto.hexagonal.infraestructure.entity.EmpresaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    EmpresaEntity findByNumDocu(@Param("numDocu") String numDocu);
    List<EmpresaEntity> findByEstado(@Param("estado") Integer estado);
}
