package model;

import javax.persistence.*;
import javax.persistence.metamodel.ListAttribute;
import java.io.File;
import java.util.ArrayList;
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
    private String especializacion;
    @OneToMany
    private List<Consulta> historial;
    @Column
    private String nombrePaciente;
    public HistoriaClinica(){
    }
    public String getEspecializacion() {
        return especializacion;
    }
    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
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