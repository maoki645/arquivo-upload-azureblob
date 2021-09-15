package br.com.maoki.upload.service;

import br.com.maoki.upload.model.dto.ArquivoDTO;
import br.com.maoki.upload.model.dto.AzureBlobContainer;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import java.io.ByteArrayOutputStream;
import javax.enterprise.context.Dependent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

@Dependent
public class AzureService {
  private static final Logger log = Logger.getLogger(AzureService.class);
  @ConfigProperty(name = "giss.azure.blob.conteiner")
  String CONTAINER;
  @ConfigProperty(name = "giss.azure.blob.connection-string")
  String CONNECTION_STRING;

  public void uploadFile(ArquivoDTO arquivoDTO) {
    log.log(Level.INFO, "inicio upload arquivo");

    BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(CONNECTION_STRING).buildClient();

    BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(CONTAINER);

    BlobClient blobClient = blobContainerClient.getBlobClient(arquivoDTO.getIdCliente()+"/"+ arquivoDTO.getTipoArquivo().getId()+"/"+arquivoDTO.getReferencia());

    blobClient.upload(arquivoDTO.getFileInputStream(), arquivoDTO.getTamanho(), true);
    log.log(Level.INFO, "Upload enviado com sucesso: "+ arquivoDTO.getIdCliente()+"/"+ arquivoDTO.getTipoArquivo().getId()+"/"+arquivoDTO.getReferencia());
    log.log(Level.INFO, "fim upload arquivo");


  }

  public byte[] downloadArquivoPorNome(String nomeArquivo) {
    log.log(Level.INFO, "inicio download arquivo");

    BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(CONNECTION_STRING).buildClient();

    BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(CONTAINER);

    BlobClient blobClient = blobContainerClient.getBlobClient(nomeArquivo);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    blobClient.download(baos);
    log.log(Level.INFO, "Download recebido com sucesso: "+ nomeArquivo);
    log.log(Level.INFO, "fim download arquivo");

    return baos.toByteArray();

  }

    public void uploadFile(ArquivoDTO arquivoDTO, AzureBlobContainer blobContainer) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(blobContainer.getConnectionString()).buildClient();

        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(blobContainer.getContainerName());

        BlobClient blobClient = blobContainerClient.getBlobClient(arquivoDTO.getFileName());

        blobClient.upload(arquivoDTO.getFileInputStream(), arquivoDTO.getTamanho(), true);

    }
}
