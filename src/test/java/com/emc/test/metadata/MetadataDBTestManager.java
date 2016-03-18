package com.emc.test.metadata;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeAccessedException;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;
import com.emc.daas.metadata_mgmt.MetadataManager;
import com.emc.test.dao.MetadataDao;
import com.emc.test.entity.MetadataEntity;

import java.util.*;
import java.util.function.Predicate;


public class MetadataDBTestManager extends MetadataManager {

    private MetadataDao metadataDao;

    public MetadataDBTestManager(UserAuthenticator authenticator) {
        super(authenticator);
    }

    public MetadataDBTestManager(UserAuthenticator authenticator, MetadataDao metadataDao) {
        super(authenticator);
        this.metadataDao = metadataDao;
    }

    public MetadataDao getMetadataDao() {
        return metadataDao;
    }

    public void setMetadataDao(MetadataDao metadataDao) {
        this.metadataDao = metadataDao;
    }

    @Override
    protected DaaSMetadata getMetaFromStorage(final UUID id) {
        List<MetadataEntity> meta =  metadataDao.getMetaById(id.toString());
        Map<String, String> metaMap = new HashMap<String, String>();
        for (MetadataEntity entity: meta) {
            metaMap.put(entity.getName(), entity.getValue());
        }
        DaaSMetadata metadata = new DaaSMetadata(id, metaMap);
        return metadata;

    }

    @Override
    protected boolean putMetaToStorage(DaaSMetadata meta) {
        List<String> keys = new ArrayList<String>();
        List<MetadataEntity> entityList = new ArrayList<MetadataEntity>();
        Map<String, String> metaMap = meta.getMeta();
        keys.addAll(metaMap.keySet());
        for (String key: keys) {
            MetadataEntity entity = new MetadataEntity();
            entity.setUuid(meta.getUUID().toString());
            entity.setName(key);
            entity.setValue(metaMap.get(key));
            entity.setId(0);
            metadataDao.putMetadata(entity);
        }
        return true;
    }

    @Override
    protected DaaSMetadata cutMetadata(DaaSMetadata meta) {
        HashMap<String, String> newMetaList = new HashMap<String, String>();
        newMetaList.put("admin_email", meta.getMeta().get("admin_email"));
        return new DaaSMetadata(meta.getUUID(), newMetaList);
    }

    @Override
    protected Iterator<DaaSMetadata> searchDataSetsInStorage(DaaSMetadata meta, String username)  {
        return null;
    }

    @Override
    protected Iterator<DaaSMetadata> searchDataSetsInStorage(Predicate<DaaSMetadata> meta, String username)  {
        return null;
    }

    @Override
    protected boolean deleteMetaFromStorage(final UUID id) throws MetadataCannotBeChangedException {
        return true;
    }

    public void cleanData() {
        metadataDao.cleanAll();
    }

    public UserAuthenticator getUserAuthenticator(){
        return this.authenticator;
    }
}
