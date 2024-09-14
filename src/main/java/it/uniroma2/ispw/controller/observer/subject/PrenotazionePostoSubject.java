package it.uniroma2.ispw.controller.observer.subject;

public class PrenotazionePostoSubject extends Subject{
private Status status;
String idPrenotazioneAula;

    public PrenotazionePostoSubject() {
        this.status=Status.NON_EFFETTUATA;
    }

    private void setStatus(Status s) {
        if (this.status != s) {
            this.status = s;
        }
    }

    public void setStatusEffettuata() {
        this.setStatus(Status.EFFETTUATA);
    }

    public void setStatusNonEffetutata() {
        this.setStatus(Status.NON_EFFETTUATA);
    }

    public Status getStatus() {
        return status;
    }

    public void setIdPrenotazioneAula(String idPrenotazioneAula) {
        this.idPrenotazioneAula=idPrenotazioneAula;
    }

}
