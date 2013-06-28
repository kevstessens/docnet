package servlets;

import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/04/12
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "EliminarServlet")
public class EliminarServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email.equalsIgnoreCase("")) {
            request.getRequestDispatcher("eliminar.jsp").forward(request, response);
        } else {
            boolean existMail = UsuarioDAO.hayMailInSession(email);
            if (existMail) {
                Usuario usuario = UsuarioDAO.getUsuarioPorEmailInSession(email);
                UsuarioDAO.delete(usuario);
                System.out.println("The delete was completed");
                response.sendRedirect("admin.jsp");
            } else {
                request.setAttribute("error", email);
                request.getRequestDispatcher("eliminar.jsp").forward(request, response);
            }
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
