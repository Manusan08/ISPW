package it.uniroma2.ispw.Controller.controllerGrafico2;

import it.uniroma2.ispw.Controller.controllerApplicativo.LoginController;
import it.uniroma2.ispw.Enums.Role;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LoginViewController2 extends TemplateView{

    @Override
    public LoginBean control() {
        LoginBean loginBean;
        try {
            loginBean = this.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            new LoginController().login(loginBean);
        } catch (SystemException | LoginException e) {
            throw new RuntimeException(e);
        }
        return loginBean;
    }

    @Override
    public List<String> getOptions() {
        return List.of("Riprova", "Esci");
    }

    @Override
    public String getHeader() {
        return "LOGIN";
    }

    @Override
    public void update(String... msg) {

    }

    public LoginBean show() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printHeader("Benvenuto nel sistema di login");

        System.out.print("Inserisci email: ");
        String email = reader.readLine();

        System.out.print("Inserisci password: ");
        String pwd = reader.readLine();

        System.out.print("Inserisci ruolo: ");
        Role ruolo = Role.valueOf(reader.readLine());

        return new LoginBean(email, pwd, ruolo);
    }

    public UserBean getUsrBean() {
        return usrBean;
    }
}