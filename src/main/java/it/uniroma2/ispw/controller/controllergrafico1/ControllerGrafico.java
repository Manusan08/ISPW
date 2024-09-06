package it.uniroma2.ispw.controller.controllergrafico1;


import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.AlertUtil;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.List;


public abstract class ControllerGrafico extends AlertUtil {


    public abstract void inizializza(UserBean cred);

    public void setAulaBeans(List<AulaBean> aulaBeans) {

    }
    public  void setAulaBean(AulaBean aulaBean){}

    public void setPrenotazioneAulaBeans(PrenotazioneAulaBean pab) throws SQLException, ItemNotFoundException {
    }



}
/*Uso del polimorfismo per istanziare i vari controller grafici, in particolare tutti i controller grafici
  che devono essere aggiornati in base all'utente estendono tale classe implementando il metodo inizializza
 */