package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ idEA.
 * User: Kevin
 * Date: 22/03/12
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Turno {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private Date date;
    @Column
    private String dateString;
    @Column
    private String especializacion;
    @Column
    private String nombreDoctor;
    @Column
    private String nombrePaciente;
    public Turno() {
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
        setDateString(date.toString());
    }
    public String getDateString() {
        return dateString;
    }
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
    public String getEspecializacion() {
        return especializacion;
    }
    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }
    public String getNombreDoctor() {
        return nombreDoctor;
    }
    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }
    public String getNombrePaciente() {
        return nombrePaciente;
    }
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
}