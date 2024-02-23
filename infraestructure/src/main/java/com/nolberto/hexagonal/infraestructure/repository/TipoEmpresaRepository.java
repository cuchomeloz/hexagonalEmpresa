package com.nolberto.hexagonal.infraestructure.repository;

import com.nolberto.hexagonal.infraestructure.entity.TipoEmpresaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEmpresaRepository extends JpaRepository<TipoEmpresaEntity, Long> {

    TipoEmpresaEntity findByCodTipo(@Param("codEmp") String codTipo);
}
