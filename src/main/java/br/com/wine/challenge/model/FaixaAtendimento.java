package br.com.wine.challenge.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(schema = "PGFGE", name = "FAIXA_ATENDIMENTO")
public class FaixaAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAIXA_ATENDIMENTO")
    @SequenceGenerator(schema = "PGFGE", name = "SQ_FAIXA_ATENDIMENTO", sequenceName = "SQ_FAIXA_ATENDIMENTO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO_LOJA")
    private String codigoLoja;

    @Column(name = "FAIXA_INICIO")
    private Long faixaInicio;

    @Column(name = "FAIXA_FIM")
    private Long faixaFim;

}
