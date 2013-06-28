package dao;

import hibernate.HibernateUtil;
import model.Doctor;
import model.Paciente;
import model.Turno;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/03/12
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class PacienteDAO extends BaseDAO {
    public static List<Paciente> getPacientePorRankingMayor(long limite) {
        List<Paciente> pacList = new ArrayList<Paciente>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from Paciente where ranking >= '"+ limite +"'";
            pacList = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return pacList;
    }
    public static Paciente getPacientePorTurno(String dateString, String nombrePaciente) {
        List<String> pList = new ArrayList<String>();
        Paciente p = new Paciente();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "select p.email from Paciente as p inner join p.turnos as t where t.dateString = '"+ dateString +"' and t.nombrePaciente = '" + nombrePaciente + "'";
            pList = session.createQuery(query).list();
            p = (Paciente)UsuarioDAO.getUsuarioPorEmailInSession(pList.get(0));
        } finally {
            return p;
        }
    }
    public static void addTurno(String paciente, Turno turno) {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        session = HibernateUtil.getSessionFactory().openSession();
        Paciente p = new Paciente();
        try {
            String query = "from Paciente where email = '"+ paciente +"'";
            pacientes = session.createQuery(query).list();
            p = pacientes.get(0);
            List<Turno> t = p.getTurnos();
            t.add(turno);
            p.setTurnos(t);
        } finally {
            persist(p);
        }
    }
    public static void deleteTurno(String paciente, Turno turno) {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        session = HibernateUtil.getSessionFactory().openSession();
        Paciente p = new Paciente();
        try {
            String query = "from Paciente where email = '"+ paciente +"'";
            pacientes = session.createQuery(query).list();
            p = pacientes.get(0);
            List<Turno> t = p.getTurnos();
            for(int i=0;i<t.size();i++) {
                if((t.get(i).getDateString()).equals(turno.getDateString())) {
                    t.remove(i);
                    i--;
                }
            }
            p.setTurnos(t);
        } finally {
            persist(p);
        }
    }
}
