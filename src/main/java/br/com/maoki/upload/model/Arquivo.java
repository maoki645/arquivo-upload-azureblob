package br.com.maoki.upload.model;


import br.com.maoki.upload.model.converters.ArquivoConverter;
import br.com.maoki.upload.model.dto.ArquivoDTO;
import br.com.maoki.upload.model.enums.TipoArquivoEnum;

import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ARQUIVO")
public class Arquivo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_ARQUIVO")
  private Long idArquivo;

  @Column(name = "ID_CLIENTE")
  private Long idCliente;

  @Column(name = "ID_EMPRESA")
  private Long idEmpresa;

  @Column(name = "REFERENCIA")
  private String referencia;

  @Column(name = "NOME")
  private String nome;

  @Column(name = "PROCESSADO")
  private Boolean processado;

  @Convert(converter = ArquivoConverter.class)
  @Column(name = "ID_TIPO_LAYOUT_ARQUIVO")
  private TipoArquivoEnum tipoArquivo;

  @Column(name = "DATA_PROCESSAMENTO")
  private LocalDateTime dataProcessamento;

  @Column(name = "CADASTRO")
  private LocalDateTime cadastro;

  @Column(name = "ATIVO")
  private boolean ativo;

  @Column(name = "SITUACAO_PROCESSAMENTO")
  private SituacaoProcessamentoEnum situacaoProcessamentoEnum;

  @OneToOne(mappedBy = "arquivo", cascade = CascadeType.ALL, orphanRemoval = true)
  private ArquivoErro arquivoErro;

  public Arquivo(ArquivoDTO arquivoDTO) {
    this.idCliente = arquivoDTO.getIdCliente();
    this.tipoArquivo = arquivoDTO.getTipoArquivo();
    this.nome = arquivoDTO.getFileName();
    this.referencia = arquivoDTO.getReferencia();
    this.ativo = true;
    this.processado = arquivoDTO.getProcessado();
    this.idEmpresa = arquivoDTO.getIdEmpresa();
    this.dataProcessamento = arquivoDTO.getDataProcessamento();
    this.cadastro = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
  }

  public Arquivo() {
  }

  public Long getIdArquivo() {
    return idArquivo;
  }

  public void setIdArquivo(Long idArquivo) {
    this.idArquivo = idArquivo;
  }

  public Long getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Long idCliente) {
    this.idCliente = idCliente;
  }

  public String getReferencia() {
    return referencia;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }


  public Long getIdEmpresa() {
    return idEmpresa;
  }

  public void setIdEmpresa(Long idEmpresa) {
    this.idEmpresa = idEmpresa;
  }

  public Boolean getProcessado() {
    return processado;
  }

  public void setProcessado(Boolean processado) {
    this.processado = processado;
  }

  public LocalDateTime getDataProcessamento() {
    return dataProcessamento;
  }

  public void setDataProcessamento(LocalDateTime dataProcessamento) {
    this.dataProcessamento = dataProcessamento;
  }

  public LocalDateTime getCadastro() {
    return cadastro;
  }

  public void setCadastro(LocalDateTime cadastro) {
    this.cadastro = cadastro;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public TipoArquivoEnum getTipoArquivo() {
    return tipoArquivo;
  }

  public void setTipoArquivo(TipoArquivoEnum tipoArquivo) {
    this.tipoArquivo = tipoArquivo;
  }

  public SituacaoProcessamentoEnum getSituacaoProcessamento() {
    return situacaoProcessamentoEnum;
  }

  public void setSituacaoProcessamento(SituacaoProcessamentoEnum situacaoProcessamentoEnum) {
    this.situacaoProcessamentoEnum = situacaoProcessamentoEnum;
  }

  public ArquivoErro getArquivoErro() {
    return arquivoErro;
  }

  public void setArquivoErro(ArquivoErro arquivoErro) {
    this.arquivoErro = arquivoErro;
  }

  public DetalhesArquivoDto toDto(){
    DetalhesArquivoDto dto = new DetalhesArquivoDto();

    dto.setIdArquivo(this.getIdArquivo());;
    dto.setIdCliente(this.getIdCliente());
    dto.setIdEmpresa(this.getIdEmpresa());
    dto.setReferencia(this.getReferencia());
    dto.setNome(this.getNome());
    dto.setProcessado(this.getProcessado());
    dto.setTipoArquivo(this.getTipoArquivo());
    dto.setDataProcessamento(this.getDataProcessamento());
    dto.setCadastro(this.getCadastro());
    dto.setAtivo(this.isAtivo());
    dto.setDescricaoProcessamento(this.situacaoProcessamentoEnum.getDescricao());
    dto.setMsgErro(this.arquivoErro != null ? this.arquivoErro.getMsg() : "");
    dto.setSituacao(this.situacaoProcessamentoEnum);

    return dto;
  }
}
