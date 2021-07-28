package br.com.wine.challenge.service;

import br.com.wine.challenge.dto.faixa.atendimento.FaixaAtendimentoMapper;
import br.com.wine.challenge.dto.faixa.atendimento.request.FaixaAtendimentoPatchRequestDTO;
import br.com.wine.challenge.dto.faixa.atendimento.request.FaixaAtendimentoPostRequestDTO;
import br.com.wine.challenge.dto.faixa.atendimento.response.FaixaAtendimentoResponseDTO;
import br.com.wine.challenge.exception.BusinessException;
import br.com.wine.challenge.exception.ResourceNotFoundException;
import br.com.wine.challenge.model.FaixaAtendimento;
import br.com.wine.challenge.exception.ConflictException;
import br.com.wine.challenge.repository.FaixaAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FaixaAtendimentoServiceImpl implements FaixaAtendimentoService{

    @Autowired
    private FaixaAtendimentoRepository faixaAtendimentoRepository;

    @Override
    public FaixaAtendimentoResponseDTO deleteFaixaAtendimento(Long id) {
        FaixaAtendimento faixaAtendimento = this.buscaFaixaPorId(id);

        faixaAtendimentoRepository.deleteById(id);

        return FaixaAtendimentoMapper.toFaixaAtendimentoResponseDTO(faixaAtendimento);
    }

    @Transactional
    @Override
    public FaixaAtendimentoResponseDTO updateFaixaAtendimento(Long id, FaixaAtendimentoPatchRequestDTO faixaAtendimentoPatchRequestDTO) {
        FaixaAtendimento faixaAtendimento = this.buscaFaixaPorId(id);

        this.validaSobreposicaoEntreFaixas(faixaAtendimentoPatchRequestDTO.getFaixaInicio(), faixaAtendimentoPatchRequestDTO.getFaixaFim());

        FaixaAtendimento updatedFaixaAtendimento = new FaixaAtendimento()
                                                    .setId(faixaAtendimento.getId())
                                                    .setCodigoLoja(faixaAtendimento.getCodigoLoja())
                                                    .setFaixaInicio(faixaAtendimentoPatchRequestDTO.getFaixaInicio())
                                                    .setFaixaFim(faixaAtendimentoPatchRequestDTO.getFaixaFim());

        return FaixaAtendimentoMapper.toFaixaAtendimentoResponseDTO(faixaAtendimentoRepository.save(updatedFaixaAtendimento));
    }

    @Override
    public FaixaAtendimentoResponseDTO getFaixaAtendimentoPorCep(final Long cep) {

        return FaixaAtendimentoMapper.toFaixaAtendimentoResponseDTO(faixaAtendimentoRepository.findByCep(cep)
                 .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma faixa atendendo no Cep informado")));
    }

    @Override
    public FaixaAtendimentoResponseDTO setFaixaAtendimento(final FaixaAtendimentoPostRequestDTO faixaAtendimentoPostRequestDTO){

        this.validaFaixaInvalida(faixaAtendimentoPostRequestDTO.getFaixaInicio(), faixaAtendimentoPostRequestDTO.getFaixaFim());
        this.validaLojaJaCadastrada(faixaAtendimentoPostRequestDTO.getCodigoLoja());
        this.validaSobreposicaoEntreFaixas(faixaAtendimentoPostRequestDTO.getFaixaInicio(), faixaAtendimentoPostRequestDTO.getFaixaFim());

        FaixaAtendimento newFaixaAtendimento = new FaixaAtendimento()
                                                .setCodigoLoja(faixaAtendimentoPostRequestDTO.getCodigoLoja())
                                                .setFaixaInicio(faixaAtendimentoPostRequestDTO.getFaixaInicio())
                                                .setFaixaFim(faixaAtendimentoPostRequestDTO.getFaixaFim());

        return FaixaAtendimentoMapper.toFaixaAtendimentoResponseDTO(faixaAtendimentoRepository.save(newFaixaAtendimento));
    }

    private void validaFaixaInvalida(final Long faixaInicio, final Long faixaFim){
        if(faixaFim <= faixaInicio)
            throw new BusinessException("O range informado não é válido");
    }

    private void validaLojaJaCadastrada(final String codigoLoja){
        if(faixaAtendimentoRepository.findByCodigoLoja(codigoLoja).isPresent())
            throw new ConflictException("Já existe faixa cadastrada para essa loja");
    }

    private void validaSobreposicaoEntreFaixas(final Long faixaInicio, final Long faixaFim) {
        List<FaixaAtendimento> faixaAtendimentoList = faixaAtendimentoRepository.findByFaixaBetween(faixaInicio, faixaFim);
        if (!faixaAtendimentoList.isEmpty()){
            throw new ConflictException("A faixa informa conflita com a faixa de outra loja :> " + faixaAtendimentoList.stream().map(FaixaAtendimento::getCodigoLoja).collect(Collectors.joining(", ")));
        }

    }

    private FaixaAtendimento buscaFaixaPorId(Long id){
        Optional<FaixaAtendimento> faixaAtendimento = faixaAtendimentoRepository.findById(id);

        if(!faixaAtendimento.isPresent())
            throw new ResourceNotFoundException("Faixa de atendimento não localizada");

        return faixaAtendimento.get();
    }
}
