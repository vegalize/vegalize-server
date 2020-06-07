package org.eco.vegalize.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger((S3Service.class));

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadFile(String localFilePath){
        try {
            File file = new File(localFilePath);
            LOG.info("Iniciando serviço");
            s3client.putObject(new PutObjectRequest(bucketName, "teste.png", file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            LOG.info("Upload Feito");
        }catch (AmazonServiceException e){
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status Code: " + e.getErrorCode());
        }catch (AmazonClientException e){
            LOG.info("AmazonClientException: "+ e.getMessage());
        }

    }
}
