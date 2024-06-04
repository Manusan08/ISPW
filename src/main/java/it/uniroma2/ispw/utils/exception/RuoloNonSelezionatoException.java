package it.uniroma2.ispw.utils.exception;


public class RuoloNonSelezionatoException extends Exception {
    public RuoloNonSelezionatoException(){
        super("Attenzione, selezionare un ruolo");
    }
}
