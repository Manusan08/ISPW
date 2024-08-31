package it.uniroma2.ispw.controller.controllergrafico1.studente;

import it.uniroma2.ispw.Façade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
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

import java.sql.SQLException;

public class MostraTutteLeAulePrenotabiliController extends ControllerGrafico {
    @FXML
    private Button avantiID;

    @FXML
    private TableColumn<?, ?> colonna1;

    @FXML
    private TableColumn<?, ?> colonna2;

    @FXML
    private TableColumn<?, ?> colonna3;

    @FXML
    private TableColumn<?, ?> colonna4;

    @FXML
    private TableColumn<?, ?> colonna5;


    private PrenotazioneAulaBean pab;
    private UserBean userBean;

    @FXML
    private TableView<PrenotazioneAulaBean> tableViewAule;

    @FXML
    void avantiBottoneAction(ActionEvent event) {
        PrenotazioneAulaBean selected = tableViewAule.getSelectionModel().getSelectedItem();

        if (selected != null) {
            try {
                ChangePage.getChangePage().cambiaPagina("/view/Studente/SelezionePosti.fxml", userBean, selected);
            } catch (SystemException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Nessuna Aula Selezionata");
            alert.setContentText("Per favore, seleziona un'aula prima di procedere.");
            alert.showAndWait();
        }
    }

    @Override
    public void inizializza(UserBean cred) throws SystemException, SQLException {
        this.userBean = cred;

    }

    @Override
    public void setPrenotazioneAulaBeans(PrenotazioneAulaBean pab) throws SQLException {
        this.pab = pab;
        tableInitializator(userBean);

    }

    public void tableInitializator(UserBean userBean) throws SQLException {
        final ObservableList<PrenotazioneAulaBean> data =
                FXCollections.observableArrayList(
                        new ManIntheMiddleFaçade().searchBySurnameAndSubject(pab, userBean));


        colonna1.setCellValueFactory(
                new PropertyValueFactory<>("Materia"));
        colonna2.setCellValueFactory(
                new PropertyValueFactory<>("OraLezione1"));
        colonna3.setCellValueFactory(
                new PropertyValueFactory<>("NomeDocente"));
        colonna4.setCellValueFactory(
                new PropertyValueFactory<>("GiornoLezione"));
        colonna5.setCellValueFactory(
                new PropertyValueFactory<>("IdAula"));

        tableViewAule.setItems(data);
    }
}

