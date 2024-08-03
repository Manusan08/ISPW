package it.uniroma2.ispw.bean;

public class DocenteBean extends UserBean{
    private String dipartimento;
    public DocenteBean(String cognome, String email, String nome) {
        super(cognome, email, nome);
    }
}
