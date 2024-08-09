package it.uniroma2.ispw.bean;

import it.uniroma2.ispw.enums.Role;

public class UserBean {
    private String nome;

    private Role ruolo;
    private String email;



    public UserBean(String email, Role ruolo) {
        this.email = email;
        this.ruolo = ruolo;
    }

    public UserBean(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }

    public UserBean(String email) {this.email =email;
    }

    public Role getRuolo() {
        return ruolo;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }




}
