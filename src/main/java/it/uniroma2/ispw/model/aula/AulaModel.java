package it.uniroma2.ispw.model.aula;

import java.sql.Date;
import java.util.List;

public class AulaModel {
    private String idAula;
    private int numeroPosti;
    private boolean proiettore;
    private boolean banchiDisegno;
    private boolean computer;
    private boolean statoAula;


    public AulaModel(String idAula, int posti) {
    }

    public AulaModel() {

    }

    public AulaModel(String idAula, int numeroPosti, boolean proiettore, boolean banchiDisegno, boolean computer) {
        this.idAula = idAula;
        this.numeroPosti = numeroPosti;
        this.proiettore = proiettore;
        this.banchiDisegno = banchiDisegno;
        this.computer = computer;
    }

    public AulaModel(String idAula) {this.idAula=idAula;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public boolean isProiettore() {
        return proiettore;
    }

    public void setProiettore(boolean proiettore) {
        this.proiettore = proiettore;
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


}
