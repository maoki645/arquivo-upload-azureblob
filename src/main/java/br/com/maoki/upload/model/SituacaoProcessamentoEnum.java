package br.com.maoki.upload.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SituacaoProcessamentoEnum {

  AGUARDANDO_PROCESSAMENTO(0L, "Aguardando processamento"),
  EM_PROCESSAMENTO(1L, "Em processamento"),
  PROCESSADO_COM_SUCESSO(2L, "Processado com sucesso"),
  PROCESSADO_COM_ERRO(3L, "Processado com erro");

  private final Long idSituacao;

  private static final Map<Long, SituacaoProcessamentoEnum> mapa = Arrays
      .stream(SituacaoProcessamentoEnum.values())
      .collect(Collectors.toMap(SituacaoProcessamentoEnum::getIdSituacao, Function.identity()));

  private final String descricao;


  SituacaoProcessamentoEnum(Long i, String msg) {
    this.idSituacao = i;
    this.descricao = msg;
  }

  @JsonCreator
  public static SituacaoProcessamentoEnum fromValor(Long id) {
    return mapa.get(id);
  }

  @JsonValue
  public Long getIdSituacao() {
    return idSituacao;
  }

  public String getDescricao() {
    return descricao;
  }



}
