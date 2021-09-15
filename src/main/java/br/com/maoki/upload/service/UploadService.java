package br.com.maoki.upload.service;

import br.com.maoki.upload.model.dto.ArquivoDTO;
import br.com.maoki.upload.model.Arquivo;
import br.com.maoki.upload.repository.ArquivoRepository;

import java.time.LocalDateTime;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
public class UploadService {

  @Inject
  AzureService azureService;

  @Inject
  ArquivoRepository arquivoRepository;

  @Transactional
  public String uploadFile(ArquivoDTO arquivoDTO) {
    //subir arquivo para o servidor
    azureService.uploadFile(arquivoDTO);
    //gravar dados do arquivo carregado
    Arquivo arquivo = new Arquivo(arquivoDTO);

    arquivoRepository.persist(arquivo);
    return arquivo.getReferencia();
  }

  @Transactional
  public String atualizaArquivo(String referencia) {
    Arquivo arquivo = arquivoRepository.findByReferenciaAndAtivo(referencia, true);
    arquivo.setDataProcessamento(LocalDateTime.now());
    arquivo.setProcessado(true);
    arquivoRepository.persist(arquivo);
    return arquivo.getReferencia();
  }
}
