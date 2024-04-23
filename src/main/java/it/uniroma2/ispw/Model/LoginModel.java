package it.uniroma2.ispw.Model;

import it.uniroma2.ispw.bean.LoginBean;

import javax.management.relation.Role;

public class LoginModel {
    private final String Email;
    private final String password;

    private Role role = null;

    public LoginModel(LoginBean credbean) {
        this.Email = credbean.getEmail();
        this.password = credbean.getPassword();
        this.role=credbean.getRole();
    }

    public LoginModel(String Email, String password, Role role) {
        this.Email = Email;
        this.password = password;
        this.role = role;
    }


    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
