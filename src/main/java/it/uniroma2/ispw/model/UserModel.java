package it.uniroma2.ispw.model;

import it.uniroma2.ispw.enums.Role;

public class UserModel {
    private String email;

    private Role ruolo;
    private String nome;


    public UserModel(String email,Role ruolo,String nome) {
            this.email=email;
            this.ruolo=ruolo;
            this.nome=nome;
    }



    public String getNome() {
        return nome;
    }

    public Role getRuolo() {
        return ruolo;
    }

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public UserModel(String email, int matricola) {
        this.email = email;

    }
}
