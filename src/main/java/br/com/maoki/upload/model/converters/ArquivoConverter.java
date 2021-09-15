package br.com.maoki.upload.model.converters;

import br.com.maoki.upload.model.enums.TipoArquivoEnum;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ArquivoConverter  implements AttributeConverter<TipoArquivoEnum, Integer> {

  @Override
  public Integer convertToDatabaseColumn(TipoArquivoEnum attribute) {
    return attribute != null ? attribute.getValor() : null;
  }

  @Override
  public TipoArquivoEnum convertToEntityAttribute(Integer dbData) {
    return dbData != null ? TipoArquivoEnum.fromValor(dbData) : null;
  }
}

