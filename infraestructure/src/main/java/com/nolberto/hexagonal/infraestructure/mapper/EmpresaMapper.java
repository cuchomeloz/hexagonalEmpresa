package com.nolberto.hexagonal.infraestructure.mapper;

import com.nolberto.hexagonal.domain.aggregates.dto.EmpresaDTO;
import com.nolberto.hexagonal.infraestructure.entity.EmpresaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public EmpresaDTO mapToDto(EmpresaEntity empresaEntity){
        return modelMapper.map(empresaEntity, EmpresaDTO.class);
    }

    public EmpresaEntity mapToEntity(EmpresaDTO empresaDTO){
        return modelMapper.map(empresaDTO, EmpresaEntity.class);
    }
}
