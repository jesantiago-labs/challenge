package br.com.wine.challenge.dto.faixa.atendimento;

import br.com.wine.challenge.dto.faixa.atendimento.response.FaixaAtendimentoResponseDTO;
import br.com.wine.challenge.model.FaixaAtendimento;

public class FaixaAtendimentoMapper {

    public static FaixaAtendimentoResponseDTO toFaixaAtendimentoResponseDTO(FaixaAtendimento faixaAtendimento){

        return new FaixaAtendimentoResponseDTO()
                .setId(faixaAtendimento.getId())
                .setCodigoLoja(faixaAtendimento.getCodigoLoja())
                .setFaixaInicio(faixaAtendimento.getFaixaInicio())
                .setFaixaFim(faixaAtendimento.getFaixaFim());
    }

}
