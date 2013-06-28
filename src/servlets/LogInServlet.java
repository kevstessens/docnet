package servlets;

import dao.BaseDAO;
import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 13/04/12
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "LogInServlet")
public class LogInServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getRemoteUser();
        Usuario usuario = UsuarioDAO.getUsuarioPorEmail(email);
        BaseDAO.openSession();
        if(usuario.isAdmin()) {
            response.sendRedirect("admin.jsp");
        } else if(usuario.isDoctor()) {
            response.sendRedirect("doctor.jsp");
        } else {
            response.sendRedirect("paciente.jsp");
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
