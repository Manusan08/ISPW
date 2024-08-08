package it.uniroma2.ispw.model;

import it.uniroma2.ispw.enums.Role;

public class UserModel {
    private String email;
    private int matricola;
    private Role ruolo;
    private String nome;
    private String cognome;

    public UserModel() {

    }

    public String getCognome() {
        return cognome;
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

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }


    public UserModel(String email, int matricola) {
        this.email = email;
        this.matricola = matricola;
    }
}
