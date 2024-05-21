package it.uniroma2.ispw.bean;

public class UserBean {
    private String nome;
    private String cognome;

    public String getCognome() {
        return cognome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UserBean(String cognome, String email, String nome) {
        this.cognome = cognome;
        this.email = email;
        this.nome = nome;
    }

    private String email;
}
