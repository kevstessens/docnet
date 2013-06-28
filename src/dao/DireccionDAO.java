package dao;

import hibernate.HibernateUtil;
import model.Direccion;
import model.Hospital;
import model.Usuario;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/03/12
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
public class DireccionDAO extends BaseDAO {
    public static Direccion getDireccionPorPais(String pais) {
        List<Direccion> dirList = new ArrayList<Direccion>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Direccion where pais = '"+ pais +"'";
            dirList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return dirList.get(0);
    }
    public static Direccion getDireccionPorProvincia(String provincia) {
        List<Direccion> dirList = new ArrayList<Direccion>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Direccion where provincia = '"+ provincia +"'";
            dirList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return dirList.get(0);
    }
    public static Direccion getDireccionPorCiudad(String ciudad) {
        List<Direccion> dirList = new ArrayList<Direccion>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Direccion where ciudad = '"+ ciudad +"'";
            dirList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return dirList.get(0);
    }
    public static Direccion getDireccionPorUsuario(String DNI_usuario) {
        Direccion dir;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Usuario where DNI = '"+ DNI_usuario +"'";
            List<Usuario> userList = session.createQuery(query).list();
            Usuario user = userList.get(0);
            dir = user.getDireccion();
        } finally {
            session.close();
        }
        return dir;
    }
    public static Direccion getDireccionPorHospital(String nombre_hospital) {
        Direccion dir;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Hospital where nombre = '"+ nombre_hospital +"'";
            List<Hospital> hospList = session.createQuery(query).list();
            Hospital hosp = hospList.get(0);
            dir = hosp.getDireccion();
        } finally {
            session.close();
        }
        return dir;
    }

}
