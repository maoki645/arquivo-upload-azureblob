package br.com.maoki.upload.model.dto;

import br.com.maoki.upload.exceptions.UploadException;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AzureBlobContainer {

    private String connectionString;
    private String containerName;

    public AzureBlobContainer(MultipartFormDataInput input) {
        Map<String, List<InputPart>> formDataMap = input.getFormDataMap();
        List<InputPart> container = formDataMap.get("container");
        for (InputPart inputPart : container) {
            try {
                this.containerName = inputPart.getBody(String.class, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<InputPart> connection = formDataMap.get("connection");
        for (InputPart inputPart : connection) {
            try {
                this.connectionString = inputPart.getBody(String.class, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(this.containerName == null || this.connectionString == null){
            throw new UploadException("Erro ao montar a string de conexao ou o nome do container. Verifique o nome de cada propriedade e se elas foram preenchidas corretamente");
        }
    }

    public String getConnectionString() {
        return connectionString;
    }

    public String getContainerName() {
        return containerName;
    }
}
