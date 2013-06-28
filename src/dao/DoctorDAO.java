package dao;

import hibernate.HibernateUtil;
import model.*;
import org.hibernate.Session;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/03/12
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
public class DoctorDAO extends BaseDAO {
    public static List<Doctor> getDoctorPorEspecificacion(String especificacion) {
        List<Doctor> docList = new ArrayList<Doctor>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Especificacion where nombre = '"+ especificacion +"'";
            List<Especificacion> esList = session.createQuery(query).list();
            Especificacion es = esList.get(0);
            docList.addAll(es.getDoctores());
        } finally {
            return docList;
        }
    }
    public static List<Doctor> getDoctorPorHospital(String nombre_hospital) {
        List<Doctor> docList = new ArrayList<Doctor>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Hospital where nombre = '"+ nombre_hospital +"'";
            List<Hospital> hospList = session.createQuery(query).list();
            Hospital hosp = hospList.get(0);
            docList.addAll(hosp.getDoctores());
        } finally {
            return docList;
        }
    }
    public static List<Doctor> getDoctorPorEyH(String especificacion, String hospital) {
        List<String> dList = new ArrayList<String>();
        List<Doctor> dList2 = new ArrayList<Doctor>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String q = "select d.DNI from Especificacion as e inner join e.doctores as d inner join d.hospitales as h where e.nombre = '"+
                    especificacion+ "' and h.nombre = '" +hospital+ "'";
            dList = session.createQuery(q).list();
            for(int i=0;i<dList.size();i++) {
                String dni = dList.get(i);
                Doctor d = getDoctorPorDNI(dni);
                dList2.add(d);
            }
        } finally {
            return dList2;
        }
    }
    public static List<Doctor> getDoctorPorOS(String nombre_obraSocial) {
        List<Doctor> docList = new ArrayList<Doctor>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from ObraSocial where nombre = '"+ nombre_obraSocial +"'";
            List<ObraSocial> osList = session.createQuery(query).list();
            ObraSocial os = osList.get(0);
            docList.addAll(os.getDoctores());
        } finally {
            return docList;
        }
    }
    public static Doctor getDoctorPorDNI(String dni) {
        Doctor d = new Doctor();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Doctor where DNI = '"+ dni +"'";
            List<Doctor> dList = session.createQuery(query).list();
            d = dList.get(0);
        } finally {
            return d;
        }
    }
    public static Doctor getDoctorPorTurno(String dateString, String nombreDoctor) {
        List<String> dList = new ArrayList<String>();
        Doctor d = new Doctor();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "select d.DNI from Doctor as d inner join d.turnos as t where t.dateString = '"+ dateString +"' and t.nombreDoctor = '" + nombreDoctor + "'";
            dList = session.createQuery(query).list();
            d = getDoctorPorDNI(dList.get(0));
        } finally {
            return d;
        }
    }
    public static List<Doctor> getAllDoctors() {
        List<Doctor> docList = new ArrayList<Doctor>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Doctor";
            docList = session.createQuery(query).list();
        } finally {
            return docList;
        }
    }
    public static void addTurno(String doctor, Turno turno) {
        List<Doctor> doctores = new ArrayList<Doctor>();
        session = HibernateUtil.getSessionFactory().openSession();
        Doctor d = new Doctor();
        try {
            String query = "from Doctor where DNI = '"+ doctor +"'";
            doctores = session.createQuery(query).list();
            d = doctores.get(0);
            List<Turno> t = d.getTurnos();
            t.add(turno);
            d.setTurnos(t);
        } finally {
            persist(d);
        }
    }
    public static void deleteTurno(String doctor, Turno turno) {
        List<Doctor> doctores = new ArrayList<Doctor>();
        session = HibernateUtil.getSessionFactory().openSession();
        Doctor d = new Doctor();
        try {
            String query = "from Doctor where DNI = '"+ doctor +"'";
            doctores = session.createQuery(query).list();
            d = doctores.get(0);
            List<Turno> t = d.getTurnos();
            for(int i=0;i<t.size();i++) {
                if((t.get(i).getDateString()).equals(turno.getDateString())) {
                    t.remove(i);
                    i--;
                }
            }
            d.setTurnos(t);
        } finally {
            persist(d);
        }
    }
}