package com.emc.daas.data_mgmt;

import com.emc.daas.data.DataEntity;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.user.DaaSUser;

import java.util.List;

/**
 * Created by timofb on 11/8/2015.
 */
public class DataManagerMock implements DataManager {
    @Override
    public String createDataSet(DaaSMetadata metadata, String username) {
        return null;
    }

    @Override
    public String putObject(String datasetId, DataEntity object, String username) {
        return null;
    }

    @Override
    public boolean deleteDataEntity(String objectId, String username) {
        return false;
    }
}
