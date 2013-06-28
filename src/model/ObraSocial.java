package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ idEA.
 * User: Kevin
 * Date: 22/03/12
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ObraSocial {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String nombre;
    @ManyToMany
    private List<Doctor> doctores;
    public ObraSocial() {
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Doctor> getDoctores() {
        return doctores;
    }
    public void setDoctores(List<Doctor> doctores) {
        this.doctores = doctores;
    }
}
