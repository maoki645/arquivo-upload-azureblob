package br.com.maoki.upload.repository;

import br.com.maoki.upload.model.Arquivo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArquivoRepository implements PanacheRepository<Arquivo> {

    public Arquivo findByReferenciaAndAtivo(String referencia, boolean ativo) {
        return find("referencia = ?1 and ativo = ?2", referencia, ativo).firstResult();
    }
}
