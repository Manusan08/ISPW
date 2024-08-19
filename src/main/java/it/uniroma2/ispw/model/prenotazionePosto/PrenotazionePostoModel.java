package it.uniroma2.ispw.model.prenotazionePosto;

import it.uniroma2.ispw.controller.controllerApplicativo.Observer.Context;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.observers.Observer;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.subject.Status;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.subject.Subject;
import it.uniroma2.ispw.controller.controllergrafico2.studente.PrenotazionePostoContext;
import it.uniroma2.ispw.model.posto.PostoModel;

import java.util.Date;

public class PrenotazionePostoModel {
    Status status;

    private String idPrenotazioneAula;
    private String idPrenotazionePosto;
    private String idAula;
    private String idPosto;
    private String oraLezione;
    private Date giornoLezione;
    private String nomeDocente;
    private String nomeStudente;
    private String materia;
    private String email;

    public PrenotazionePostoModel() {
        super();
        this.status = Status.NON_EFFETTUATA;
    }

    public PrenotazionePostoModel(String idPosto) {
        this.idPosto = idPosto;
    }

    public PrenotazionePostoModel(String idPrenotazionePosto, String idAula, String email, String idPosto, String idPrenotazioneAula) {
        this.idPrenotazionePosto = idPrenotazionePosto;
        this.idAula = idAula;
        this.email = email;
        this.idPosto = idPosto;
        this.idPrenotazioneAula = idPrenotazioneAula;

    }


    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
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

    public String getIdPosto() {
        return idPosto;
    }

    public void setIdPosto(String idPosto) {
        this.idPosto = idPosto;
    }

    public String getIdPrenotazionePosto() {
        return idPrenotazionePosto;
    }

    public void setIdPrenotazionePosto(String idPrenotazionePosto) {
        this.idPrenotazionePosto = idPrenotazionePosto;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }


    public String getOraLezione() {
        return oraLezione;
    }

    public void setOraLezione(String oraLezione) {
        this.oraLezione = oraLezione;
    }

    public String getIdPrenotazioneAula() {
        return idPrenotazioneAula;
    }

    //-----------------------------DA QUI NUOVA PARTE----------------------------------------


    public void setIdPrenotazioneAula(String idPrenotazioneAula) {
        this.idPrenotazioneAula=idPrenotazioneAula;
    }
}