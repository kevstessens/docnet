package dao;

import hibernate.HibernateUtil;
import model.HistoriaClinica;
import model.Paciente;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/03/12
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class HistoriaClinicaDAO extends BaseDAO {
    public static HistoriaClinica getHCPorEyP(String especificacion, String nombre_paciente) {
        List<HistoriaClinica> hcList = new ArrayList<HistoriaClinica>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from HistoriaClinica where nombrePaciente = " + nombre_paciente + "' and especificacion = "+ especificacion +"'";
            hcList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return hcList.get(0);
    }
    public static List<HistoriaClinica> getHCPorPaciente(String DNI_paciente) {
        List<HistoriaClinica> hcList = new ArrayList<HistoriaClinica>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Paciente where DNI = '"+ DNI_paciente +"'";
            List<Paciente> pacList = session.createQuery(query).list();
            Paciente pac = pacList.get(0);
            hcList.addAll(pac.getHistoriasClinicas());
        } finally {
            session.close();
        }
        return hcList;
    }
}
