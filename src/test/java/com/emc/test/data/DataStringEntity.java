package com.emc.test.data;

import com.emc.daas.data.DataEntity;

import java.util.UUID;

/**
 * Created by timofb on 16-Mar-16.
 */
public class DataStringEntity extends DataEntity {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DataStringEntity(UUID id, String content) {
        this.id = id;
        this.content = content;
    }
}
