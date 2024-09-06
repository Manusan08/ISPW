package it.uniroma2.ispw.controller.controllergrafico1.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;

import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.facade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class MostraTutteLeAuleController extends ControllerGrafico {

    // Creazione di un'istanza del GestisciAuleController per recuperare le aule
    private ManIntheMiddleFaçade intheMiddleFaçade = new ManIntheMiddleFaçade();

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
            } catch (SystemException | SQLException e) {
                getAlert();
            } catch (ItemNotFoundException e) {
                showAlert("Attenzione!","nessuna aula trovata");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            showAlert("Errore","Per favore, seleziona un'aula prima di procedere.");
        }
    }

    @Override
    public void inizializza(UserBean cred) {
        this.userBean = cred;

        aulaBeans = intheMiddleFaçade.getAllAule();
        setAulaBeans(aulaBeans);
    }

    @Override
    public void setAulaBeans(List<AulaBean> aulaBeans) {
        this.aulaBeans = aulaBeans;
        popolaTableView();
    }

    private void popolaTableView() {
        ObservableList<AulaBean> data = FXCollections.observableArrayList(aulaBeans);

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("idAula"));
        colonnaPosti.setCellValueFactory(new PropertyValueFactory<>("posti"));
        colonnaProiettore.setCellValueFactory(new PropertyValueFactory<>("proiettore"));
        colonnaComputer.setCellValueFactory(new PropertyValueFactory<>("computer"));
        colonnaBanchi.setCellValueFactory(new PropertyValueFactory<>("banchiDisegno"));

        // Imposta i dati nella TableView
        tableViewAule.setItems(data);
    }
}
