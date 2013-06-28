package servlets;

import dao.*;
import model.*;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 26/04/12
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "SacarTurnoServlet")
public class SacarTurnoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        if(!usuario.isDoctor()) {
            final String d = request.getParameter("date");
            final Date date = new Date(Long.valueOf(d));
            Doctor doctor = (Doctor)session.getAttribute("doctor");
            Paciente paciente = (Paciente)UsuarioDAO.getUsuarioPorEmail(request.getRemoteUser());
            Turno turno = new Turno();
            turno.setDate(date);
            Especificacion e = EspecificacionDAO.getEspecificacionPorDoctor(doctor.getDNI());
            String especificacion = e.getNombre();
            turno.setEspecializacion(especificacion);
            String nombreDoctor = doctor.getNombre()+" "+doctor.getApellido();
            turno.setNombreDoctor(nombreDoctor);
            String nombrePaciente = paciente.getNombre()+" "+paciente.getApellido();
            turno.setNombrePaciente(nombrePaciente);
            TurnoDAO.persist(turno);
            DoctorDAO.addTurno(doctor.getDNI(), turno);
            String email_paciente = request.getRemoteUser();
            PacienteDAO.addTurno(email_paciente, turno);
            System.out.print(" =========> " + DateFormat.getInstance().format(date) + date.toString());
            response.getWriter().print("Su turno se ha guardado correctamente, puede revisar o cancelar sus turnos desde el panel de usuario");
            response.getWriter().flush();
        } else if(usuario.isDoctor()) {
            final String d = request.getParameter("date");
            final Date date = new Date(Long.valueOf(d));
            Turno turno = new Turno();
            turno.setDate(date);
            String nombreDoctor = usuario.getNombre()+" "+usuario.getApellido();
            turno.setNombreDoctor(nombreDoctor);
            TurnoDAO.persist(turno);
            DoctorDAO.addTurno(usuario.getDNI(), turno);
            response.getWriter().print("Se ha cancelado la disponibilidad de este turno");
            response.getWriter().flush();
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
