package com.transportcompany.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySetting("hibernate.connection.url",
                            "jdbc:mysql://localhost:3306/transport_company_app?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC")
                    .applySetting("hibernate.connection.username", "root")
                    .applySetting("hibernate.connection.password", "Abob140104")
                    .applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .applySetting("hibernate.hbm2ddl.auto", "update")
                    .applySetting("hibernate.show_sql", "true")
                    .applySetting("hibernate.format_sql", "true")
                    .build();

            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClass(com.transportcompany.entity.TransportCompany.class)
                    .addAnnotatedClass(com.transportcompany.entity.Client.class)
                    .addAnnotatedClass(com.transportcompany.entity.Vehicle.class)
                    .addAnnotatedClass(com.transportcompany.entity.DriverQualification.class)
                    .addAnnotatedClass(com.transportcompany.entity.Employee.class)
                    .addAnnotatedClass(com.transportcompany.entity.Transport.class)
                    .addAnnotatedClass(com.transportcompany.entity.Price.class)
                    .addAnnotatedClass(com.transportcompany.entity.Ticket.class);

            return sources.buildMetadata().buildSessionFactory();

        } catch (Exception e) {
            throw new ExceptionInInitializerError("SessionFactory creation failed: " + e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
