package servlets;

import dao.ObraSocialDAO;
import dao.PacienteDAO;
import dao.UsuarioDAO;
import model.ObraSocial;
import model.Paciente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 23/03/12
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String DNI = (request.getParameter("DNI"));
        String fechaDeNacimiento = request.getParameter("fechaDeNacimiento");
        String obraSocial = request.getParameter("obraSocial");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (apellido.equalsIgnoreCase("") || nombre.equalsIgnoreCase("") || password.equalsIgnoreCase("") || email.equalsIgnoreCase("")
                || DNI.equalsIgnoreCase("") || fechaDeNacimiento.equalsIgnoreCase("") || obraSocial.equalsIgnoreCase("")) {
            request.getRequestDispatcher("registrate.jsp").forward(request, response);
        } else {
            boolean existDNI = UsuarioDAO.hayDNI(DNI);
            boolean existMail = UsuarioDAO.hayMail(email);
            if (!(existMail || existDNI)) {
                Paciente paciente = new Paciente();
                paciente.setNombre(nombre);
                paciente.setApellido(apellido);
                paciente.setEmail(email);
                paciente.setPassword(password);
                paciente.setDNI(DNI);
                paciente.setFechaDeNacimiento(fechaDeNacimiento);
                paciente.setObraSocial(ObraSocialDAO.getOSPorNombre(obraSocial));
                UsuarioDAO.persistOutOfSession(paciente);
                System.out.println("The registration was completed");
                response.sendRedirect("ingresa.jsp");
            } else {
                if (existDNI) {
                    request.setAttribute("nameExist", DNI);
                }
                if (existMail) {
                    System.out.println("existMail = " + existMail);
                    request.setAttribute("mailExist", email);
                }
                request.getRequestDispatcher("registrate.jsp").forward(request, response);
            }
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}