package com.emc.daas.metadata_mgmt;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeAccessedException;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;

import java.util.Iterator;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Created by timofb on 11/26/2015.
 */
public class MetaDataManagerMock extends MetadataManager {
    public MetaDataManagerMock(UserAuthenticator authenticator) {
        super(authenticator);
    }

    @Override
    public Iterator<DaaSMetadata> searchDataSetsInStorage(Predicate<DaaSMetadata> predicate, String username) {
        return null;
    }

    @Override
    public DaaSMetadata getMeta(UUID id, String username) throws MetadataCannotBeAccessedException {
        return super.getMeta(id, username);
    }

    @Override
    public Iterator<DaaSMetadata> searchDataSets(DaaSMetadata meta, String username) throws MetadataCannotBeAccessedException {
        return super.searchDataSets(meta, username);
    }

    @Override
    public boolean deleteMeta(UUID id, String username) throws MetadataCannotBeChangedException {
        return super.deleteMeta(id, username);
    }

    @Override
    protected DaaSMetadata cutMetadata(DaaSMetadata meta) {
        return null;
    }

    @Override
    protected boolean putMetaToStorage(DaaSMetadata meta) {
        return false;
    }

    @Override
    protected DaaSMetadata getMetaFromStorage(UUID id) {
        return null;
    }

    @Override
    protected Iterator<DaaSMetadata> searchDataSetsInStorage(DaaSMetadata meta, String username) {
        return null;
    }

    @Override
    protected boolean deleteMetaFromStorage(UUID id) throws MetadataCannotBeChangedException {
        return false;
    }
}
