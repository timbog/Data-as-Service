package com.emc.daas.data;

import java.util.UUID;

/**
 * Created by timofb on 10/31/2015.
 */
public class DataEntity {
    protected UUID id;
    public DataEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
