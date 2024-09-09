package it.uniroma2.ispw.view.cli.studente;

import it.uniroma2.ispw.controller.observer.Context;

public class PrenotazionePostoContext implements Context {
private final String postoId;

    public PrenotazionePostoContext(String postoId) {
        this.postoId = postoId;
    }

    public String getPostoId() {
        return postoId;
    }
}
