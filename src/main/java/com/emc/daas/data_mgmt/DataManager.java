package com.emc.daas.data_mgmt;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.access_control.UserAccessMode;
import com.emc.daas.data.DataEntity;
import com.emc.daas.data_mgmt.exceptions.NoSuchDatasetException;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.MetadataManager;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeAccessedException;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Created by timofb on 11/5/2015.
 */
public abstract class DataManager {
    private UserAuthenticator authenticator;
    private MetadataManager metadataManager;
    public DataManager(UserAuthenticator authenticator, MetadataManager metadataManager) {
        this.authenticator = authenticator;
        this.metadataManager = metadataManager;
    }

    protected abstract void createDataSet(String datasetId);

    protected abstract void putObjectToStorage(String datasetId, DataEntity object) throws NoSuchDatasetException;

    protected abstract boolean deleteDataEntity(UUID objectId);

    protected abstract boolean deleteDataEntity(UUID objectId, UUID datasetId);

    public boolean putObject(String datasetId, DataEntity object, String username) {
        if (authenticator.hasAccess(username, UserAccessMode.CREATE, null)) {
            try {
                Map<String, String> map = new HashMap<String, String>();
                map.put("dataset", datasetId);
                metadataManager.putMeta(object.getId(), map, username);
                this.putObjectToStorage(datasetId, object);
                return true;
            }
            catch (MetadataCannotBeChangedException ex) {
            }
            catch (NoSuchDatasetException ex1) {
            }
        }
        return false;
    }

    public void createDataSet(DaaSMetadata metadata, String username) {
        if (authenticator.hasAccess(username, UserAccessMode.CREATE, null)) {
            try {
                UUID datasetId = metadata.getUUID();
                this.createDataSet(datasetId.toString());
                metadataManager.putMeta(metadata.getUUID(), metadata.getMeta(), username);
            }
            catch (MetadataCannotBeChangedException ex) {
            }
        }
    }

    public boolean deleteDataEntity(UUID objectId, String username){
        try {
            DaaSMetadata meta = metadataManager.getMeta(objectId, username);
            if (authenticator.hasAccess(username, UserAccessMode.FULL, meta)) {
                if (meta.getMeta().containsKey("dataset")) {
                    return this.deleteDataEntity(objectId, UUID.fromString(meta.getMeta().get("dataset")));
                }
                return this.deleteDataEntity(objectId);
            }
        }
        catch (MetadataCannotBeAccessedException ex){
        }
        return false;
    }
}
