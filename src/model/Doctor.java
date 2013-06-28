package model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 22/03/12
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Doctor extends Usuario {
    @OneToMany
    private List<HorariosDeAtencion> horarios;
    @ManyToMany
    private List<Hospital> hospitales;
    @ManyToMany
    private List<ObraSocial> obrasSociales;
    @OneToMany
    private List<Turno> turnos;
    public Doctor(){
        super();
    }
    public List<HorariosDeAtencion> getHorarios() {
        return horarios;
    }
    public void setHorarios(List<HorariosDeAtencion> horarios) {
        this.horarios = horarios;
    }
    public List<Hospital> getHospitales() {
        return hospitales;
    }
    public void setHospitales(List<Hospital> hospitales) {
        this.hospitales = hospitales;
    }
    public List<ObraSocial> getObrasSociales() {
        return obrasSociales;
    }
    public void setObrasSociales(List<ObraSocial> obrasSociales) {
        this.obrasSociales = obrasSociales;
    }
    public List<Turno> getTurnos() {
        return turnos;
    }
    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
