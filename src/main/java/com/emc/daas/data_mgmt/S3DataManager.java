package com.emc.daas.data_mgmt;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.data.DataEntity;
import com.emc.daas.data_mgmt.exceptions.NoSuchDatasetException;
import com.emc.daas.metadata_mgmt.MetadataManager;

import java.io.File;
import java.util.UUID;

/**
 * Created by timofb on 01-Apr-16.
 */
public class S3DataManager extends DataManager{
    private AmazonS3Client s3Client;

    public S3DataManager(UserAuthenticator authenticator, MetadataManager metadataManager, AmazonS3Client client) {
        super(authenticator, metadataManager);
        s3Client = client;
    }

    @Override
    protected boolean deleteDataEntity(UUID objectId, UUID datasetId) {
        s3Client.deleteObject(datasetId.toString(), objectId.toString());
        if (s3Client.getObject(datasetId.toString(), objectId.toString()) != null) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean deleteDataEntity(UUID objectId) {
        s3Client.deleteBucket(objectId.toString());
        if (s3Client.doesBucketExist(objectId.toString())) {
            return false;
        }
        return true;
    }

    @Override
    protected void putObjectToStorage(String datasetId, DataEntity object) throws NoSuchDatasetException {
        s3Client.putObject(datasetId.toString(), object.getId().toString(), null, null);
    }

    @Override
    protected void createDataSet(String datasetId) {
        s3Client.createBucket(datasetId.toString());
    }
}
