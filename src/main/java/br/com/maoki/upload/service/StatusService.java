package br.com.maoki.upload.service;

import br.com.maoki.upload.model.Arquivo;
import br.com.maoki.upload.model.ArquivoErro;
import br.com.maoki.upload.model.DetalhesArquivoDto;
import br.com.maoki.upload.model.SituacaoProcessamentoEnum;
import br.com.maoki.upload.repository.ArquivoRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
public class StatusService {

  @Inject
  ArquivoRepository arquivoRepository;

  public Optional<DetalhesArquivoDto> consultarArquivo(String referencia) {
    Arquivo arquivo = arquivoRepository.findByReferenciaAndAtivo(referencia, true);
    return Optional.ofNullable(arquivo != null ? arquivo.toDto() : null);
  }

  @Transactional
  public void atualizarStatus(String referencia, DetalhesArquivoDto dto) {
    Arquivo arquivo = arquivoRepository.findByReferenciaAndAtivo(referencia, true);

    arquivo.setSituacaoProcessamento(dto.getSituacao());
    if(dto.getMsgErro() != null && !dto.getMsgErro().trim().isEmpty()){
      ArquivoErro arquivoerro = new ArquivoErro();
      arquivoerro.setArquivo(arquivo);
      arquivoerro.setMsg(dto.getMsgErro());
      arquivo.setArquivoErro(arquivoerro);
    }
    if(SituacaoProcessamentoEnum.PROCESSADO_COM_SUCESSO == dto.getSituacao()){
      arquivo.setArquivoErro(null);
      arquivo.setProcessado(true);
      arquivo.setDataProcessamento(LocalDateTime.now());
    }

    arquivoRepository.persist(arquivo);

  }
}
