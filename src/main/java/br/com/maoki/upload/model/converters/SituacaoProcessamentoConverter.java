package br.com.maoki.upload.model.converters;

import br.com.maoki.upload.model.SituacaoProcessamentoEnum;

import javax.persistence.AttributeConverter;

public class SituacaoProcessamentoConverter implements AttributeConverter<SituacaoProcessamentoEnum, Long> {

  @Override
  public Long convertToDatabaseColumn(SituacaoProcessamentoEnum situacaoProcessamentoEnum) {
    return situacaoProcessamentoEnum != null ? situacaoProcessamentoEnum.getIdSituacao() : null ;
  }

  @Override
  public SituacaoProcessamentoEnum convertToEntityAttribute(Long id) {
    return id != null ? SituacaoProcessamentoEnum.fromValor(id) : null;
  }
}
