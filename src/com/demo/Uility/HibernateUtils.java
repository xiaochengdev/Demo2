package com.demo.Uility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final Configuration configuration;
    private static  final SessionFactory factory;
    static {
        configuration=new Configuration().configure();
        factory=configuration.buildSessionFactory();
    }

    public static Session getSession(){
        return factory.getCurrentSession();
    }
}
