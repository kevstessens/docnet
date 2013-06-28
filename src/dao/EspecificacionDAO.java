package dao;

import hibernate.HibernateUtil;
import model.Doctor;
import model.Especificacion;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 25/04/12
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
public class EspecificacionDAO extends BaseDAO {
    public static Especificacion getEspecificacionPorNombre(String nombre) {
        List<Especificacion> esList = new ArrayList<Especificacion>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Especificacion where nombre = '"+ nombre +"'";
            esList = session.createQuery(query).list();
        } finally {
            return esList.get(0);
        }
    }
    public static Especificacion getEspecificacionPorDoctor(String DNI_doctor) {
        List<Especificacion> esList = new ArrayList<Especificacion>();
        session = HibernateUtil.getSessionFactory().openSession();
        Especificacion e = new Especificacion();
        try {
            String query = "Select e from Especificacion as e inner join e.doctores as d where d.DNI = '"+ DNI_doctor +"'";
            esList = session.createQuery(query).list();
            e = esList.get(0);
        } finally {
            return e;
        }
    }
    public static void addDoctor(String nombre, Doctor doctor) {
        List<Especificacion> especificacion = new ArrayList<Especificacion>();
        session = HibernateUtil.getSessionFactory().openSession();
        Especificacion e = new Especificacion();
        try {
            String query = "from Especificacion where nombre = '"+ nombre +"'";
            especificacion = session.createQuery(query).list();
            e = especificacion.get(0);
            List<Doctor> d = e.getDoctores();
            d.add(doctor);
            e.setDoctores(d);
        } finally {
            persist(e);
        }
    }

}
