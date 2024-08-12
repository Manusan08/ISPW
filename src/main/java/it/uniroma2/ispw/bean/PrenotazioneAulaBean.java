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

    public void setNomeDocente(String desiredIn) {
    }

    public void setMateria(String desiredIn) {
    }

    public String getNomeMateria() {
    return "ciao";
    }

    public String getNomeProfessore() {
        return "ciao";

    }

    public Object getOrario() {
        return null;
    }

    public void setOrario(Orario orario) {

    }
}

