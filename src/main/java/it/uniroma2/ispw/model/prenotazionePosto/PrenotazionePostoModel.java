package it.uniroma2.ispw.model.prenotazionePosto;

import it.uniroma2.ispw.enums.Orario;

import java.util.Date;

public class PrenotazionePostoModel {
    private  String idPrenotazionePosto;
    private String idAula;
    private String idPosto;
    private String oraLezione;
    private Date giornoLezione;
    private  String nomeDocente;
    private  String nomeStudente;
    private String materia;
    private String email;

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
}
