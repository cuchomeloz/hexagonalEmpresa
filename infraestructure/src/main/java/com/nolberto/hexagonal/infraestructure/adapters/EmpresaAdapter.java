package com.nolberto.hexagonal.infraestructure.adapters;

import com.nolberto.hexagonal.domain.aggregates.constants.Constants;
import com.nolberto.hexagonal.domain.aggregates.dto.EmpresaDTO;
import com.nolberto.hexagonal.domain.aggregates.request.RequestEmpresa;
import com.nolberto.hexagonal.domain.aggregates.response.ResponseSunat;
import com.nolberto.hexagonal.domain.ports.out.EmpresaServiceOut;
import com.nolberto.hexagonal.infraestructure.entity.EmpresaEntity;
import com.nolberto.hexagonal.infraestructure.entity.TipoDocumentoEntity;
import com.nolberto.hexagonal.infraestructure.entity.TipoEmpresaEntity;
import com.nolberto.hexagonal.infraestructure.mapper.EmpresaMapper;
import com.nolberto.hexagonal.infraestructure.repository.EmpresaRepository;
import com.nolberto.hexagonal.infraestructure.repository.TipoDocumentoRepository;
import com.nolberto.hexagonal.infraestructure.repository.TipoEmpresaRepository;
import com.nolberto.hexagonal.infraestructure.res.client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaAdapter implements EmpresaServiceOut {

    private final EmpresaRepository empresaRepository;
    private final TipoEmpresaRepository tipoEmpresaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final ClienteSunat sunat;
    private final EmpresaMapper empresaMapper;

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public EmpresaDTO crearEmpresaOut(RequestEmpresa requestEmpresa) {
        ResponseSunat responseSunat = getInfo(requestEmpresa.getNumDoc());
        empresaRepository.save(getEntity(responseSunat, requestEmpresa));
        return empresaMapper.mapToDto(getEntity(responseSunat, requestEmpresa));
    }

    @Override
    public Optional<EmpresaDTO> obtenerEmpresaOut(String numDoc) {
        return Optional.of(empresaMapper.mapToDto(empresaRepository.findByNumDocu(numDoc)));
    }

    @Override
    public List<EmpresaDTO> obtenerTodoOut() {
        List<EmpresaDTO> empresaDTOS = new ArrayList<>();
        List<EmpresaEntity> entityList = empresaRepository.findByEstado(Constants.STATUS_ACTIVE);
        for (EmpresaEntity entity : entityList){
            EmpresaDTO empresaDTO = empresaMapper.mapToDto(entity);
            empresaDTOS.add(empresaDTO);
        }
        return empresaDTOS;
    }

    @Override
    public EmpresaDTO actualizarOut(Long id, RequestEmpresa requestEmpresa) {
        boolean existe = empresaRepository.existsById(id);
        if (existe){
            Optional<EmpresaEntity> empresa = empresaRepository.findById(id);
            ResponseSunat responseSunat = getInfo(requestEmpresa.getNumDoc());
            empresaRepository.save(getEntityUpdate(responseSunat, empresa.get()));
            return empresaMapper.mapToDto(getEntityUpdate(responseSunat, empresa.get()));
        }
        return null;
    }

    @Override
    public EmpresaDTO deleteOut(Long id) {
        boolean existe = empresaRepository.existsById(id);
        if(existe){
            Optional<EmpresaEntity> empresa = empresaRepository.findById(id);
            empresa.get().setEstado(Constants.STATUS_INACTIVE);
            empresa.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            empresa.get().setDateDelet(getTimestamp());
            empresaRepository.save(empresa.get());
            return empresaMapper.mapToDto(empresa.get());
        }
        return null;
    }

    private ResponseSunat getInfo(String numero){
        String autho = "Bearer "+tokenApi;
        return sunat.getInfoSunat(numero, autho);
    }

    private EmpresaEntity getEntity(ResponseSunat sunat, RequestEmpresa requestEmpresa){
        TipoDocumentoEntity tipoDocumentoEntity = tipoDocumentoRepository.findByCodTipo(requestEmpresa.getTipoDoc());
        TipoEmpresaEntity tipoEmpresaEntity = tipoEmpresaRepository.findByCodTipo(requestEmpresa.getTipoEmp());

        EmpresaEntity entity = new EmpresaEntity();
        entity.setNumDocu(sunat.getNumeroDocumento());
        entity.setNomComercial(sunat.getRazonSocial());
        entity.setRazonSocial(sunat.getRazonSocial());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoEmpresa(tipoEmpresaEntity);
        entity.setTipoDocumento(tipoDocumentoEntity);
        return entity;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

    private EmpresaEntity getEntityUpdate(ResponseSunat sunat, EmpresaEntity empresaEntity){
        empresaEntity.setNomComercial(sunat.getRazonSocial());
        empresaEntity.setNumDocu(sunat.getNumeroDocumento());
        empresaEntity.setRazonSocial(sunat.getRazonSocial());
        empresaEntity.setUsuaModif(Constants.AUDIT_ADMIN);
        empresaEntity.setDateModif(getTimestamp());
        return empresaEntity;
    }
}
