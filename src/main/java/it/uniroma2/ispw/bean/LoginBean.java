package it.uniroma2.ispw.bean;

import it.uniroma2.ispw.Enums.Role;

public class  aLoginBean {
    private String email;
    private String password;
    private int idSession;
    private Role role;

    public LoginBean() {}
    public LoginBean(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public LoginBean(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public int getIdSession() {
        return idSession;
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
