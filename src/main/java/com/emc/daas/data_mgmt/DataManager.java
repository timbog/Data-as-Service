package com.emc.daas.data_mgmt;

import com.emc.daas.data.DataEntity;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.user.DaaSUser;

import java.util.List;

/**
 * Created by timofb on 11/5/2015.
 */
public interface DataManager {
    public List<DaaSUser> getUserList();

    public boolean addUser(DaaSUser user);

    public List<DaaSUser> searchUsers(DaaSUser user);

    public DaaSUser getUser(String username);

    public boolean deleteUser(String username);

    public String createDataSet(DaaSMetadata metadata);

    public String putObject(String datasetId, DataEntity object);

    public boolean deleteDataEntity(String objectId);

}
