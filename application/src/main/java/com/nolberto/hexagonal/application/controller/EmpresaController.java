package com.nolberto.hexagonal.application.controller;

import com.nolberto.hexagonal.domain.aggregates.dto.EmpresaDTO;
import com.nolberto.hexagonal.domain.aggregates.request.RequestEmpresa;
import com.nolberto.hexagonal.domain.ports.in.EmpresaServiceIn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@OpenAPIDefinition(
        info = @Info(
                title = "API-EMPRESA",
                version = "2.0",
                description = "Mantenimiento de una Empresa"
        )
)
@RestController
@RequestMapping("/v2/empresa")
public class EmpresaController {
    @Autowired
    @Qualifier("empresaServiceImpl")
    private EmpresaServiceIn empresaServiceIn;
    @Operation(summary = "Crea una empresa")
    @PostMapping
    public ResponseEntity<EmpresaDTO> registrar(@RequestBody RequestEmpresa requestEmpresa){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearEmpresaIn(requestEmpresa));
    }
    @Operation(summary = "Obtiene datos de una empresa")
    @GetMapping("{numDoc}")
    public ResponseEntity<EmpresaDTO> obtenerEmpresa(@PathVariable String numDoc){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.obtenerEmpresaIn(numDoc).get());
    }
    @Operation(summary = "Obtiene datos de todas las empresas")
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> obtenerTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.obtenerTodoIn());
    }

    @Operation(summary = "Actualiza los datos de una empresa")
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> actualizar (@PathVariable Long id, @RequestBody RequestEmpresa requestEmpresa){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.actualizarIn(id, requestEmpresa));
    }

    @Operation(summary="Borrar datos de una Empresa, Eliminado Logico Status =0")
    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaDTO> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.deleteIn(id));
    }


}
