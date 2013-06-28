package servlets;

import dao.DoctorDAO;
import dao.PacienteDAO;
import dao.TurnoDAO;
import model.Doctor;
import model.Turno;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 08/06/12
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "CancelarTurnosServlet")
public class CancelarTurnosServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String[] dates = request.getParameterValues("eliminar");
        if(dates!=null) {
            for(int i=0;i<dates.length;i++) {
                System.out.println(dates[i]);
                Turno turno = TurnoDAO.getTurnoPorFechaNum(dates[i].toString());
                Doctor doctor = DoctorDAO.getDoctorPorTurno(turno.getDateString(), turno.getNombreDoctor());
                DoctorDAO.deleteTurno((doctor).getDNI(), turno);
                String email_paciente = request.getRemoteUser();
                PacienteDAO.deleteTurno(email_paciente, turno);
                TurnoDAO.deleteOutOfSession(turno);
            }
        }
        response.sendRedirect("paciente.jsp");
    }
}