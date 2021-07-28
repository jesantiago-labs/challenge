package br.com.wine.challenge.service;

import br.com.wine.challenge.dto.faixa.atendimento.request.FaixaAtendimentoPatchRequestDTO;
import br.com.wine.challenge.dto.faixa.atendimento.request.FaixaAtendimentoPostRequestDTO;
import br.com.wine.challenge.dto.faixa.atendimento.response.FaixaAtendimentoResponseDTO;

public interface FaixaAtendimentoService {

    FaixaAtendimentoResponseDTO deleteFaixaAtendimento(Long id);
    FaixaAtendimentoResponseDTO updateFaixaAtendimento(Long id, FaixaAtendimentoPatchRequestDTO faixaAtendimentoPatchRequestDTO);
    FaixaAtendimentoResponseDTO getFaixaAtendimentoPorCep(Long cep);
    FaixaAtendimentoResponseDTO setFaixaAtendimento(FaixaAtendimentoPostRequestDTO faixaAtendimentoPostRequestDTO);
}
