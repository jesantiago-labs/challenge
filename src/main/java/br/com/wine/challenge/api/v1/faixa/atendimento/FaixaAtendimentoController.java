package br.com.wine.challenge.api.v1.faixa.atendimento;

import br.com.wine.challenge.api.v1.faixa.atendimento.docs.FaixaAtendimentoControllerDocs;
import br.com.wine.challenge.dto.faixa.atendimento.request.FaixaAtendimentoPatchRequestDTO;
import br.com.wine.challenge.dto.faixa.atendimento.request.FaixaAtendimentoPostRequestDTO;
import br.com.wine.challenge.dto.faixa.atendimento.response.FaixaAtendimentoResponseDTO;
import br.com.wine.challenge.service.FaixaAtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/faixas-atendimento",
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class FaixaAtendimentoController implements FaixaAtendimentoControllerDocs {

    @Autowired
    FaixaAtendimentoService faixaAtendimentoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<FaixaAtendimentoResponseDTO> postFaixa(@RequestBody FaixaAtendimentoPostRequestDTO faixaAtendimentoPostRequestDTO) {
        return ResponseEntity.ok().body(faixaAtendimentoService.setFaixaAtendimento(faixaAtendimentoPostRequestDTO));
    }

    @GetMapping
    @Override
    public ResponseEntity<FaixaAtendimentoResponseDTO> getFaixaAtendimentoPorCep(@RequestParam Long porCep) {
        return ResponseEntity.ok().body(faixaAtendimentoService.getFaixaAtendimentoPorCep(porCep));
    }

    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<FaixaAtendimentoResponseDTO> patchFaixa(@PathVariable Long id, @RequestBody FaixaAtendimentoPatchRequestDTO faixaAtendimentoPatchRequestDTO) {
        return ResponseEntity.ok().body(faixaAtendimentoService.updateFaixaAtendimento(id, faixaAtendimentoPatchRequestDTO));
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<FaixaAtendimentoResponseDTO> deleteFaixa(@PathVariable Long id) {
        return ResponseEntity.ok().body(faixaAtendimentoService.deleteFaixaAtendimento(id));
    }
}
