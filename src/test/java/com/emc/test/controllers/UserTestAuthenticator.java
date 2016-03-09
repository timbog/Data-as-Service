package com.emc.test.controllers;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.access_control.UserAccessMode;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.MetadataManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Created by timofb on 18-Dec-15.
 */
public class UserTestAuthenticator implements UserAuthenticator{
    private MetadataManager manager;

    @Override
    public boolean hasAccess(String username, UserAccessMode access, DaaSMetadata meta) {
        //In test case it is supposed that any user can create meta
        if (access.equals(UserAccessMode.CREATE)) {
            return true;
        }
        String json = meta.getMeta().get("ACL");
        Map<String, String> myMap = (new Gson()).fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
        if (!myMap.containsKey(username)) {
            return false;
        }
        if (UserAccessMode.valueOf(myMap.get(username)).ordinal() >= UserAccessMode.valueOf(access.name()).ordinal()) {
            return true;
        }
        return false;
    }

    public void setManager(MetadataManager manager) {
        this.manager = manager;
    }


}
