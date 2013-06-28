package model;

import javax.persistence.*;

/**
 * Created by IntelliJ idEA.
 * User: Kevin
 * Date: 22/03/12
 * Time: 13:49
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class HorariosDeAtencion {
    @Id
    @GeneratedValue
    private long id;
    @Column (nullable = false)
    private String horarioDeInicio;
    @Column (nullable = false)
    private String horarioDeFin;
    @Column
    private String duracion;
    public HorariosDeAtencion(){
    }
    public String getHorarioDeInicio() {
        return horarioDeInicio;
    }
    public void setHorarioDeInicio(String horarioDeInicio) {
        this.horarioDeInicio = horarioDeInicio;
    }
    public String getHorarioDeFin() {
        return horarioDeFin;
    }
    public void setHorarioDeFin(String horarioDeFin) {
        this.horarioDeFin = horarioDeFin;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
