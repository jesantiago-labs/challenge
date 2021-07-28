package br.com.wine.challenge.api.v1.faixa.atendimento.docs;

import br.com.wine.challenge.dto.error.response.ErrorResponseDTO;
import br.com.wine.challenge.dto.faixa.atendimento.request.FaixaAtendimentoPatchRequestDTO;
import br.com.wine.challenge.dto.faixa.atendimento.request.FaixaAtendimentoPostRequestDTO;
import br.com.wine.challenge.dto.faixa.atendimento.response.FaixaAtendimentoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Tag(name = "Faixas de Cep em que as lojas atendem.", description = "Responsável por manter as faixas de atendimento das lojas.")
public interface FaixaAtendimentoControllerDocs {

    @Operation(summary = "Registra a faixa de atendimento de uma loja.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Registro criado"),
            @ApiResponse(responseCode = "400",
                    description = "Payload da requisição inválido",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "409",
                    description = "A requisição não pode ser completada devido a um conflito de estado do registro informado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<FaixaAtendimentoResponseDTO> postFaixa(@NotNull @Valid FaixaAtendimentoPostRequestDTO faixaAtendimentoPostRequestDTO);

    @Operation(summary = "Retorna a loja que atende a um determinado Cep.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Requisição sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Resource não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<FaixaAtendimentoResponseDTO> getFaixaAtendimentoPorCep(@NotNull Long porCep);

    @Operation(summary = "Atualiza uma faixa de atendimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Requisição sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Resource não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<FaixaAtendimentoResponseDTO> patchFaixa(@NotNull Long id, @NotNull @Valid FaixaAtendimentoPatchRequestDTO faixaAtendimentoPatchRequestDTO);

    @Operation(summary = "Remove uma faixa de atendimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Requisição sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Resource não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<FaixaAtendimentoResponseDTO> deleteFaixa(@NotNull Long id);

}
