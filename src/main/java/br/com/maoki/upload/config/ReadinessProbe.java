package br.com.maoki.upload.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
public class ReadinessProbe implements HealthCheck {

  @ConfigProperty(name = "giss.azure.blob.conteiner")
  String CONTAINER;
  @ConfigProperty(name = "giss.azure.blob.connection-string")
  String CONNECTION_STRING;


  @Override
  public HealthCheckResponse call() {
    return checkDefaultAzureConnection() ? HealthCheckResponse.up("Azure Connection") : HealthCheckResponse.down("Azure Connection");
  }

  private boolean checkDefaultAzureConnection() {
    try {
      BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(CONNECTION_STRING).buildClient();
      BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(CONTAINER);
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}
