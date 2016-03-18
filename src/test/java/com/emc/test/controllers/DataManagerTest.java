package com.emc.test.controllers;

import com.emc.daas.access_control.UserAccessMode;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;
import com.emc.test.config.TestApplication;
import com.emc.test.data.DataStringEntity;
import com.emc.test.data.DataTestManager;
import com.emc.test.metadata.MetadataDBTestManager;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Created by timofb on 16-Mar-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
public class DataManagerTest extends ManagerTest{

    @Autowired
    private DataTestManager dataManager;

    @Before
    public void setUp() {
        this.config();
        dataManager.createDataSet(new DaaSMetadata(testId, new HashMap<String, String>()), adminUserName);
    }

    @Test
    public void testPutToStorage() {
        String content = "test";
        UUID newUUID = UUID.randomUUID();
        dataManager.putObject(testId.toString(), new DataStringEntity(newUUID,content), adminUserName);
        assertTrue(new File(new File(System.getProperty("java.io.tmpdir"), testId.toString()).getAbsolutePath(), newUUID.toString()).exists());
        assertTrue(dataManager.deleteDataEntity(newUUID, adminUserName));
    }

    @After
    public void tearDown() {
        dataManager.deleteDataEntity(testId, adminUserName);
        metadataManager.cleanData();
    }
}
