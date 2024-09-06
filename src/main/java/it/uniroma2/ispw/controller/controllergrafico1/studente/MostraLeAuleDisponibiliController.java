package it.uniroma2.ispw.controller.controllergrafico1.studente;

import it.uniroma2.ispw.Façade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazionePostoController;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MostraLeAuleDisponibiliController extends ControllerGrafico {

    private UserBean userBean;
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

    }

    @Override
    public void inizializza(UserBean cred) {
        this.userBean = cred;
        final ObservableList<PrenotazioneAulaBean> data =
                FXCollections.observableArrayList(
                        new ManIntheMiddleFaçade().getAvailableClass());

        colonna1.setCellValueFactory(
                new PropertyValueFactory<>("IdAula"));
        colonna2.setCellValueFactory(
                new PropertyValueFactory<>("OraLezione1"));
        tableViewPrenotazioni.setSelectionModel(null);
        tableViewPrenotazioni.setItems(data);

    }


}
