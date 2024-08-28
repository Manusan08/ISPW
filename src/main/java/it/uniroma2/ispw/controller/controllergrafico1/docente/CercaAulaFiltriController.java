package it.uniroma2.ispw.controller.controllergrafico1.docente;



import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciAuleController;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.CheckBox;
        import javafx.scene.control.TextField;

import java.util.List;

public  class  CercaAulaFiltriController extends ControllerGrafico {
private UserBean userBean;
@FXML
private CheckBox CheckBanchi;

@FXML
private CheckBox CheckComputer;

@FXML
private CheckBox CheckProiettore;

@FXML
private Button avantiBottoneID;

@FXML
private TextField txtNumeroPosti;

@FXML
    void avantiAction(ActionEvent event) {
    try  {
        // Recupera i dati dall'interfaccia utente
        int postiMinimi = Integer.parseInt(txtNumeroPosti.getText());
        boolean proiettoreRichiesto = CheckProiettore.isSelected();
        boolean computerRichiesto = CheckComputer.isSelected();
        boolean banchiRichiesti = CheckBanchi.isSelected();

        // Crea un'istanza di AulaBean con i filtri selezionati
        AulaBean aulaBean = new AulaBean();
        aulaBean.setPosti(postiMinimi);

        aulaBean.setProiettore(proiettoreRichiesto);
        aulaBean.setComputer(computerRichiesto);
        aulaBean.setBanchiDisegno(banchiRichiesti);

        // Chiamata al controller applicativo per cercare le aule disponibili
        GestisciAuleController gestisciAuleController;
        gestisciAuleController = new GestisciAuleController();
        List<AulaBean> auleDisponibili = gestisciAuleController.chekAula(aulaBean);

        ChangePage.getChangePage().cambiaPagina("/view/Docente/MostratutteleAule.fxml", userBean, auleDisponibili);
    } catch (SystemException e) {
        // Gestisci l'eccezione
        e.printStackTrace();
    } catch (ItemNotFoundException e) {
        throw new RuntimeException(e);
    }

}


    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean=cred;
    }


}