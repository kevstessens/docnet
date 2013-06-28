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
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "ModificarServlet")
public class ModificarServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaDeNacimiento = request.getParameter("fechaDeNacimiento");
        String obraSocial = request.getParameter("obraSocial");
        if (email.equalsIgnoreCase("")) {
            request.getRequestDispatcher("modificar.jsp").forward(request, response);
        } else {
            boolean existMail = UsuarioDAO.hayMailInSession(email);
            if (existMail) {
                Usuario usuario = UsuarioDAO.getUsuarioPorEmailInSession(email);
                if(!nombre.equalsIgnoreCase("")) {
                    usuario.setNombre(nombre);
                }
                if(!apellido.equalsIgnoreCase("")) {
                    usuario.setApellido(apellido);
                }
                if(!fechaDeNacimiento.equalsIgnoreCase("")) {
                    usuario.setFechaDeNacimiento(fechaDeNacimiento);
                }
                UsuarioDAO.persist(usuario);
                System.out.println("The modification was completed");
                response.sendRedirect("admin.jsp");
            } else {
                request.setAttribute("error", email);
                request.getRequestDispatcher("modificar.jsp").forward(request, response);
            }
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
