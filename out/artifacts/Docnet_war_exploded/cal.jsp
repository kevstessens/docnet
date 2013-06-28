<%@ page import="java.util.List" %>
<%@ page import="model.Doctor" %>
<%@ page import="dao.PacienteDAO" %>
<%@ page import="model.Usuario" %>
<%@ page import="dao.UsuarioDAO" %>
<%@ page import="model.Paciente" %>
<%@ page import="model.Turno" %>
<%@ page import="dao.TurnoDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <style type='text/css'>

        body {
            font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
            margin: 0;
        }

        h1 {
            margin: 0 0 1em;
            padding: 0.5em;
        }

        p.description {
            font-size: 0.8em;
            padding: 1em;
            position: absolute;
            top: 3.2em;
            margin-right: 400px;
        }

        #message {
            font-size: 0.7em;
            position: absolute;
            top: 1em;
            right: 1em;
            width: 350px;
            display: none;
            padding: 1em;
            background: #ffc;
            border: 1px solid #dda;
        }

    </style>
    <!--
	<link rel='stylesheet' type='text/css' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css' />
	-->
    <link rel='stylesheet' type='text/css' href='calendar/libs/css/smoothness/jquery-ui-1.8.11.custom.css'/>
    <link rel='stylesheet' type='text/css' href='calendar/jquery.weekcalendar.css'/>
    <!--
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js'></script>
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js'></script>
    -->
    <script type='text/javascript' src='js/jquery-1.4.4.min.js'></script>
    <script type='text/javascript' src='js/jquery-ui-1.8.11.custom.min.js'></script>
    <script type="text/javascript" src="js/date.js"></script>
    <script type='text/javascript' src='js/jquery.weekcalendar.js'></script>
    <script type='text/javascript'>

        var year = new Date().getFullYear();
        var month = new Date().getMonth();
        var day = new Date().getDate();
        <%
            HttpSession s = request.getSession();
            List<Integer> horas = (List<Integer>)s.getAttribute("horas");
            List<Integer> minutos = (List<Integer>)s.getAttribute("minutos");
            int duracion = (Integer)s.getAttribute("duracion");
            List<Turno> turnos = (List<Turno>)s.getAttribute("turnos");
            Usuario usuario = UsuarioDAO.getUsuarioPorEmail(request.getRemoteUser());
            s.setAttribute("usuario", usuario);
            boolean isDoctor = usuario.isDoctor();
        %>
        var eventData = {
            events : [
                <% if(!isDoctor) { %>
                    <%=TurnoDAO.toJsonString(turnos, duracion)%>
                <% } else if(isDoctor) { %>
                    <%=TurnoDAO.toJsonStringDoctor(turnos, duracion)%>
                <% } %>
            ]
        };
        $(document).ready(function() {
            $('#calendar').weekCalendar({
                        timeslotsPerHour : 60 /<%=duracion%>,
                        timeslotHeight : 40,
                        defaultEventLength : 1,
                        businessHours : {start: <%=horas.get(0)%>, end: <%=horas.get(horas.size()-1)%>+1, limitDisplay: true},
                        height : function($calendar) {
                            return $(window).height() - $("h1").outerHeight(true);
                        },
                        eventRender : function(calEvent, $event) {
                            if (calEvent.end.getTime() < new Date().getTime()) {
                                $event.css("backgroundColor", "#aaa");
                                $event.find(".time").css({"backgroundColor": "#999", "border":"1px solid #888"});
                            }
                        },
                        eventNew : function(calEvent, $event) {
                            displayMessage("<strong>Added event</strong><br/>Start: " + calEvent.start + "<br/>End: " + calEvent.end);
                            displayMessage("NEW EVENT! " + calEvent.title)
                            var test = jQuery.get("/docnet/SacarTurnoServlet?date=" + calEvent.start.getTime(), function(data){
                                alert("Data Loaded: " + data);
                            })
                            .success(function() { alert("second success"); })
                                    .error(function() { alert("error"); })
                                    .complete(function() { alert("complete"); })
                            alert(test);
                        },
                        resizable : function(calEvent, $event) {
                            return false;
                        },
                        draggable : function(calEvent, $event) {
                            return false;
                        },
                        eventClick : function(calEvent, $event) {
                            displayMessage("<strong>Clicked Event</strong><br/>Start: " + calEvent.start + "<br/>End: " + calEvent.end);
                            <% if(isDoctor) { %>
                            calEvent.title = "MyTest"
                            c = confirm("Cerrar consulta");
                            if(c) {
                                window.location.href = "/docnet/CerrarTurnoServlet?date=" + calEvent.start.getTime();

                            }
                            <% } %>
                        },
                        eventMouseover : function(calEvent, $event) {
                            displayMessage("<strong>Mouseover Event</strong><br/>Start: " + calEvent.start + "<br/>End: " + calEvent.end);
                        },
                        eventMouseout : function(calEvent, $event) {
                            displayMessage("<strong>Mouseout Event</strong><br/>Start: " + calEvent.start + "<br/>End: " + calEvent.end);
                        },
                        noEvents : function() {
                            displayMessage("There are no events for this week");
                        },
                        firstDayOfWeek : function(calEvent, $event) {
                            return 1;
                        },
                        data: function(start, end, callback) {
                                callback(eventData);
                        }
                    });

            function displayMessage(message) {
                $("#message").html(message).fadeIn();
                console.log(message);
            }

            $("<div id=\"message\" class=\"ui-corner-all\"></div>").prependTo($("body"));

        });

    </script>

</head>
<body>
<h1>Week Calendar Demo</h1>

<p class="description">This calendar demonstrates a basic calendar. Events triggered are displayed in the message area.
    Appointments in the past are shaded grey.</p>

<div id='calendar'></div>

</body>
</html>