package it.uniroma2.ispw.controller.controllergrafico1.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciAuleController;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

public class MostraTutteLeAuleController extends ControllerGrafico {

    // Creazione di un'istanza del GestisciAuleController per recuperare le aule
    GestisciAuleController gestisciAuleController = new GestisciAuleController();

    private UserBean userBean;
    private List<AulaBean> aulaBeans;

    @FXML
    private Button avantiID;

    @FXML
    private TableView<AulaBean> tableViewAule;

    @FXML
    private TableColumn<AulaBean, String> colonnaNome;

    @FXML
    private TableColumn<AulaBean, Integer> colonnaPosti;

    @FXML
    private TableColumn<AulaBean, Boolean> colonnaProiettore;

    @FXML
    private TableColumn<AulaBean, Boolean> colonnaComputer;

    @FXML
    private TableColumn<AulaBean, Boolean> colonnaBanchi;

    @FXML
    void avantiBottoneAction(ActionEvent event) {
        AulaBean selectedAula = tableViewAule.getSelectionModel().getSelectedItem();

        if (selectedAula != null) {
            try {
                // Passa l'aula selezionata alla prossima pagina
                ChangePage.getChangePage().cambiaPagina("/view/Docente/FiltriInseriti.fxml", userBean, selectedAula);
            } catch (SystemException e) {
                // Gestisci l'eccezione
                e.printStackTrace();
            }
        } else {
            // Mostra un messaggio di errore se nessuna aula è selezionata
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Nessuna Aula Selezionata");
            alert.setContentText("Per favore, seleziona un'aula prima di procedere.");
            alert.showAndWait();
        }
    }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean = cred;

        // Recupero delle aule e popolamento della TableView
        aulaBeans = gestisciAuleController.getAllAule();
        setAulaBeans(aulaBeans);
    }

    @Override
    public void setAulaBeans(List<AulaBean> aulaBeans) {
        this.aulaBeans = aulaBeans;
        popolaTableView();
    }

    private void popolaTableView() {
        ObservableList<AulaBean> data = FXCollections.observableArrayList(aulaBeans);

        // Configura le colonne della TableView con i valori degli oggetti AulaBean
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("idAula"));
        colonnaPosti.setCellValueFactory(new PropertyValueFactory<>("posti"));
        colonnaProiettore.setCellValueFactory(new PropertyValueFactory<>("proiettore"));
        colonnaComputer.setCellValueFactory(new PropertyValueFactory<>("computer"));
        colonnaBanchi.setCellValueFactory(new PropertyValueFactory<>("banchiDisegno"));

        // Imposta i dati nella TableView
        tableViewAule.setItems(data);
    }
}
