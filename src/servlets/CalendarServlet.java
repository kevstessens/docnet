package servlets;

import dao.DoctorDAO;
import dao.TurnoDAO;
import model.Doctor;
import model.HorariosDeAtencion;

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
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "CalendarServlet")
public class CalendarServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DNI = request.getParameter("DNI");
        HttpSession session = request.getSession(true);
        String especificacion = (String)session.getAttribute("especificacion");
        String DNIdoc = (String)session.getAttribute("DNI");
        String isDoctor = (String)session.getAttribute("isDoctor");
        if(DNI==null && DNIdoc==null) {
            request.setAttribute("Null", DNI);
        } else {
            String s = "";
            if(isDoctor==null) {
                s = DNI;
            } else if(isDoctor.equals("Si")) {
                s = DNIdoc;
            }
            Doctor doctor = DoctorDAO.getDoctorPorDNI(s);
            List<String> horas = getHoras(doctor);
            List<String> minutos = getMinutos(doctor);
            int duracion = getDuracion(doctor);
            session.setAttribute("especificacion", especificacion);
            session.setAttribute("doctor", doctor);
            session.setAttribute("horas",  horas);
            session.setAttribute("minutos", minutos);
            session.setAttribute("duracion", duracion);
            session.setAttribute("turnos", TurnoDAO.getTurnosPorDoctor(s));
            response.sendRedirect("cal.jsp");
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    private int getDuracion(Doctor doctor) {
        List<HorariosDeAtencion> hs = doctor.getHorarios();
        HorariosDeAtencion h = hs.get(0);
        return Integer.parseInt(h.getDuracion());
    }
    private List<String> getMinutos(Doctor doctor) {
        List<HorariosDeAtencion> hs = doctor.getHorarios();
        HorariosDeAtencion h = hs.get(0);
        List<String> list = new ArrayList<String>();
        int d = Integer.parseInt(h.getDuracion());
        list.add("00");
        if(d==15) {
            list.add("15");
            list.add("30");
            list.add("45");
        } else if(d==20) {
            list.add("20");
            list.add("40");
        } else if(d==30) {
            list.add("30");
        }
        return list;
    }
    private List<String> getHoras(Doctor doctor) {
        List<HorariosDeAtencion> hs = doctor.getHorarios();
        HorariosDeAtencion h = hs.get(0);
        List<String> list = new ArrayList<String>();
        String s = h.getHorarioDeInicio();
        int hi = Integer.parseInt(s);
        int hf = Integer.parseInt(h.getHorarioDeFin());
        if(s.length()==1) {
            s = "0".concat(s);
        }
        list.add(s);
        for(int i=hi+1;i<hf;i++) {
            String t = i+"";
            list.add(t);
        }
        return list;
    }
}
