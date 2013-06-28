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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 26/04/12
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "BuscarTurnosServlet")
public class BuscarTurnosServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String especificacion = request.getParameter("especificacion");
        String hospital = request.getParameter("hospital");
        if(especificacion.equalsIgnoreCase("*") && hospital.equalsIgnoreCase("*")) {
            request.setAttribute("Ok", DoctorDAO.getAllDoctors());
        } else if(hospital.equalsIgnoreCase("*")) {
            request.setAttribute("Ok", DoctorDAO.getDoctorPorEspecificacion(especificacion));
        } else if(especificacion.equalsIgnoreCase("*")) {
            request.setAttribute("Ok", DoctorDAO.getDoctorPorHospital(hospital));
        } else {
            request.setAttribute("Ok", DoctorDAO.getDoctorPorEyH(especificacion, hospital));
        }
        session.setAttribute("especificacion", especificacion);
        request.getRequestDispatcher("buscarTurnos.jsp").forward(request, response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}