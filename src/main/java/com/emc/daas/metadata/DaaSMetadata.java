package com.emc.daas.metadata;

import java.util.Map;
import java.util.UUID;

/**
 * Created by timofb on 10/31/2015.
 */
public class DaaSMetadata {
    private UUID UUID;
    private Map<String, String> meta;

    public DaaSMetadata(UUID UUID, Map<String, String> meta) {
        this.UUID = UUID;
        this.meta = meta;
    }

    public DaaSMetadata() {

    }

    public UUID getUUID() {
        return UUID;
    }

    public void setUUID(UUID UUID) {
        this.UUID = UUID;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }
}
