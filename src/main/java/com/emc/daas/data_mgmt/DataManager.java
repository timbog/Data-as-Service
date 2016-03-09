package com.emc.daas.data_mgmt;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.access_control.UserAccessMode;
import com.emc.daas.data.DataEntity;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.MetadataManager;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeAccessedException;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;

import java.util.HashMap;
import java.util.Iterator;
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

    protected abstract void createDataSet(DaaSMetadata metadata);

    protected abstract void putObjectToStorage(String datasetId, DataEntity object);

    protected abstract boolean deleteDataEntity(String objectId);

    public String putObject(String datasetId, DataEntity object, String username) {
        if (authenticator.hasAccess(username, UserAccessMode.CREATE, null)) {
            try {
                final UUID newId = UUID.randomUUID();
                metadataManager.putMeta(newId, new HashMap<String, String>(), username);
                this.putObjectToStorage(datasetId, object);
                return newId.toString();
            }
            catch (MetadataCannotBeChangedException ex) {
            }
        }
        return null;
    }

    public String createDataSet(DaaSMetadata metadata, String username) {
        if (authenticator.hasAccess(username, UserAccessMode.CREATE, null)) {
            try {
                this.createDataSet(metadata);
                final UUID newId = UUID.randomUUID();
                metadataManager.putMeta(UUID.randomUUID(), new HashMap<String, String>(), username);
                return newId.toString();
            }
            catch (MetadataCannotBeChangedException ex) {
            }
        }
        return null;
    }

    public boolean deleteDataEntity(String objectId, String username) {
        if (authenticator.hasAccess(username, UserAccessMode.FULL, null)) {
            return this.deleteDataEntity(objectId);
        }
        return false;
    }
}
