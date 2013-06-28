package dao;

import hibernate.HibernateUtil;
import model.Doctor;
import model.ObraSocial;
import model.Paciente;
import model.Turno;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/03/12
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class TurnoDAO extends BaseDAO {
    public static Turno getTurnoPorFechaNum(String dateString) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
        Date date;
        List<Turno> turList = new ArrayList<Turno>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            date = formato.parse(dateString);
            dateString = date.toString();
            String query = "from Turno where dateString = '"+ dateString +"'";
            turList = session.createQuery(query).list();
        } catch(ParseException e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return turList.get(0);
    }
    public static Turno getTurnoPorFechaText(String dateString) {
        Date date;
        List<Turno> turList = new ArrayList<Turno>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Turno where dateString = '"+ dateString +"'";
            turList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return turList.get(0);
    }
    public static List<Turno> getTurnosPorDoctor(String DNI_doctor) {
        List<Turno> tList = new ArrayList<Turno>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Doctor where DNI = '"+ DNI_doctor +"'";
            List<Doctor> docList = session.createQuery(query).list();
            Doctor doc = docList.get(0);
            tList.addAll(doc.getTurnos());
        } finally {
            session.close();
        }
        return tList;
    }
    public static List<Turno> getTurnosPorPaciente(String email_paciente) {
        List<Turno> tList = new ArrayList<Turno>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Paciente where email = '"+ email_paciente +"'";
            List<Paciente> pacList = session.createQuery(query).list();
            Paciente pac = pacList.get(0);
            tList.addAll(pac.getTurnos());
        } finally {
            session.close();
        }
        return tList;
    }
    public static String toJsonString(List<Turno> list, final int turnoDurationMinutes) {
        String json = "";
        int id = 0;
        for (Turno turno : list) {
            final Date startTime = turno.getDate();
            final String startTimeStr = JSON_DATE_FORMAT.format(startTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            calendar.add(Calendar.MINUTE, turnoDurationMinutes);
            final String endTimeStr = JSON_DATE_FORMAT.format(calendar.getTime());
            json = json+"{\"id\":"+id+++", \"start\": \""+startTimeStr+"\", \"end\": \""+endTimeStr+"\",\"title\":\"Ocupado\"},";
        }
        if(json.length() > 1)
        {
            json = json.substring(0, json.length()-1);
        }
        return json;
    }
    public static String toJsonStringDoctor(List<Turno> list, final int turnoDurationMinutes) {
        String json = "";
        int id = 0;
        for (Turno turno : list) {
            final Date startTime = turno.getDate();
            final String startTimeStr = JSON_DATE_FORMAT.format(startTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            calendar.add(Calendar.MINUTE, turnoDurationMinutes);
            final String endTimeStr = JSON_DATE_FORMAT.format(calendar.getTime());
            if(turno.getNombrePaciente()==null) {
            json = json+"{\"id\":"+id+++", \"start\": \""+startTimeStr+"\", \"end\": \""+endTimeStr+"\",\"title\":\"No Disponible\"},";
            } else {
            json = json+"{\"id\":"+id+++", \"start\": \""+startTimeStr+"\", \"end\": \""+endTimeStr+"\",\"title\":\""+turno.getNombrePaciente()+"\"},";
            }
        }
        if(json.length() > 1)
        {
            json = json.substring(0, json.length()-1);
        }
        return json;
    }
    private static final SimpleDateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
}