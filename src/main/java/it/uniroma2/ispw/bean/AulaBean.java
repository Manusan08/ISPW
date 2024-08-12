package it.uniroma2.ispw.bean;

import it.uniroma2.ispw.enums.Orario;

import java.sql.Date;
import java.util.List;


public class AulaBean {




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



    public AulaBean(){}
    public AulaBean(int posti){
        this.posti=posti;
    }


    public AulaBean(String idAula, int posti, boolean proiettore, boolean banchiDisegno, boolean computer) {
        this.idAula = idAula;
        this.posti = posti;
        this.proiettore = proiettore;
        this.banchiDisegno = banchiDisegno;
        this.computer = computer;
    }

    public boolean isProiettore() {
        return proiettore;
    }

    public boolean isBanchiDisegno() {
        return banchiDisegno;
    }

    public void setBanchiDisegno(boolean banchiDisegno) {
        this.banchiDisegno = banchiDisegno;
    }

    public boolean isComputer() {
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    public boolean isStatoAula() {
        return statoAula;
    }

    public void setStatoAula(boolean statoAula) {
        this.statoAula = statoAula;
    }




    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }




    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }


    public void setProiettore(boolean proiettoreRichiesto) {this.proiettore=proiettoreRichiesto;
    }
}
