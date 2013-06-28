package hibernate;

import dao.*;
import model.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.ArrayList;
import java.util.List;

public class DataBaseCreator {
    public static void main(String[] args) {
        createDatabase();
        addAdminUser();
        addCreators();
        addObrasSociales();
        addEspecificaciones();
        addHospitales();
    }
    public static void createDatabase() {
        Configuration cfg = new Configuration().configure("/hibernate.cfg.xml");
        SchemaExport schemaExport = new SchemaExport(cfg);
        schemaExport.drop(true, true);
        schemaExport.setOutputFile("testDB.sql");
        schemaExport.create(true, true);
    }
    private static void addAdminUser() {
        Usuario user = new Usuario();
        user.setNombre("admin");
        user.setApellido("admin");
        user.setPassword("admin123");
        user.setEmail("admin@docnet.com");
        user.setAdmin(true);
        UsuarioDAO.persistOutOfSession(user);
    }
    private static void addCreators() {
        Usuario user = new Usuario();
        user.setNombre("kevin");
        user.setApellido("Stessens");
        user.setPassword("kevin123");
        user.setEmail("kevin@docnet.com");
        Usuario user2 = new Usuario();
        user2.setNombre("tomas");
        user2.setApellido("Mateus");
        user2.setPassword("tomas123");
        user2.setEmail("tomas@docnet.com");
        Paciente carlos = new Paciente();
        carlos.setNombre("charly");
        carlos.setEmail("carlos@docnet.com");
        carlos.setPassword("123456");
        UsuarioDAO.persistOutOfSession(user);
        UsuarioDAO.persistOutOfSession(user2);
        UsuarioDAO.persistOutOfSession(carlos);
    }
    private static void addObrasSociales() {
        ObraSocial obraSocialA = new ObraSocial();
        obraSocialA.setNombre("ObraSocial A");
        ObraSocial obraSocialB = new ObraSocial();
        obraSocialB.setNombre("ObraSocial B");
        ObraSocial obraSocialC = new ObraSocial();
        obraSocialC.setNombre("ObraSocial C");
        ObraSocial obraSocialD = new ObraSocial();
        obraSocialD.setNombre("ObraSocial D");
        ObraSocialDAO.persistOutOfSession(obraSocialA);
        ObraSocialDAO.persistOutOfSession(obraSocialB);
        ObraSocialDAO.persistOutOfSession(obraSocialC);
        ObraSocialDAO.persistOutOfSession(obraSocialD);
    }
    private static void addEspecificaciones() {
        Especificacion especificacionA = new Especificacion();
        especificacionA.setNombre("Especificacion A");
        Especificacion especificacionB = new Especificacion();
        especificacionB.setNombre("Especificacion B");
        EspecificacionDAO.persistOutOfSession(especificacionA);
        EspecificacionDAO.persistOutOfSession(especificacionB);
    }
    private static void addHospitales() {
        Hospital hospitalA = new Hospital();
        hospitalA.setNombre("Hospital A");
        Hospital hospitalB = new Hospital();
        hospitalB.setNombre("Hospital B");
        HospitalDAO.persistOutOfSession(hospitalA);
        HospitalDAO.persistOutOfSession(hospitalB);
    }
}
