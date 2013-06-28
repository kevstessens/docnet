package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ idEA.
 * User: Kevin
 * Date: 22/03/12
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class HistoriaClinica {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String especificacion;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Consulta> historial;
    @Column
    private String nombrePaciente;
    public HistoriaClinica(){
    }
    public String getEspecificacion() {
        return especificacion;
    }
    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }
    public List<Consulta> getHistorial() {
        return historial;
    }
    public void setHistorial(List<Consulta> historial) {
        this.historial = historial;
    }
    public String getNombrePaciente() {
        return nombrePaciente;
    }
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
}