package com.nolberto.hexagonal.domain.ports.out;

import com.nolberto.hexagonal.domain.aggregates.dto.EmpresaDTO;
import com.nolberto.hexagonal.domain.aggregates.request.RequestEmpresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {
    EmpresaDTO crearEmpresaOut(RequestEmpresa requestEmpresa);
    Optional<EmpresaDTO> obtenerEmpresaOut(String numDoc);
    List<EmpresaDTO> obtenerTodoOut();
    EmpresaDTO actualizarOut(Long id, RequestEmpresa requestEmpresa);
    EmpresaDTO deleteOut(Long id);
}
