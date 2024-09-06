package it.uniroma2.ispw.model.prenotazioneaula;

import it.uniroma2.ispw.enums.Orario;

import java.sql.Date;

public  class PrenotazioneAulaModel {
    private  String email;
    private String idAula;
    private Orario oraLezione;
    private Date datalezione;
    private String descrizione;
    private String materia;
    private String idPrenotazioneAula;
    private String nomeProfessore;
    private boolean isRicorente;
    private java.util.Date dataFine;

    public PrenotazioneAulaModel(String idPrenotazioneAula) {
        this.idPrenotazioneAula = idPrenotazioneAula;
    }


    public String getIdAula() {
        return idAula;
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

    public String getDescrizione() {
        return descrizione;
    }

    public String getIdPrenotazioneAula() {
        return idPrenotazioneAula;
    }

    public String getNomeProfessore() {
        return nomeProfessore;
    }

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

    public String getCognomeProfessore() {
        return cognomeProfessore;
    }

    public void setCognomeProfessore(String cognomeProfessore) {
        this.cognomeProfessore = cognomeProfessore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






    private String cognomeProfessore;

    public PrenotazioneAulaModel(String nomeProfessore, String materia) {
        this.nomeProfessore=nomeProfessore;
        this.materia=materia;
    }

    public PrenotazioneAulaModel() {
    }



    public void setDatalezione(Date datalezione) {
        this.datalezione = datalezione;
    }



    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }





    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }



    public void setIdPrenotazioneAula(String idPrenotazioneAula) {
        this.idPrenotazioneAula = idPrenotazioneAula;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }



    public void setNomeProfessore(String nomeProfessore) {
        this.nomeProfessore = nomeProfessore;
    }


    public void setOraLezione(String oraLezione) {

        Orario orario=Orario.valueOf(oraLezione);
        this.oraLezione =orario ;
    }
}
