package it.uniroma2.ispw.controller.controllergrafico1;

import it.uniroma2.ispw.bean.IdSessioneBean;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.utils.exception.SystemException;

public abstract class ControllerGrafico {
    public abstract void inizializza(IdSessioneBean id, LoginBean cred) throws SystemException;
}
/*Uso del polimorfismo per istanziare i vari controller grafici, in particolare tutti i controller grafici
  che devono essere aggiornati in base all'utente estendono tale classe implementando il metodo inizializza
 */