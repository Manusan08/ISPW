package it.uniroma2.ispw.view.graphicalcontroller.studente;

import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.facade.ManIntheMiddleFacade;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class MostraLeAuleDisponibiliController extends ControllerGrafico {

    private UserBean userBean;
    @FXML
    public Button indietroButton;
    @FXML
    private Button avantiID;

    @FXML
    private TableColumn<?, ?> colonna1;

    @FXML
    private TableColumn<?, ?> colonna2;

    @FXML
    private TableView<PrenotazioneAulaBean> tableViewPrenotazioni;

    @FXML
    void avantiBottoneAction(ActionEvent event) {
        //da fare

    }

    @Override
    public void inizializza(UserBean cred) {
        this.userBean = cred;
        final ObservableList<PrenotazioneAulaBean> data =
                FXCollections.observableArrayList(
                        new ManIntheMiddleFacade().getAvailableClass());

        colonna1.setCellValueFactory(
                new PropertyValueFactory<>("IdAula"));
        colonna2.setCellValueFactory(
                new PropertyValueFactory<>("OraLezione1"));
        tableViewPrenotazioni.setSelectionModel(null);
        tableViewPrenotazioni.setItems(data);

    }


    public void indietro(ActionEvent event) {

        try {
            ChangePage.getChangePage().cambiaPagina("/view/Studente/CercaConFiltri.fxml", this.userBean);
        } catch (SQLException | ItemNotFoundException e) {
            getAlert().showAndWait();
        }
    }
}
