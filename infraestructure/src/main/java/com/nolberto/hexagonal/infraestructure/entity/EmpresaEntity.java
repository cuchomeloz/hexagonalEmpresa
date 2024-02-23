package com.nolberto.hexagonal.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@NamedQuery(name = "EmpresaEntity.findByNumDocu", query = "SELECT a from EmpresaEntity a where a.numDocu=:numDocu")
@NamedQuery(name = "EmpresaEntity.findByEstado", query = "SELECT a from EmpresaEntity a where a.estado=:estado")
@Entity
@Table(name = "empresa")
@Getter
@Setter
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(name = "num_docu", nullable = false, length = 15)
    private String numDocu;

    @Column(name = "razon_social", nullable = false, length = 150)
    private String razonSocial;

    @Column(name = "nom_comercial", nullable = false, length = 150)
    private String nomComercial;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "usua_crea", length = 45)
    private String usuaCrea;

    @Column(name = "date_create")
    private Timestamp dateCreate;

    @Column(name = "usua_modif", length = 45)
    private String usuaModif;

    @Column(name = "date_modif")
    private Timestamp dateModif;

    @Column(name = "usua_delet", length = 45)
    private String usuaDelet;

    @Column(name = "date_delet")
    private Timestamp dateDelet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_empresa_id", nullable = false)
    private TipoEmpresaEntity tipoEmpresa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private TipoDocumentoEntity tipoDocumento;
}
