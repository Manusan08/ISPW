import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.model.login.dao.LoginDAO;
import it.uniroma2.ispw.model.login.dao.LoginDBMS;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class WrongCredentialTest {
    @Test
    void testCredenziali(){
        LoginDAO loginDAO=new LoginDBMS();
        LoginBean loginBean=new LoginBean("paolo89@Torverdura.org","torverdura123");
        try {
            Assertions.assertNull(loginDAO.auth(loginBean));
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
