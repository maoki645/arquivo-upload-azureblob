package br.com.maoki.upload.model;

import br.com.maoki.upload.model.enums.TipoArquivoEnum;
import java.time.LocalDateTime;

public class DetalhesArquivoDto {

  private Long idArquivo;
  private Long idCliente;
  private Long idEmpresa;
  private String referencia;
  private String nome;
  private Boolean processado;
  private TipoArquivoEnum tipoArquivo;
  private LocalDateTime dataProcessamento;
  private LocalDateTime cadastro;
  private boolean ativo;
  private SituacaoProcessamentoEnum situacao;
  private String descricaoProcessamento;
  private String msgErro;

  public void setIdArquivo(Long idArquivo) {
    this.idArquivo = idArquivo;
  }

  public Long getIdArquivo() {
    return idArquivo;
  }

  public void setIdCliente(Long idCliente) {
    this.idCliente = idCliente;
  }

  public Long getIdCliente() {
    return idCliente;
  }

  public void setIdEmpresa(Long idEmpresa) {
    this.idEmpresa = idEmpresa;
  }

  public Long getIdEmpresa() {
    return idEmpresa;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  public String getReferencia() {
    return referencia;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  public void setProcessado(Boolean processado) {
    this.processado = processado;
  }

  public Boolean getProcessado() {
    return processado;
  }

  public void setTipoArquivo(TipoArquivoEnum tipoArquivo) {
    this.tipoArquivo = tipoArquivo;
  }

  public TipoArquivoEnum getTipoArquivo() {
    return tipoArquivo;
  }

  public void setDataProcessamento(LocalDateTime dataProcessamento) {
    this.dataProcessamento = dataProcessamento;
  }

  public LocalDateTime getDataProcessamento() {
    return dataProcessamento;
  }

  public void setCadastro(LocalDateTime cadastro) {
    this.cadastro = cadastro;
  }

  public LocalDateTime getCadastro() {
    return cadastro;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public boolean getAtivo() {
    return ativo;
  }

  public void setDescricaoProcessamento(String descricaoProcessamento) {
    this.descricaoProcessamento = descricaoProcessamento;
  }

  public String getDescricaoProcessamento() {
    return descricaoProcessamento;
  }

  public void setMsgErro(String msgErro) {
    this.msgErro = msgErro;
  }

  public String getMsgErro() {
    return msgErro;
  }

  public SituacaoProcessamentoEnum getSituacao() {
    return situacao;
  }

  public void setSituacao(SituacaoProcessamentoEnum situacao) {
    this.situacao = situacao;
  }
}
