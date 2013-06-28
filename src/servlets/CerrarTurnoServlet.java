package servlets;

import dao.*;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 14/06/12
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "CerrarTurnoServlet")
public class CerrarTurnoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Doctor doctor = (Doctor)session.getAttribute("usuario");
        final String d = request.getParameter("date");
        Date date = new Date(Long.valueOf(d));
        String dateString = date.toString();
        Turno turno = TurnoDAO.getTurnoPorFechaText(dateString);
        session.setAttribute("turno", turno);
        if(turno.getNombrePaciente()!=null) {
            Paciente paciente = PacienteDAO.getPacientePorTurno(turno.getDateString(), turno.getNombrePaciente());
            session.setAttribute("paciente", paciente);
            String email_paciente = paciente.getEmail();
            PacienteDAO.deleteTurno(email_paciente, turno);
        }
        DoctorDAO.deleteTurno((doctor).getDNI(), turno);
        TurnoDAO.deleteOutOfSession(turno);
        response.sendRedirect("cerrarTurno.jsp");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}