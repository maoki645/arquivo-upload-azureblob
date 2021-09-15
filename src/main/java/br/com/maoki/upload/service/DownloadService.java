package br.com.maoki.upload.service;

import br.com.maoki.upload.model.Arquivo;
import br.com.maoki.upload.repository.ArquivoRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class DownloadService {

  @Inject
  AzureService azureService;

  @Inject
  ArquivoRepository arquivoRepository;

  public byte[] downloadArquivoPorNome(String referencia) {
    Arquivo arquivo = arquivoRepository.findByReferenciaAndAtivo(referencia,true);
    return azureService.downloadArquivoPorNome(arquivo.getIdCliente() +"/" + arquivo.getTipoArquivo().getId() + "/" + arquivo.getReferencia());
  }
}
