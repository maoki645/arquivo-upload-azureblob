package br.com.maoki.upload.resource;

import br.com.maoki.upload.model.DetalhesArquivoDto;
import br.com.maoki.upload.service.StatusService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
public class StatusResource {

  @Inject
  StatusService service;

  @GET
  @Path("/{referencia}")
  public Response consultarArquivos(@PathParam("referencia") String referencia){
    return service.consultarArquivo(referencia)
        .map(Response::ok)
        .orElseGet(Response::noContent)
        .build();
  }

  @PUT
  @Path("/{referencia}")
  public Response atualizarStatusArquivo(@PathParam("referencia") String referencia,
      DetalhesArquivoDto dto){
    service.atualizarStatus(referencia, dto);
    return Response.ok().build();
  }


}
