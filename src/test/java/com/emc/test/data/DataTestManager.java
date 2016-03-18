package com.emc.test.data;

import com.ctc.wstx.io.SystemId;
import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.data.DataEntity;
import com.emc.daas.data_mgmt.DataManager;
import com.emc.daas.data_mgmt.exceptions.NoSuchDatasetException;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.MetadataManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by timofb on 16-Mar-16.
 */
public class DataTestManager extends DataManager {
    private static String TEMP_PATH = System.getProperty("java.io.tmpdir");

    private boolean deleteFile(File file) {
        if (file.isDirectory()) {
            try {
                FileUtils.deleteDirectory(file);
                return true;
            }
            catch (IOException ex) {
                return false;
            }
        }
        else {
            return file.delete();
        }
    }

    @Override
    protected boolean deleteDataEntity(UUID objectId) {
        File file = new File(TEMP_PATH, objectId.toString());
        return deleteFile(file);
    }

    @Override
    protected boolean deleteDataEntity(UUID objectId, UUID datasetId) {
        File dir = new File(TEMP_PATH, datasetId.toString());
        File file = new File(dir.getAbsolutePath(), objectId.toString());
        return deleteFile(file);
    }



    @Override
    protected void putObjectToStorage(String datasetId, DataEntity object) throws NoSuchDatasetException{
        File dataSetDir = new File(TEMP_PATH, datasetId);
        DataStringEntity entity = (DataStringEntity) object;
        if (!dataSetDir.exists()) {
            throw new NoSuchDatasetException();
        }
        File newFile = new File(dataSetDir.getAbsolutePath(), entity.getId().toString());
        try {
            newFile.createNewFile();
            FileUtils.writeStringToFile(newFile, entity.getContent());
        }
        catch (IOException ex) {
        }
    }

    @Override
    protected void createDataSet(String datasetId) {
        new File(TEMP_PATH, datasetId).mkdirs();
    }

    public DataTestManager(UserAuthenticator authenticator, MetadataManager metadataManager) {
        super(authenticator, metadataManager);
    }
}
