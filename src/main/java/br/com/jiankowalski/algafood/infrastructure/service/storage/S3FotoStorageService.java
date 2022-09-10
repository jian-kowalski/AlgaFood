package br.com.jiankowalski.algafood.infrastructure.service.storage;

import br.com.jiankowalski.algafood.core.storage.StorageProperties;
import br.com.jiankowalski.algafood.domain.exception.StorageException;
import br.com.jiankowalski.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3FotoStorageService implements FotoStorageService {

    private final AmazonS3 amazonS3;
    private final StorageProperties properties;

    public S3FotoStorageService(AmazonS3 amazonS3, StorageProperties properties) {
        this.amazonS3 = amazonS3;
        this.properties = properties;
    }

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        var url = amazonS3.getUrl(properties.getS3().getBucket(), getPathArq(nomeArquivo));
        return FotoRecuperada.builder().url(url.toString()).build();
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            var metaData = new ObjectMetadata();
            metaData.setContentType(novaFoto.getContentType());

            var putRequest = new PutObjectRequest(
                    properties.getS3().getBucket(),
                    getPathArq(novaFoto.getNomeAquivo()),
                    novaFoto.getInputStream(),
                    metaData
            ).withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putRequest);
        } catch (Exception e) {
            throw new StorageException("Falha ao enviar o arquivo para Amazon S3", e);
        }
    }

    private String getPathArq(String arq) {
        return properties.getS3().getDiretorioFotos().concat("/").concat(arq);
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            String caminhoArquivo = getPathArq(nomeArquivo);

            var deleteObjectRequest = new DeleteObjectRequest(
                    properties.getS3().getBucket(), caminhoArquivo);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
        }
    }
}
