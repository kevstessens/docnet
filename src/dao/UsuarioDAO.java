package dao;

import hibernate.HibernateUtil;
import model.Usuario;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Kevin
 * Date: 23/03/12
 * Time: 08:45
 * To change this template use File | Settings | File Templates.
 */
public class UsuarioDAO extends BaseDAO {
    // cambie los query
    public static Usuario getUsuarioPorNombre(String nombre) {
        List<Usuario> userList = new ArrayList<Usuario>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Usuario where nombre = '"+ nombre +"'";
            userList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return userList.get(0);
    }
    public static Usuario getUsuarioPorApellido(String apellido) {
        List<Usuario> userList = new ArrayList<Usuario>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Usuario where apellido = '"+ apellido +"'";
            userList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return userList.get(0);
    }
    public static Usuario getUsuarioPorEmail(String email) {
        List<Usuario> userList = new ArrayList<Usuario>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Usuario where email = '"+ email +"'";
            userList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return userList.isEmpty() ? null : userList.get(0);
    }
    public static Usuario getUsuarioPorEmailInSession(String email) {
        List<Usuario> userList = new ArrayList<Usuario>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Usuario where email = '"+ email +"'";
            userList = session.createQuery(query).list();
        } finally {
            return userList.isEmpty() ? null : userList.get(0);
        }
    }
    public static void updatePassword(String nombre, String password) {
        List<Usuario> userList = new ArrayList<Usuario>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Usuario where nombre = '"+ nombre +"'";
            userList = session.createQuery(query).list();
        } finally {
            Usuario user = userList.get(0);
            user.setPassword(password);
            persist(user);
        }
    }
    public static boolean hayMail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Usuario where email = '"+ email +"'";
            return session.createQuery(query).list().size() > 0;
        } finally {
            session.close();
        }
    }
    public static boolean hayMailInSession(String email) {
        session = HibernateUtil.getSessionFactory().openSession();
        boolean b = false;
        try {
            String query = "from Usuario where email = '"+ email +"'";
            b = session.createQuery(query).list().size() > 0;
        } finally {
            return b;
        }
    }
    public static boolean hayDNI(String DNI) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Usuario where DNI = '"+ DNI +"'";
            return session.createQuery(query).list().size() > 0;
        } finally {
            session.close();
        }
    }
    public static List<Usuario> getAllUsers() {
        List<Usuario> userList = new ArrayList<Usuario>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Usuario";
            userList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return userList;
    }
}