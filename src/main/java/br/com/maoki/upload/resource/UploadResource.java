package br.com.maoki.upload.resource;

import br.com.maoki.upload.model.dto.ArquivoDTO;
import br.com.maoki.upload.model.enums.TipoArquivoEnum;
import br.com.maoki.upload.service.AzureService;
import br.com.maoki.upload.service.UploadService;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/upload")
public class UploadResource {

    @Inject
    UploadService service;

    @Inject
    AzureService azureService;

    @GET
    @Path("/")
    public Response consultarArquivos(@QueryParam("tipo") TipoArquivoEnum arquivo, @QueryParam("idCliente") Long idCliente){
        return null;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input) {
        String referencia =service.uploadFile(new ArquivoDTO(input));

        return Response.ok(referencia).build();
    }


    @PUT
    @Path("/cliente/{idCliente}/processado/{referencia}")
    public Response uploadFile(@PathParam("referencia") String referencia) {
        service.atualizaArquivo(referencia);

        return Response.ok().build();
    }

//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFileToLocation(MultipartFormDataInput input) {
//
//        azureService.uploadFile(new Arquivo(input), new AzureBlobContainer(input));
//
//        return Response.accepted().build();
//    }


}
