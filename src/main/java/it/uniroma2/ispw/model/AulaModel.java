package it.uniroma2.ispw.model;

import java.sql.Date;
import java.util.List;

public class AulaModel {

    private String nomeDocente;

    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    public Date getOrarioLezione() {
        return orarioLezione;
    }

    public void setOrarioLezione(Date orarioLezione) {
        this.orarioLezione = orarioLezione;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<String> getDispositivi() {
        return dispositivi;
    }

    public void setDispositivi(List<String> dispositivi) {
        this.dispositivi = dispositivi;
    }

    public boolean isPrenotata() {
        return prenotata;
    }

    public void setPrenotata(boolean prenotata) {
        this.prenotata = prenotata;
    }

    private Date orarioLezione;
    private String materia;
    private String idAula;
    private int posti;
    private String descrizione;
    private List<String> dispositivi;
    private boolean prenotata;
}
