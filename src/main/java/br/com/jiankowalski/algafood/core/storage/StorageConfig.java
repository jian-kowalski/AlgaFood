package br.com.jiankowalski.algafood.core.storage;

import br.com.jiankowalski.algafood.domain.service.FotoStorageService;
import br.com.jiankowalski.algafood.infrastructure.service.storage.LocalFotoStorageService;
import br.com.jiankowalski.algafood.infrastructure.service.storage.S3FotoStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    private final StorageProperties properties;

    public StorageConfig(StorageProperties properties) {
        this.properties = properties;
    }
    
    private AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentials())
                .withRegion(properties.getS3().getRegiao())
                .build();
    }

    private AWSStaticCredentialsProvider awsCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                properties.getS3().getIdChaveAcesso(),
                properties.getS3().getChaveAcessoSecreta()));
    }

    @Bean
    public FotoStorageService fotoStorageService() {
        if (StorageProperties.TipoStorage.S3.equals(properties.getTipoStorage())) {
            return new S3FotoStorageService(amazonS3(), properties);
        }
        return new LocalFotoStorageService(properties);
    }
}
