package br.com.wine.challenge.dto.faixa.atendimento.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FaixaAtendimentoResponseDTO {

    private Long id;

    private String codigoLoja;

    private Long faixaInicio;

    private Long faixaFim;

}
