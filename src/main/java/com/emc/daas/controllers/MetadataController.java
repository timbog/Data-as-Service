package com.emc.daas.controllers;

import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeAccessedException;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;
import com.emc.daas.metadata_mgmt.MetadataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Created by timofb on 11/26/2015.
 */
@RestController
@Component
@Secured({"ROLE_USER", "ROLE_DATA_STEWARD"})
@RequestMapping("/meta")
public class MetadataController {

    @Autowired
    MetadataManager manager;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public boolean putMeta(@PathVariable String id, @RequestBody HashMap<String,String> meta) {
        try {
            return manager.putMeta(UUID.fromString(id), meta, getCurrentUserName());
        } catch (MetadataCannotBeChangedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Iterator<DaaSMetadata> searchDataSets(@RequestBody DaaSMetadata meta) {
        try {
            return manager.searchDataSets(meta, getCurrentUserName());
        } catch (MetadataCannotBeAccessedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/search/predicate", method = RequestMethod.POST)
    public Iterator<DaaSMetadata> searchDataSets(@RequestBody Predicate<DaaSMetadata> predicate) {
        try {
            return manager.searchDataSets(predicate, getCurrentUserName());
        } catch (MetadataCannotBeAccessedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DaaSMetadata getMetaData(@PathVariable String id) {
        try {
            return manager.getMeta(UUID.fromString(id), getCurrentUserName());
        } catch (MetadataCannotBeAccessedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteMetaData(@PathVariable String id) {
        try {
            return manager.deleteMeta(UUID.fromString(id), getCurrentUserName());
        } catch (MetadataCannotBeChangedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }



}

