package it.uniroma2.ispw.bean;

import javax.management.relation.Role;

public class  LoginBean {
    private String email;
    private String password;

    private String role;


    public LoginBean() {
    }

    public LoginBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
}
