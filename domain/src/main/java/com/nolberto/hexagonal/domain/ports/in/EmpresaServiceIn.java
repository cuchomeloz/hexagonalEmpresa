package com.nolberto.hexagonal.domain.ports.in;

import com.nolberto.hexagonal.domain.aggregates.dto.EmpresaDTO;
import com.nolberto.hexagonal.domain.aggregates.request.RequestEmpresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDTO crearEmpresaIn(RequestEmpresa requestEmpresa);
    Optional<EmpresaDTO> obtenerEmpresaIn(String numDoc);
    List<EmpresaDTO> obtenerTodoIn();
    EmpresaDTO actualizarIn(Long id, RequestEmpresa requestEmpresa);
    EmpresaDTO deleteIn(Long id);
}
