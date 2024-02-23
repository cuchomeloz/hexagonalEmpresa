package com.nolberto.hexagonal.domain.aggregates.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSunat {
    private String razonSocial;
    private String tipoDocumento;
    private String numeroDocumento;
    private String estado;
}
