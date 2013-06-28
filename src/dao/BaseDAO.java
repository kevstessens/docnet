package dao;

import hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


public class BaseDAO {
    static Session session;
    public static void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    public static void closeSession() {
        session.close();
    }
    public static void persist(Object o) {
        try {
            session.saveOrUpdate(o);
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    public static void persistOutOfSession(Object o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.saveOrUpdate(o);
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    public static void delete(Object o) {
        try {
            session.delete(o);
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    public static void deleteOutOfSession(Object o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.delete(o);
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    public static boolean appendAnd(StringBuffer stringBuffer, boolean and) {
        if (and) {
            stringBuffer.append("and ");
        }
        return true;
    }
    public static <T> List<T> retrieveObject(T object, Query query) {
        List<T> list = new ArrayList<T>();
        try {
            list = query.list();
        } finally {
            return list;
        }
    }
}
