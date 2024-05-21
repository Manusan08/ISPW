package it.uniroma2.ispw.Model;

public class UserModel {
    private String email;
    private int matricola;
    private int role;
    private String nome;
    private String cognome;

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public int getRole() {
        return role;
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
