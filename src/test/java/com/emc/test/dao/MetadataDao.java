package com.emc.test.dao;

import com.emc.test.entity.MetadataEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by timofb on 27-Jan-16.
 */
@Repository
public class MetadataDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void putMetadata(final MetadataEntity metadataEntity) {
        final Session session = this.sessionFactory.openSession();
        try {
            session.save(metadataEntity);
            session.flush();
        } finally {
            session.close();
        }
    }

    public List<MetadataEntity> getMetaById(final String metaId) {
        final Session session = sessionFactory.openSession();
        try {
            Criteria crit = session.createCriteria(MetadataEntity.class);
            crit.add(Restrictions.eq("uuid",metaId));
            return crit.list();
        } finally {
            session.close();
        }
    }

    public void cleanAll() {
        final Session session = sessionFactory.openSession();
        try {
            session.createQuery("delete from MetadataEntity").executeUpdate();
            session.flush();
        } finally {
            session.close();
        }
    }


}
