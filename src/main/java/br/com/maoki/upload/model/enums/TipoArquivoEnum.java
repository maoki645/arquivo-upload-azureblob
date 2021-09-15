package br.com.maoki.upload.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;

public enum TipoArquivoEnum {
  RPS(1, "RPS"),
  NFTSE(2, "NFTS-e"),
  RPS_NFTSE(3, "rps_NFTS-e"),
  PER(4, "PER"),
  PERMEI(5, "PERMEI"),
  PGDAS(6, "PGDAS"),
  DESIF_INFORMACOES_COMUNS(7, "Informações comuns aos municipios"),
  DESIF_APURACAO_MENSAL(8, "Apuração Mensal"),
  CURSO(9, "Curso"),
  ALUNO(10, "Aluno"),
  ARQUIVO_RETORNO(11, "Arquivo Retorno"),
  CALLBACK_RETORNO_GIEX(12, "Callback Retorno Giex"),
  DESCONTO(13, "Desconto"),
  NCM(14, "NCM"),
  DAF_607(15, "DAF 607"),
  DESIF_DEMONSTRATIVO_CONTABIL(16, "Demonstrativo Contábil"),
  DESIF_DEMONSTRATIVO_LANCAMENTOS_CONTABEIS(17, "Demonstrativo das Partidas dos Lançamentos Contábeis"),
  SOLICITACAO_DOCUMENTO_CPOM(18, "Solicitação documento CPOM"),
  DASSENDA(19, "DASSENDA");

  private Integer id;
  private String descricao;

  private TipoArquivoEnum(Integer id, String descricao) {
    this.id = id;
    this.descricao = descricao;
  }

  private static final Map<Integer, TipoArquivoEnum> valorToArquivoTipo = new HashMap<>();

  static {
    for (TipoArquivoEnum remessaTipo : TipoArquivoEnum.values()) {
      valorToArquivoTipo.put(remessaTipo.getValor(), remessaTipo);
    }
  }

  @JsonCreator
  public static TipoArquivoEnum fromValor(Integer valor) {
    return valorToArquivoTipo.get(valor);
  }

  @JsonValue
  public Integer getValor() {
    return id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
}


