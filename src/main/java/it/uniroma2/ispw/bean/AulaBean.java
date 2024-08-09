package it.uniroma2.ispw.bean;

import it.uniroma2.ispw.enums.Orario;

import java.sql.Date;
import java.util.List;


public class AulaBean {



    private Orario orarioLezione;
    private Date giornoLezione;
    private String idAula;
    private int posti;
    private boolean proiettore;
    private boolean banchiDisegno;
    private boolean computer;
    private boolean statoAula;


    public AulaBean(String idAula, int posti) {
        this.idAula = idAula;
        this.posti = posti;

    }

    private boolean prenotata;

    public AulaBean(){}
    public AulaBean(int posti){
        this.posti=posti;
    }







    public boolean isPrenotata() {
        return prenotata;
    }

    public void setPrenotata(boolean prenotata) {
        this.prenotata = prenotata;
    }




    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }



    public Orario getOrarioLezione() {
        return orarioLezione;
    }

    public void setOrarioLezione(Orario orarioLezione) {
        this.orarioLezione = orarioLezione;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }


}
