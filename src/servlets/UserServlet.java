package servlets;

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
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getRemoteUser();
        if (email.equalsIgnoreCase(null)) {
            request.setAttribute("error", true);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (request.getParameter("email") != null) {
            email = request.getParameter("email");
            request.setAttribute("validUser", UserDAO.getUserByUserName(userName));
            request.setAttribute("establishmentData", getMapData(EstablishmentDAO.getEstablishment(userName, seeMap)));

            request.getRequestDispatcher("establishmentMap.jsp").forward(request, response);

        } else if (request.getParameter("step2Event") != null) {

            String step2Event = request.getParameter("step2Event");
            request.setAttribute("validUser", UserDAO.getUserByUserName(userName));
            request.setAttribute("establishmentData", getMapData(EventDAO.getEvent(userName, step2Event).getEstablishment()));
            request.setAttribute("event", EventDAO.getEvent(userName, step2Event));

            request.getRequestDispatcher("eventStep2.jsp").forward(request, response);

        } else if (request.getParameter("deleteEvent") != null) {

            EventDAO.deleteEvent(userName, request.getParameter("deleteEvent"));
            setUserDataFromDB(request, userName);
            request.getRequestDispatcher("validLog.jsp").forward(request, response);

        } else if (request.getParameter("deleteEstablishment") != null) {

            EstablishmentDAO.deleteEstablishment(userName, request.getParameter("deleteEstablishment"));
            setUserDataFromDB(request, userName);
            request.getRequestDispatcher("validLog.jsp").forward(request, response);

        } else if (UserDAO.existUser(userName)) {

            if (userName.equalsIgnoreCase("Admin")) {
                request.setAttribute("allUsers", UserDAO.getAllUsers());
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else {
                setUserDataFromDB(request, userName);
                request.getRequestDispatcher("validLog.jsp").forward(request, response);
            }

        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void setUserDataFromDB(HttpServletRequest request, String userName) {
        request.setAttribute("validUser", UserDAO.getUserByUserName(userName));
        request.setAttribute("userEvents", EventDAO.getEvents(userName));
        request.setAttribute("userEstablishments", EstablishmentDAO.getEstablishments(userName));
    }

    private String getMapData(Establishment establishment) {
        Map map = EstablishmentDAO.getMap(establishment);
        int capacity = establishment.getCapacity();
        ShapeRectangle activeRectangle = map.getShape();
        List<DeadZone> deadZones = map.getDeadZones();

        int mapScale = 1;
        if (map.getMapType() == Map.MapType.SMALL) {
            mapScale = 32;
        } else if (map.getMapType() == Map.MapType.MEDIUM) {
            mapScale = 16;
        } else if (map.getMapType() == Map.MapType.LARGE) {
            mapScale = 4;
        }

        String establishmentData = capacity + ";" + mapScale + ";" + activeRectangle.toString() + ";";
        for (int i = 0; i < deadZones.size(); i++) {
            if (i + 1 == deadZones.size()) {
                establishmentData = establishmentData + deadZones.get(i).getShape().toString();
            } else {
                establishmentData = establishmentData + deadZones.get(i).getShape().toString() + ";";
            }
        }

        System.out.println("EstablishmentData Sent:");
        System.out.println("MapData = " + establishmentData);

        return establishmentData;
    }*/
}