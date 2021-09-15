package br.com.maoki.upload.model.dto;

import br.com.maoki.upload.exceptions.UploadException;
import br.com.maoki.upload.model.enums.TipoArquivoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ws.rs.core.GenericType;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class ArquivoDTO {
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private long tamanho;
  private String fileName;
  private InputStream fileInputStream;
  private TipoArquivoEnum tipoArquivo;
  private String referencia;
  private Boolean processado;

  @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
  private LocalDateTime dataProcessamento;
  private Long idEmpresa;
  private Long idCliente;

  public ArquivoDTO(MultipartFormDataInput input) {
    Map<String, List<InputPart>> formParts = input.getFormDataMap();

    preencherDadosArquivo(formParts);

    preencherTipoArquivo(formParts);

    preencherCliente(formParts);

    preencherEmpresa(formParts);

    preencherProcessado(tipoArquivo);

    //preencherDataProcessamento(formParts);

    if (this.fileInputStream == null || this.fileName == null || this.tamanho == 0L) {
      throw new UploadException("Erro lendo arquivo para upload. Verifique o arquivo enviado e se ele possui algum conteudo ou o nome da propriedade de envio e tente novamente");
    }
    this.referencia = UUID.randomUUID().toString();
    if (tipoArquivo.equals(TipoArquivoEnum.DESIF_APURACAO_MENSAL)
        || tipoArquivo.equals(TipoArquivoEnum.DESIF_INFORMACOES_COMUNS)
        || tipoArquivo.equals(TipoArquivoEnum.DESIF_DEMONSTRATIVO_CONTABIL)
        || tipoArquivo.equals(TipoArquivoEnum.DESIF_DEMONSTRATIVO_LANCAMENTOS_CONTABEIS)) {
      this.referencia = referencia.substring(0,30);
    }

  }

  private void preencherCliente(Map<String, List<InputPart>> formParts) {
    List<InputPart> inputCliente = formParts.get("idCliente");

    if(inputCliente == null){
      throw new UploadException("Id do cliente não encontrado.");
    }

    try {
      for (InputPart inputPart : inputCliente) {
        String codigoCliente = inputPart.getBodyAsString();
        this.idCliente = Long.valueOf(codigoCliente);
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new UploadException("Id do cliente não encontrado.");
    }

  }


  private void preencherProcessado(TipoArquivoEnum tipo) {
    if (!tipo.equals(TipoArquivoEnum.SOLICITACAO_DOCUMENTO_CPOM)) {
        this.processado = false;
    }else{
      this.processado = null;
    }

  }


  private void preencherDataProcessamento(Map<String, List<InputPart>> formParts) {
    List<InputPart> inputDataProcessamento = formParts.get("dataProcessamento");

    if(inputDataProcessamento == null){
      this.dataProcessamento = null;
      return;
    }

    try {
      for (InputPart inputPart : inputDataProcessamento) {
        LocalDateTime dataProcessamento = LocalDateTime.parse(inputPart.getBody(String.class, null),formatter);
        this.dataProcessamento = dataProcessamento;
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new UploadException("data processamento inválido");
    }

  }

  private void preencherEmpresa(Map<String, List<InputPart>> formParts) {
    List<InputPart> inputEmpresa = formParts.get("idEmpresa");

    if(inputEmpresa == null){
      return;
    }

    try {
      for (InputPart inputPart : inputEmpresa) {
        String idEmpresa = inputPart.getBodyAsString();
        this.idEmpresa = Long.valueOf(idEmpresa);
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new UploadException("Id do Empresa não encontrado.");
    }

  }

  private void preencherTipoArquivo(Map<String, List<InputPart>> formParts) {
    List<InputPart> inputTipo = formParts.get("tipo");

    if(inputTipo == null){
      throw new UploadException("Tipo do arquivo não encontrado.");
    }

    Integer tipo = null;
    try {
      for (InputPart inputPart : inputTipo) {
        tipo = inputPart.getBody(new GenericType<Integer>(){});
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new UploadException("Tipo do arquivo nao informado");
    }
    if (tipo == null) {
      throw new UploadException("Tipo do arquivo nao informado");
    }

    this.tipoArquivo = TipoArquivoEnum.fromValor(tipo);

    if (this.tipoArquivo == null) {
      throw new UploadException("Tipo de arquivo nao suportado");
    }

  }

  private void preencherDadosArquivo(Map<String, List<InputPart>> formParts) {
    List<InputPart> inputFile = formParts.get("arquivo");

    if(inputFile == null){
      throw new UploadException("Propriedade arquivo não encontrada");
    }

    for (InputPart inputPart : inputFile) {
      try {
        InputStream body = inputPart.getBody(InputStream.class, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while (((len) = body.read(buffer)) > -1) {
          baos.write(buffer, 0, len);
        }
        baos.flush();
        this.tamanho = baos.size();
        this.fileInputStream = new ByteArrayInputStream(baos.toByteArray());

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    List<InputPart> inputNome = formParts.get("nome");

    if(inputNome == null){
      throw new UploadException("Tipo do arquivo não encontrado.");
    }


    try {
      for (InputPart inputPart : inputNome) {
        this.fileName = inputPart.getBody(new GenericType<String>(){});
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new UploadException("Tipo do arquivo nao informado");
    }
  }

  public String getFileName() {
    return fileName;
  }

  public InputStream getFileInputStream() {
    return fileInputStream;
  }

  public long getTamanho() {
    return tamanho;
  }

//  public String getFile() {
//    Path tempFile = null;
//    try {
//      tempFile = Files.createTempFile(fileName, null);
//      Files.write(tempFile, fileInputStream.readAllBytes());
//      return tempFile.toString();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    return null;
//  }


  public Boolean getProcessado() {
    return processado;
  }

  public void setProcessado(Boolean processado) {
    this.processado = processado;
  }

  public Long getIdCliente() {
    return idCliente;
  }

  @Override
  public String toString() {
    return "Arquivo{" +
        "tamanho=" + tamanho +
        ", fileName='" + fileName + '\'' +
        ", tipoArquivo=" + tipoArquivo +
        ", referencia=" + referencia +
        '}';
  }

  public String getReferencia() {
    return referencia;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  public TipoArquivoEnum getTipoArquivo() {
    return tipoArquivo;
  }

  public void setTipoArquivo(TipoArquivoEnum tipoArquivo) {
    this.tipoArquivo = tipoArquivo;
  }



  public LocalDateTime getDataProcessamento() {
    return dataProcessamento;
  }

  public void setDataProcessamento(LocalDateTime dataProcessamento) {
    this.dataProcessamento = dataProcessamento;
  }

  public Long getIdEmpresa() {
    return idEmpresa;
  }

  public void setIdEmpresa(Long idEmpresa) {
    this.idEmpresa = idEmpresa;
  }
}
