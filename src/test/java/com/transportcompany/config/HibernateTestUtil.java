package com.transportcompany.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import static com.transportcompany.config.HibernateTx.inTx;

public class HibernateTestUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration config = new Configuration();
            config.configure("hibernate-test.cfg.xml");
            return config.buildSessionFactory(
                    new StandardServiceRegistryBuilder()
                            .applySettings(config.getProperties())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to build TEST SessionFactory", e);
        }
    }
    }


