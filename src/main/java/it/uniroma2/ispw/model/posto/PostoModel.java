package it.uniroma2.ispw.model.posto;

import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;

public class PostoModel {

    String postoId;
    boolean isPrenotato;
    PrenotazionePostoModel ppm;
    String idAula;

    public PostoModel() {
        isPrenotato = false;
    }

    public PostoModel(String idPosto, PrenotazionePostoModel ppm) {
        this.postoId = idPosto;
        this.ppm = ppm;
        isPrenotato = false;
    }


    public void setPpm(PrenotazionePostoModel ppm) {
        this.ppm = ppm;
    }

    public void setPrenotato(boolean prenotato) {
        isPrenotato = prenotato;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getPostoId() {
        return postoId;
    }

    public void setPostoId(String postoId) {
        this.postoId = postoId;
    }

    public boolean isPrenotato() {
        return isPrenotato;
    }

}

