package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 22/03/12
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Paciente extends Usuario {
    @Column
    private double ranking;
    @OneToMany
    private List<HistoriaClinica> historiasClinicas;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Turno> turnos;
    @ManyToOne
    private ObraSocial obraSocial;
    public Paciente() {
        super();
        ranking = 1;
    }
    public double getRanking() {
        return ranking;
    }
    public void setRanking(double ranking) {
        this.ranking = ranking;
    }
    public List<HistoriaClinica> getHistoriasClinicas() {
        return historiasClinicas;
    }
    public void setHistoriasClinicas(List<HistoriaClinica> historiasClinicas) {
        this.historiasClinicas = historiasClinicas;
    }
    public List<Turno> getTurnos() {
        return turnos;
    }
    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
    public ObraSocial getObraSocial() {
        return obraSocial;
    }
    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }
}
