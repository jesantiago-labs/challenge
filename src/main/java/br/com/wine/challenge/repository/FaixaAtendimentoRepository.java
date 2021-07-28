package br.com.wine.challenge.repository;

import br.com.wine.challenge.model.FaixaAtendimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FaixaAtendimentoRepository extends CrudRepository<FaixaAtendimento, Long> {

    Optional<FaixaAtendimento> findById(Long id);

    Optional<FaixaAtendimento> findByCodigoLoja(String codigoLoja);

    @Query(value = "SELECT fa FROM FaixaAtendimento fa WHERE (fa.faixaInicio BETWEEN :newFaixaIni and :newFaixaFim) OR (fa.faixaFim BETWEEN :newFaixaIni and :newFaixaFim)")
    List<FaixaAtendimento> findByFaixaBetween(@Param("newFaixaIni") Long newFaixaIni, @Param("newFaixaFim") Long newFaixaFim);

    @Query(value = "SELECT fa FROM FaixaAtendimento fa WHERE :cep BETWEEN fa.faixaInicio and fa.faixaFim")
    Optional<FaixaAtendimento> findByCep(@Param("cep") Long cep);

}
