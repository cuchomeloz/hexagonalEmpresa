package com.nolberto.hexagonal.domain.impl;

import com.nolberto.hexagonal.domain.aggregates.dto.EmpresaDTO;
import com.nolberto.hexagonal.domain.aggregates.request.RequestEmpresa;
import com.nolberto.hexagonal.domain.ports.in.EmpresaServiceIn;
import com.nolberto.hexagonal.domain.ports.out.EmpresaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {
    @Autowired
    private EmpresaServiceOut empresaServiceOut;


    @Override
    public EmpresaDTO crearEmpresaIn(RequestEmpresa requestEmpresa) {
        return empresaServiceOut.crearEmpresaOut(requestEmpresa);
    }

    @Override
    public Optional<EmpresaDTO> obtenerEmpresaIn(String numDoc) {
        return empresaServiceOut.obtenerEmpresaOut(numDoc);
    }

    @Override
    public List<EmpresaDTO> obtenerTodoIn() {
        return empresaServiceOut.obtenerTodoOut();
    }

    @Override
    public EmpresaDTO actualizarIn(Long id, RequestEmpresa requestEmpresa) {
        return empresaServiceOut.actualizarOut(id, requestEmpresa);
    }

    @Override
    public EmpresaDTO deleteIn(Long id) {
        return empresaServiceOut.deleteOut(id);
    }
}
