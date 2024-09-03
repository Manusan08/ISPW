package it.uniroma2.ispw.controller.controllergrafico2;

import it.uniroma2.ispw.controller.controllerApplicativo.LoginController;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LoginViewController2 extends TemplateView{

    @Override
    public void control() throws SystemException, LoginException {
        LoginBean loginBean = new LoginBean();
        LoginController loginController= new LoginController();
        try {
            loginBean = this.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            usrBean= loginController.login(loginBean);
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getOptions() {
        return List.of("Riprova", "Esci");
    }

    @Override
    public String getHeader() {
        return "LOGIN";
    }



    public LoginBean show() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printHeader("Benvenuto nel sistema di login");

        System.out.print("Inserisci email: ");
        String email = reader.readLine();

        System.out.print("Inserisci password: ");
        String pwd = reader.readLine();

        return new LoginBean(email, pwd);
    }

    public UserBean getUsrBean() {
        return usrBean;
    }
}