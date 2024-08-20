package it.uniroma2.ispw.controller.controllergrafico1;


import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.SystemException;

public abstract class ControllerGrafico {
    public abstract void inizializza(UserBean cred) throws SystemException;
}
/*Uso del polimorfismo per istanziare i vari controller grafici, in particolare tutti i controller grafici
  che devono essere aggiornati in base all'utente estendono tale classe implementando il metodo inizializza
 */