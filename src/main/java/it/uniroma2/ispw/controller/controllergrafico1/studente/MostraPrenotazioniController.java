package it.uniroma2.ispw.controller.controllergrafico1.studente;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazionePostoController;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class MostraPrenotazioniController extends ControllerGrafico {

    private UserBean userBean;

    @FXML
    private Button avantiID;

    @FXML
    private TableColumn<PrenotazionePostoBean, Date> colonna1;
    @FXML
    private TableColumn<PrenotazionePostoBean, String> colonna2;
    @FXML
    private TableColumn<PrenotazionePostoBean, String> colonna3;
    @FXML
    private TableColumn<PrenotazionePostoBean, String> colonna4;
    @FXML
    private TableColumn<PrenotazionePostoBean, String> colonna5;

    @FXML
    private TableView<PrenotazionePostoBean> tableViewPrenotazioni;

    @FXML
    void avantiBottoneAction(ActionEvent event) {
        // Azione del bottone avanti
    }


    @Override
    public void inizializza(UserBean cred) {
        this.userBean = cred;
        //TODO bisogna chiamare il façade non il controller!
        final ObservableList<PrenotazionePostoBean> data;
        try {
            data = FXCollections.observableArrayList(
                    new GestisciPrenotazionePostoController().getAllReservation(cred));
            colonna1.setCellValueFactory(
                    new PropertyValueFactory<>("OraLezione"));
            colonna2.setCellValueFactory(
                    new PropertyValueFactory<>("NomeDocente"));
            colonna3.setCellValueFactory(
                    new PropertyValueFactory<>("IdPrenotazionePosto"));
            colonna4.setCellValueFactory(
                    new PropertyValueFactory<>("IdPosto"));
            colonna5.setCellValueFactory(
                    new PropertyValueFactory<>("IdAula"));
            tableViewPrenotazioni.setItems(data);
        } catch (ItemNotFoundException e) {
            getAlert(e.getMessage()).showAndWait();
        }


    }

}
