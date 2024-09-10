package it.uniroma2.ispw.view.graphicalcontroller.docente;



import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;

import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import it.uniroma2.ispw.utils.facade.DocenteFacade;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.CheckBox;
        import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public  class  CercaAulaFiltriController extends ControllerGrafico {
    @FXML
    public Button indietroButton;
    private UserBean userBean;
@FXML
private CheckBox checkBanchi;

@FXML
private CheckBox checkComputer;

@FXML
private CheckBox checkProiettore;

@FXML
private Button avantiBottoneID;

@FXML
private TextField txtNumeroPosti;
    private final DocenteFacade docenteFacade = new DocenteFacade();

@FXML
    void avantiAction(ActionEvent event) {
    try  {
        // Recupera i dati dall'interfaccia utente
        int postiMinimi = Integer.parseInt(txtNumeroPosti.getText());
        boolean proiettoreRichiesto = checkProiettore.isSelected();
        boolean computerRichiesto = checkComputer.isSelected();
        boolean banchiRichiesti = checkBanchi.isSelected();

        // Crea un'istanza di AulaBean con i filtri selezionati
        AulaBean aulaBean = new AulaBean();
        aulaBean.setPosti(postiMinimi);

        aulaBean.setProiettore(proiettoreRichiesto);
        aulaBean.setComputer(computerRichiesto);
        aulaBean.setBanchiDisegno(banchiRichiesti);

        // Chiamata al controller applicativo per cercare le aule disponibili


        List<AulaBean> auleDisponibili = docenteFacade.chekAula(aulaBean);

        ChangePage.getChangePage().cambiaPagina("/view/Docente/MostratutteleAule.fxml", userBean, auleDisponibili);
    } catch (ItemNotFoundException e) {
        getAlert(e.getMessage());
    } catch (SQLException e) {
        getAlert().showAndWait();
    }

}


    @Override
    public void inizializza(UserBean cred) {
        this.userBean=cred;
    }


    public void indietro(ActionEvent event) {
        try {
            ChangePage.getChangePage().cambiaPagina("/view/Docente/CercaAula.fxml", this.userBean);
        } catch (SQLException | ItemNotFoundException e) {
            getAlert().showAndWait();
        }

    }
}