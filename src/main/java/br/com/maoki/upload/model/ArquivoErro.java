package br.com.maoki.upload.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ARQUIVO_ERRO")
public class ArquivoErro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_ARQUIVO_ERRO")
  private Long idArquivoErro;

  @OneToOne()
  @JoinColumn(name = "id_arquivo", referencedColumnName = "id_arquivo")
  private Arquivo arquivo;

  @Column(name = "MSG")
  private String msg;

  public Long getIdArquivoErro() {
    return idArquivoErro;
  }

  public void setIdArquivoErro(Long idArquivoErro) {
    this.idArquivoErro = idArquivoErro;
  }

  public Arquivo getArquivo() {
    return arquivo;
  }

  public void setArquivo(Arquivo arquivo) {
    this.arquivo = arquivo;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
