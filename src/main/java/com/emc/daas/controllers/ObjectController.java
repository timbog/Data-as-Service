package com.emc.daas.controllers;

import com.emc.daas.data.DaaSObject;
import com.emc.daas.data_mgmt.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by timofb on 11/5/2015.
 */
@RestController
@Component
@Secured({"ROLE_USER", "ROLE_DATA_STEWARD"})
@RequestMapping("/objects")
public class ObjectController {

    @Autowired
    DataManager manager;

    @RequestMapping(value = "/{dataSetId}", method = RequestMethod.POST)
    public String putObject(@PathVariable String dataSetId, @RequestBody DaaSObject data) {
        return manager.putObject(dataSetId, data, getCurrentUserName());
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.DELETE)
    public boolean deleteObject(@PathVariable String objectId) {
        return manager.deleteDataEntity(objectId, getCurrentUserName());
    }

    private String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
