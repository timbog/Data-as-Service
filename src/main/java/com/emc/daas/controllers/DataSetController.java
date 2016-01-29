package com.emc.daas.controllers;

import com.emc.daas.data_mgmt.DataManager;
import com.emc.daas.metadata.DaaSMetadata;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by timofb on 11/12/2015.
 */
@RestController
@Component
@Secured("ROLE_DATA_STEWARD")
@RequestMapping("/datasets")
public class DataSetController {
    @Autowired
    DataManager manager;

    @ApiOperation(value = "Create dataset")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createDataSet(@RequestBody DaaSMetadata meta) {
        return manager.createDataSet(meta);
    }

    @RequestMapping(value = "/{dataSetId}", method = RequestMethod.DELETE)
    public boolean deleteDataSet(@PathVariable String dataSetId) {
        return manager.deleteDataEntity(dataSetId);
    }
}
