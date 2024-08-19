package it.uniroma2.ispw.model.prenotazioneAula;

import it.uniroma2.ispw.enums.Orario;

import java.sql.Date;

public class PrenotazioneAulaModel {
    private  String email;
    private String iDaula;
    private Orario oraLezione;
    private Date datalezione;
    private String descrizione;
    private String materia;
    private String idPrenotazioneAula;
    private String nomeProfessore;
    private String cognomeProfessore;

    public PrenotazioneAulaModel(String nomeProfessore, String materia) {
        this.nomeProfessore=nomeProfessore;
        this.materia=materia;
    }

    public PrenotazioneAulaModel() {
    }

    public String getCognomeProfessore() {
        return cognomeProfessore;
    }

    public void setCognomeProfessore(String cognomeProfessore) {
        this.cognomeProfessore = cognomeProfessore;
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

    public String getIdPrenotazioneAula() {
        return idPrenotazioneAula;
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

    public String getNomeProfessore() {
        return nomeProfessore;
    }

    public void setNomeProfessore(String nomeProfessore) {
        this.nomeProfessore = nomeProfessore;
    }

    public Orario getOraLezione() {
        return oraLezione;
    }

    public void setOraLezione(String oraLezione) {

        Orario orario=Orario.valueOf(oraLezione);
        this.oraLezione =orario ;
    }
}
