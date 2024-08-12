package it.uniroma2.ispw.bean;


import it.uniroma2.ispw.enums.Orario;

import java.util.Date;

public class PrenotazioneAulaBean {

    private  String idPrenotazioneAula;
    private String idAula;
    private String email;

    private Orario oraLezione;
    private Date giornoLezione;
    private  String Descrizione;
    private  String nomeDocente;
    private String materia;
    private boolean isRicorente;
    private  Date dataFine;

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    public void setMateria(String materia) {
        this.materia = materia;
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
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public String getEmail() {
        return email;
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


    public String getNomeMateria() {
    return "ciao";
    }

    public String getNomeProfessore() {
        return nomeDocente;

    }

    public Object getOrario() {
        return null;
    }

    public void setOrario(Orario orario) {

    }

    public void setOrarioLezione(java.sql.Date dataLezione) {

    }
}

