package br.com.maoki.upload.resource;

import br.com.maoki.upload.service.DownloadService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("download")
public class DownloadResource {

  @Inject
  DownloadService service;

  @GET()
  @Path("{referencia}")
  public Response downloadArquivoPorNome(@PathParam("referencia") String referencia){


    byte[] arquivo = service.downloadArquivoPorNome(referencia);

    return Response.ok(arquivo).build();
  }
}
