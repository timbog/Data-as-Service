package com.emc.daas.metadata_mgmt;

import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeAccessedException;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;

import java.util.Iterator;

/**
 * Created by timofb on 01-Dec-15.
 */
public interface MetadataRetriever {
    public boolean putMeta(String id, DaaSMetadata meta) throws MetadataCannotBeChangedException;
    public DaaSMetadata getMeta(String id) throws MetadataCannotBeAccessedException;
    /**
     * TODO this method shall return Iterator as list may be too large and inefficient
     * TODO review different scenario and add search predicate and/or query
     * TODO it may return not only DaaSMetadata but any subset defined by user, e.g. (id, name) or (id, name, user)
     * @param meta
     * @return
     */
    public Iterator<DaaSMetadata> searchDataSets(DaaSMetadata meta) throws MetadataCannotBeAccessedException;
    public boolean deleteMeta(String id) throws MetadataCannotBeChangedException;


}
