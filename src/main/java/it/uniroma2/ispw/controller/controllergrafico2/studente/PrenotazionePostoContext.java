package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.controller.controllerApplicativo.Observer.Context;

public class PrenotazionePostoContext implements Context {
private String PostoId;

    public PrenotazionePostoContext(String postoId) {
        PostoId = postoId;
    }

    public String getPostoId() {
        return PostoId;
    }
}
