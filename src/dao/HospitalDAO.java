package dao;

import hibernate.HibernateUtil;
import model.Doctor;
import model.Hospital;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/03/12
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class HospitalDAO extends BaseDAO {
    // getHospitalPorCiudad,Provincia,Pais nose si es necesario porq ya estan en DireccionDAO
    public static Hospital getHospitalPorNombre(String nombre) {
        List<Hospital> hospList = new ArrayList<Hospital>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Hospital where nombre = '"+ nombre +"'";
            hospList = session.createQuery(query).list();
        } finally {
            return hospList.get(0);
        }
    }
    public static List<Hospital> getHospitalPorDoctor(String DNI_doctor) {
        List<Hospital> hospList = new ArrayList<Hospital>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Doctor where DNI = '"+ DNI_doctor +"'";
            List<Doctor> docList = session.createQuery(query).list();
            Doctor doc = docList.get(0);
            hospList.addAll(doc.getHospitales());
        } finally {
            return hospList;
        }
    }
    public static void addDoctor(String nombre, Doctor doctor) {
        List<Hospital> hospitales = new ArrayList<Hospital>();
        session = HibernateUtil.getSessionFactory().openSession();
        Hospital h = new Hospital();
        try {
            String query = "from Hospital where nombre = '"+ nombre +"'";
            hospitales = session.createQuery(query).list();
            h = hospitales.get(0);
            List<Doctor> d = h.getDoctores();
            d.add(doctor);
            h.setDoctores(d);
        } finally {
            persist(h);
        }
    }
}
