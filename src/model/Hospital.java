package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ idEA.
 * User: Kevin
 * Date: 22/03/12
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Hospital {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String nombre;
    @OneToOne
    private Direccion direccion;
    @ManyToMany
    private List<Doctor> doctores;
    public Hospital() {
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Direccion getDireccion() {
        return direccion;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    public List<Doctor> getDoctores() {
        return doctores;
    }
    public void setDoctores(List<Doctor> doctores) {
        this.doctores = doctores;
    }
}
