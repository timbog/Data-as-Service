package com.emc.daas.data_mgmt;

import com.emc.daas.data.DataEntity;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.user.DaaSUser;

import java.util.List;

/**
 * Created by timofb on 11/5/2015.
 */
public interface DataManager {
    public String createDataSet(DaaSMetadata metadata, String username);

    public String putObject(String datasetId, DataEntity object, String username);

    public boolean deleteDataEntity(String objectId, String username);

}
