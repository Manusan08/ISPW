package it.uniroma2.ispw.model.login;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.bean.LoginBean;


public class LoginModel {
    public void setEmail(String email) {
        this.email = email;
    }

    private  String email;
    private String password;

    private it.uniroma2.ispw.enums.Role role = null;

    public LoginModel(LoginBean credbean) {
        this.email = credbean.getEmail();
        this.password = credbean.getPassword();
        this.role = credbean.getRole();
    }

    public LoginModel(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public LoginModel() {

    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LoginModel(String email, String password) {
        this.email = email;

        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
