package it.uniroma2.ispw.controller.controllerApplicativo.Observer.observers;

import it.uniroma2.ispw.controller.controllerApplicativo.Observer.Context;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.subject.PrenotazionePostoSubject;
import it.uniroma2.ispw.controller.controllergrafico2.studente.PrenotazionePostoContext;

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

//TODO forse anzichè posto contentex gli passero' un posto bean.
    @Override
    public void update(Context context) {
        if (context instanceof PrenotazionePostoContext ppc) {
            switch (this.pps.getStatus()) {
                case EFFETTUATA:
                    if (this.postoId.equals(ppc.getPostoId())) {
                        this.setPrenotato(true);
                    }
                    else{
                        this.setPrenotato(false);
                    }
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

