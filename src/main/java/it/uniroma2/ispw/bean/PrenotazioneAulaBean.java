package it.uniroma2.ispw.bean;


import it.uniroma2.ispw.enums.Orario;

import java.util.Date;

public class PrenotazioneAulaBean {

    private String idPrenotazioneAula;
    private String idAula;
    private String email;


    private Orario oraLezione;
    private Date giornoLezione;
    private String descrizione;
    private String nomeDocente;
    private String materia;
    private boolean isRicorente;
    private Date dataFine;
    private int numeroPosti;

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public String getEmail() {
        return email;
    }


    public PrenotazioneAulaBean(String idPrenotazioneAula,

                                String nomeDocente,
                                java.sql.Date datalezione,
                                Orario oraLezione,
                                String descrizione,
                                String materia, String idAula) {


        this.idPrenotazioneAula = idPrenotazioneAula;
        this.descrizione = descrizione;
        this.nomeDocente = nomeDocente;
        this.giornoLezione = datalezione;
        this.oraLezione = oraLezione;
        this.materia = materia;
        this.idAula = idAula;


    }

    public PrenotazioneAulaBean() {

    }


    public boolean isRicorente() {
        return isRicorente;
    }

    public void setRicorente(boolean ricorente) {
        isRicorente = ricorente;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getGiornoLezione() {
        return giornoLezione;
    }

    public void setGiornoLezione(Date giornoLezione) {
        this.giornoLezione = giornoLezione;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getIdPrenotazioneAula() {
        return idPrenotazioneAula;
    }

    public void setIdPrenotazioneAula(String idPrenotazioneAula) {
        this.idPrenotazioneAula = idPrenotazioneAula;
    }

    public String getMateria() {
        return materia;
    }

    public String getNomeDocente() {
        return nomeDocente;
    }

    public Orario getOraLezione() {
        return oraLezione;
    }

    public void setOraLezione(Orario oraLezione) {
        this.oraLezione = oraLezione;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }


    public void setOrario(Orario orario) {

    }

    public void setOrarioLezione(java.sql.Date dataLezione) {

    }

    public String getOraLezione1() {
        return  this.oraLezione.getFasciaOraria();
    }
}

