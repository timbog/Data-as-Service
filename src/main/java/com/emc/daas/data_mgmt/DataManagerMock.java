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
    public String putObject(String dataSetId, DataEntity object) {
        return "test";
    }

    @Override
    public List<DaaSUser> getUserList() {
        return null;
    }

    @Override
    public boolean addUser(DaaSUser user) {
        return false;
    }

    @Override
    public List<DaaSUser> searchUsers(DaaSUser user) {
        return null;
    }

    @Override
    public DaaSUser getUser(String username) {
        return null;
    }

    @Override
    public boolean deleteUser(String username) {
        return false;
    }

    @Override
    public String createDataSet(DaaSMetadata metadata) {
        return null;
    }

    @Override
    public boolean deleteDataEntity(String objectId) {
        return false;
    }

}
