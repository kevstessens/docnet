package servlets;

import dao.*;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 24/04/12
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "AddDoctorServlet")
public class AddDoctorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String DNI = (request.getParameter("DNI"));
        String fechaDeNacimiento = request.getParameter("fechaDeNacimiento");
        String especificacion = request.getParameter("especificacion");
        String obraSocial1 = request.getParameter("obraSocial1");
        String obraSocial2 = request.getParameter("obraSocial2");
        String obraSocial3 = request.getParameter("obraSocial3");
        String obraSocial4 = request.getParameter("obraSocial4");
        String horaInicial = request.getParameter("horaInicial");
        String horaFinal = request.getParameter("horaFinal");
        String duracion = request.getParameter("duracion");
        String hospital1 = request.getParameter("hospital1");
        String hospital2 = request.getParameter("hospital2");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (apellido.equalsIgnoreCase("") || nombre.equalsIgnoreCase("") || password.equalsIgnoreCase("") ||
                email.equalsIgnoreCase("") || DNI.equalsIgnoreCase("") || fechaDeNacimiento.equalsIgnoreCase("") ||
                ((obraSocial1==null)&&(obraSocial2==null)&&(obraSocial3==null)&&(obraSocial4==null)) ||
                ((hospital1==null)&&(hospital2==null))) {
            request.setAttribute("falta", nombre);
            request.getRequestDispatcher("agregarDoctor.jsp").forward(request, response);
        } else {
            boolean existDNI = UsuarioDAO.hayDNI(DNI);
            boolean existMail = UsuarioDAO.hayMail(email);
            if (!(existMail || existDNI)) {
                Doctor doctor = new Doctor();
                doctor.setNombre(nombre);
                doctor.setApellido(apellido);
                doctor.setDNI(DNI);
                doctor.setFechaDeNacimiento(fechaDeNacimiento);
                List<ObraSocial> obrasSociales = setObrasSociales(obraSocial1, obraSocial2, obraSocial3, obraSocial4);
                doctor.setObrasSociales(obrasSociales);
                List<HorariosDeAtencion> horariosDeAtencion = setHorariosDeAtencion(horaInicial, horaFinal, duracion);
                doctor.setHorarios(horariosDeAtencion);
                List<Hospital> hospitales = setHospitales(hospital1, hospital2);
                doctor.setHospitales(hospitales);
                doctor.setEmail(email);
                doctor.setPassword(password);
                doctor.setDoctor(true);
                UsuarioDAO.persist(doctor);
                EspecificacionDAO.addDoctor(especificacion, doctor);
                for(int i=0;i<obrasSociales.size();i++) {
                    ObraSocialDAO.addDoctor(obrasSociales.get(i).getNombre(), doctor);
                }
                for(int i=0;i<hospitales.size();i++) {
                    HospitalDAO.addDoctor(hospitales.get(i).getNombre(), doctor);
                }
                System.out.println("The registration was completed");
                response.sendRedirect("admin.jsp");
            } else {
                if (existDNI) {
                    request.setAttribute("nameExist", DNI);
                }
                if (existMail) {
                    request.setAttribute("mailExist", email);
                }
                request.getRequestDispatcher("agregarDoctor.jsp").forward(request, response);
            }
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    private List<ObraSocial> setObrasSociales(String obraSocial1, String obraSocial2, String obraSocial3, String obraSocial4) {
        List<ObraSocial> obrasSociales = new ArrayList<ObraSocial>();
        if(!(obraSocial1==null)) {
            obrasSociales.add(ObraSocialDAO.getOSPorNombre(obraSocial1));
        }
        if(!(obraSocial2==null)) {
            obrasSociales.add(ObraSocialDAO.getOSPorNombre(obraSocial2));
        }
        if(!(obraSocial3==null)) {
            obrasSociales.add(ObraSocialDAO.getOSPorNombre(obraSocial3));
        }
        if(!(obraSocial4==null)) {
            obrasSociales.add(ObraSocialDAO.getOSPorNombre(obraSocial4));
        }
        return obrasSociales;
    }
    private List<HorariosDeAtencion> setHorariosDeAtencion(String horaInicial, String horaFinal, String duracion) {
        HorariosDeAtencion lunes, martes, miercoles, jueves, viernes;
        lunes = new HorariosDeAtencion();
        lunes.setHorarioDeInicio(horaInicial);
        lunes.setHorarioDeFin(horaFinal);
        lunes.setDuracion(duracion);
        martes = new HorariosDeAtencion();
        martes.setHorarioDeInicio(horaInicial);
        martes.setHorarioDeFin(horaFinal);
        martes.setDuracion(duracion);
        miercoles = new HorariosDeAtencion();
        miercoles.setHorarioDeInicio(horaInicial);
        miercoles.setHorarioDeFin(horaFinal);
        miercoles.setDuracion(duracion);
        jueves = new HorariosDeAtencion();
        jueves.setHorarioDeInicio(horaInicial);
        jueves.setHorarioDeFin(horaFinal);
        jueves.setDuracion(duracion);
        viernes = new HorariosDeAtencion();
        viernes.setHorarioDeInicio(horaInicial);
        viernes.setHorarioDeFin(horaFinal);
        viernes.setDuracion(duracion);
        List<HorariosDeAtencion> horariosDeAtencion = new ArrayList<HorariosDeAtencion>();
        HorariosDeAtencionDAO.persist(lunes);
        HorariosDeAtencionDAO.persist(martes);
        HorariosDeAtencionDAO.persist(miercoles);
        HorariosDeAtencionDAO.persist(jueves);
        HorariosDeAtencionDAO.persist(viernes);
        horariosDeAtencion.add(lunes);
        horariosDeAtencion.add(martes);
        horariosDeAtencion.add(miercoles);
        horariosDeAtencion.add(jueves);
        horariosDeAtencion.add(viernes);
        return horariosDeAtencion;
    }
    private List<Hospital> setHospitales(String hospital1, String hospital2) {
        List<Hospital> hospitales = new ArrayList<Hospital>();
        if(!(hospital1==null)) {
            hospitales.add(HospitalDAO.getHospitalPorNombre(hospital1));
        }
        if(!(hospital2==null)) {
            hospitales.add(HospitalDAO.getHospitalPorNombre(hospital2));
        }
        return hospitales;
    }
}