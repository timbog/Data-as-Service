package com.emc.daas.data_mgmt;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.data.DataEntity;
import com.emc.daas.data_mgmt.exceptions.NoSuchDatasetException;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.MetadataManager;
import com.emc.daas.user.DaaSUser;

import java.util.List;
import java.util.UUID;

/**
 * Created by timofb on 11/8/2015.
 */
public class DataManagerMock extends DataManager {

    public DataManagerMock(UserAuthenticator authenticator, MetadataManager metadataManager) {
        super(authenticator, metadataManager);
    }

    @Override
    protected void createDataSet(String datasetId) {

    }

    @Override
    protected boolean deleteDataEntity(UUID objectId, UUID datasetId) {
        return false;
    }

    @Override
    protected void putObjectToStorage(String datasetId, DataEntity object) throws NoSuchDatasetException {

    }

    @Override
    protected boolean deleteDataEntity(UUID objectId) {
        return false;
    }
}
