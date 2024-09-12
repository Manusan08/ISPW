package it.uniroma2.ispw.view.graphicalcontroller.studente;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.facade.StudenteFacade;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
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
    public Button indietroButton;
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
            } catch (SQLException | SystemException e) {
                getAlert().showAndWait();
            } catch (ItemNotFoundException e) {
                getAlert(e.getMessage()).showAndWait();
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
    public void inizializza(UserBean cred) {
        this.userBean = cred;

    }

    @Override
    public void setPrenotazioneAulaBeans(PrenotazioneAulaBean pab)  {
        this.pab = pab;
        try {
            tableInitializator(userBean);
        } catch ( SystemException e) {
            getAlert("Ops, qualcosa è andato storto");
        } catch (ItemNotFoundException e) {
            getAlert(e.getMessage()).showAndWait();
        }
    }

    public void tableInitializator(UserBean userBean) throws  ItemNotFoundException, SystemException {
        final ObservableList<PrenotazioneAulaBean> data =
                FXCollections.observableArrayList(
                        new StudenteFacade().searchBySurnameAndSubject(pab, userBean));


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

    public void indietro(ActionEvent event) {
        try {
            ChangePage.getChangePage().cambiaPagina("/view/Studente/CercaConFiltri.fxml", this.userBean);
        } catch (SQLException | ItemNotFoundException | SystemException e) {
            getAlert().showAndWait();
        }
    }
}

