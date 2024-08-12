package it.uniroma2.ispw.model.prenotazioneAula;

import it.uniroma2.ispw.enums.Orario;

import java.sql.Date;

public  class PrenotazioneAulaModel {
    private  String email;
    private String iDaula;
    private Orario oraLezione;
    private Date datalezione;
    private String descrizione;
    private String materia;
    private String idPrenotazioneAula;
    private String nomeProfessore;
    private boolean isRicorente;
    private java.util.Date dataFine;

    public boolean isRicorente() {
        return isRicorente;
    }

    public void setRicorente(boolean ricorente) {
        isRicorente = ricorente;
    }

    public java.util.Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(java.util.Date dataFine) {
        this.dataFine = dataFine;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getiDaula() {
        return iDaula;
    }

    public void setiDaula(String iDaula) {
        this.iDaula = iDaula;
    }

    public Orario getOraLezione() {
        return oraLezione;
    }

    public void setOraLezione(Orario oraLezione) {
        this.oraLezione = oraLezione;
    }

    public Date getDatalezione() {
        return datalezione;
    }

    public void setDatalezione(Date datalezione) {
        this.datalezione = datalezione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getIdPrenotazioneAula() {
        return idPrenotazioneAula;
    }

    public void setIdPrenotazioneAula(String idPrenotazioneAula) {
        this.idPrenotazioneAula = idPrenotazioneAula;
    }

    public String getNomeProfessore() {
        return nomeProfessore;
    }

    public void setNomeProfessore(String nomeProfessore) {
        this.nomeProfessore = nomeProfessore;
    }

}
