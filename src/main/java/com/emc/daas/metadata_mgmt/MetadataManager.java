package com.emc.daas.metadata_mgmt;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.access_control.UserMetadataAccess;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeAccessedException;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Created by timofb on 11/26/2015.
 * TODO Add comments to make javadoc complete
 * TODO Add access control functionality 
 */
public abstract class MetadataManager {
    private UserAuthenticator authenticator;
    public MetadataManager(UserAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    public boolean putMeta(UUID id, Map<String, String> meta, String username) throws MetadataCannotBeChangedException {
        if (authenticator.hasAccess(username, UserMetadataAccess.CREATE, null)) {
            return this.putMetaToStorage(new DaaSMetadata(id, meta));
        }
        throw new MetadataCannotBeChangedException();
    }

    public DaaSMetadata getMeta(UUID id, String username) throws MetadataCannotBeAccessedException {
        DaaSMetadata meta = this.getMetaFromStorage(id);
        if (authenticator.hasAccess(username, UserMetadataAccess.READMETA, meta)) {
            return meta;
        }
        else {
            return cutMetadata(getMetaFromStorage(id));
        }
    }

    public Iterator<DaaSMetadata> searchDataSets(DaaSMetadata meta, String username) throws MetadataCannotBeAccessedException {
        return searchDataSetsInStorage(meta, username);
    }

    public Iterator<DaaSMetadata> searchDataSets(Predicate<DaaSMetadata> predicate, String username) throws MetadataCannotBeAccessedException {
        return searchDataSetsInStorage(predicate, username);
    }

    public boolean deleteMeta(UUID id, String username) throws MetadataCannotBeChangedException {
        DaaSMetadata meta = this.getMetaFromStorage(id);
        if (authenticator.hasAccess(username, UserMetadataAccess.FULL, meta)) {
            return this.deleteMetaFromStorage(id);
        }
        throw new MetadataCannotBeChangedException();
    }


    protected abstract DaaSMetadata cutMetadata(DaaSMetadata meta);
    protected abstract boolean putMetaToStorage(DaaSMetadata meta);
    protected abstract DaaSMetadata getMetaFromStorage(UUID id);

    protected abstract Iterator<DaaSMetadata> searchDataSetsInStorage(DaaSMetadata meta, String username);

    protected abstract Iterator<DaaSMetadata> searchDataSetsInStorage(Predicate<DaaSMetadata> meta, String username);

    protected abstract boolean deleteMetaFromStorage(UUID id) throws MetadataCannotBeChangedException;

}
