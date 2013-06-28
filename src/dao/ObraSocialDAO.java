package dao;

import hibernate.HibernateUtil;
import model.Doctor;
import model.ObraSocial;
import model.Paciente;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/03/12
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class ObraSocialDAO extends BaseDAO {
    // para un getOSPorPaciente habria q agregar en Paciente @ManyToOne ObraSocial
    public static ObraSocial getOSPorNombre(String nombre) {
        List<ObraSocial> osList = new ArrayList<ObraSocial>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from ObraSocial where nombre = '"+ nombre +"'";
            osList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return osList.get(0);
    }
    public static ObraSocial getOSPorNombreInSession(String nombre) {
        List<ObraSocial> osList = new ArrayList<ObraSocial>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from ObraSocial where nombre = '"+ nombre +"'";
            osList = session.createQuery(query).list();
        } finally {
            return osList.get(0);
        }
    }
    public static List<ObraSocial> getOSPorDoctor(String DNI_doctor) {
        List<ObraSocial> osList = new ArrayList<ObraSocial>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Doctor where DNI = '"+ DNI_doctor +"'";
            List<Doctor> docList = session.createQuery(query).list();
            Doctor doc = docList.get(0);
            osList.addAll(doc.getObrasSociales());
        } finally {
            session.close();
        }
        return osList;
    }
    public static void addDoctor(String nombre, Doctor doctor) {
        List<ObraSocial> obrasSociales = new ArrayList<ObraSocial>();
        session = HibernateUtil.getSessionFactory().openSession();
        ObraSocial os = new ObraSocial();
        try {
            String query = "from ObraSocial where nombre = '"+ nombre +"'";
            obrasSociales = session.createQuery(query).list();
            os = obrasSociales.get(0);
            List<Doctor> d = os.getDoctores();
            d.add(doctor);
            os.setDoctores(d);
        } finally {
            persist(os);
        }
    }
}
