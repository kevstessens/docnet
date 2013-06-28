package securityFilter;

import dao.UsuarioDAO;
import model.Usuario;
import org.securityfilter.realm.SimpleSecurityRealmBase;

/**
 * Created by IntelliJ IDEA.
 * User: Tomi
 * Date: 20/04/12
 * Time: 08:27
 * To change this template use File | Settings | File Templates.
 */
public class DocnetRealm extends SimpleSecurityRealmBase {
    public boolean booleanAuthenticate(String email, String password) {
        boolean result = false;
        Usuario usuario = UsuarioDAO.getUsuarioPorEmail(email);
        if (usuario != null) {
            //Crypt password
            if (usuario.getPassword().equalsIgnoreCase(password)) {
                result = true;
                System.out.println("The login was completed");
            }
        }
        return result;
    }
    public boolean isUserInRole(String email, String role) {
        return true;
    }
}