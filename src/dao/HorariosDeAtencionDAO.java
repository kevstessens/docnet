package dao;

import hibernate.HibernateUtil;
import model.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/03/12
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class HorariosDeAtencionDAO extends BaseDAO {
    public static List<HorariosDeAtencion> getHDAPorDoctor(String DNI_doctor) {
        List<HorariosDeAtencion> hdaList = new ArrayList<HorariosDeAtencion>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Doctor where DNI = '"+ DNI_doctor +"'";
            List<Doctor> docList = session.createQuery(query).list();
            Doctor doc = docList.get(0);
            hdaList.addAll(doc.getHorarios());
        } finally {
            session.close();
        }
        return hdaList;
    }
}
