package com.emc.test.controllers;

import com.emc.test.dao.MetadataDao;
import com.emc.test.config.TestApplication;
import com.emc.test.entity.MetadataEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by timofb on 27-Jan-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
public class DBTest {


    @Autowired
    public MetadataDao dao;

    @Before
    public void setUp() {

        MetadataEntity metaEntity = new MetadataEntity();
        metaEntity.setUuid("ccc");
        metaEntity.setName("address");
        metaEntity.setValue("Saint-");
        ///dao.getMetaById("ccc")
        //System.out.println(dao.getMetaById("ccc").getValue());
        //dao.putMetadata(metaEntity);
        //dao.putMetaUnit(entity);
        //int a = 5;
    }

    @Test
    public void test(){

    }

}
