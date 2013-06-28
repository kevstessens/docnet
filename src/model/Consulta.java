package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 22/03/12
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Consulta {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private Date date;
    @Column
    private String texto;
    public Consulta() {
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
}