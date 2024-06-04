package it.uniroma2.ispw.utils.factory;


import it.uniroma2.ispw.utils.exception.SystemException;

public class Factory {

    public DialogBox getBox(Exception e){
        if(e instanceof SystemException){
            return (DialogBox) new java.lang.Error();
        }
        else{
            return new Notification();
        }
    }
}
