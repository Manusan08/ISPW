package it.uniroma2.ispw.bean;

import java.sql.Date;
import java.util.List;


public class AulaBean {


    private String nomeDocente;
    private Date orarioLezione;
    private String materia;
    private String idAula;
    private int posti;
    private String descrizione;
    private List<String> dispositivi;

    public AulaBean(String idAula, int posti, List<String> dispositivi) {
        this.idAula = idAula;
        this.posti = posti;
        this.dispositivi = dispositivi;
    }

    private boolean prenotata;

    public AulaBean(){}
    public AulaBean(int posti){
        this.posti=posti;
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


    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Date getOrarioLezione() {
        return orarioLezione;
    }

    public void setOrarioLezione(Date orarioLezione) {
        this.orarioLezione = orarioLezione;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }

    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

}
