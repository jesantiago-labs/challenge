package br.com.wine.challenge.dto.faixa.atendimento.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public enum FaixaAtendimentoRequestDTO {;

    public interface CodigoLoja{
        @NotBlank(message = "Favor informar um valor para o campo <codigoLoja>")
        String getCodigoLoja();
    }

    public interface FaixaInicio{
        @NotNull(message = "Favor informar um valor entre 0 e 9999999999 para o campo <faixaInicio>")
        @Min(0) @Max(9999999999L)
        Long getFaixaInicio();
    }

    public interface FaixaFim{
        @NotNull(message = "Favor informar um valor entre 0 e 9999999999 para o campo <faixaInicio>")
        @Min(0) @Max(9999999999L)
        Long getFaixaFim();
    }

}
