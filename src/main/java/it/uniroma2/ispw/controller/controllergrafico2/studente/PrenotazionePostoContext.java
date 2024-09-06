package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.controller.controllerapplicativo.observer.Context;

public class PrenotazionePostoContext implements Context {
private final String postoId;

    public PrenotazionePostoContext(String postoId) {
        this.postoId = postoId;
    }

    public String getPostoId() {
        return postoId;
    }
}
