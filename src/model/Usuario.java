package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 22/03/12
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
@Entity ()
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToOne
    private Direccion direccion;
    //int
    @Column(unique = true)
    private String DNI;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String fechaDeNacimiento;   //  DD/MM/AAAA
    @Column
    private boolean admin = false;
    @Column
    private boolean doctor = false;
    public Usuario(){
    }
    public Direccion getDireccion() {
        return direccion;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String mail) {
        this.email = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean esAdmin) {
        this.admin = esAdmin;
    }
    public boolean isDoctor() {
        return doctor;
    }
    public void setDoctor(boolean esDoctor) {
        this.doctor = esDoctor;
    }
}