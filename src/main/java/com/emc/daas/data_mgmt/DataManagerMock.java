package com.emc.daas.data_mgmt;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.data.DataEntity;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.MetadataManager;
import com.emc.daas.user.DaaSUser;

import java.util.List;

/**
 * Created by timofb on 11/8/2015.
 */
public class DataManagerMock extends DataManager {

    public DataManagerMock(UserAuthenticator authenticator, MetadataManager metadataManager) {
        super(authenticator, metadataManager);
    }

    @Override
    protected void createDataSet(DaaSMetadata metadata) {

    }

    @Override
    protected boolean deleteDataEntity(String objectId) {
        return false;
    }

    @Override
    protected void putObjectToStorage(String datasetId, DataEntity object) {

    }
}
