package it.uniroma2.ispw.controller.observer.observers;

import it.uniroma2.ispw.controller.observer.Context;
import it.uniroma2.ispw.controller.observer.subject.PrenotazionePostoSubject;
import it.uniroma2.ispw.view.cli.studente.PrenotazionePostoContext;

public class PostoObserver implements Observer {
    String postoId;
    boolean isPrenotato;
    PrenotazionePostoSubject pps;
    String  idAula;

    public PostoObserver() {

    }

    public boolean isPrenotato() {
        return isPrenotato;
    }
    public void setPrenotato(boolean prenotato) {
        isPrenotato = prenotato;
    }
    public String getPostoId(){
        return this.postoId;
    }

    public String getIdAula(){
        return this.idAula;
    }

    public PostoObserver(String idPosto, String idAula, PrenotazionePostoSubject pps) {
        this.postoId = idPosto;
        this.idAula=idAula;
        this.pps = pps;
        this.isPrenotato = false;
    }


    @Override
    public void update(Context context) {
        if (context instanceof PrenotazionePostoContext ppc) {
            switch (this.pps.getStatus()) {
                case EFFETTUATA:
                    this.setPrenotato(this.postoId.equals(ppc.getPostoId()));
                    break;
                case NON_EFFETTUATA:
                    this.setPrenotato(false);
                    break;
                default:
                    break;
            }
        }
    }
}

