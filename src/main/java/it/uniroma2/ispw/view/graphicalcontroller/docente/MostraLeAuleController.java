package it.uniroma2.ispw.view.graphicalcontroller.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
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
import java.util.List;

public class MostraLeAuleController extends ControllerGrafico {
    @FXML
    public Button indietroButton;
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

                getAlert().showAndWait();
            } catch (ItemNotFoundException e) {
                getAlert(e.getMessage());
            }
        } else {
            showAlert("Errore","Per favore, seleziona un'aula prima di procedere.");

        }
    }

    @Override
    public void inizializza(UserBean cred){
        this.userBean = cred;
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
        System.out.println("Numero di aule nella TableView: " + data.size());
        for (AulaBean aula : data) {
            System.out.println("Aula: " + aula.getIdAula() + ", Posti: " + aula.getPosti() +
                    ", Proiettore: " + aula.isProiettore() +
                    ", Computer: " + aula.isComputer() +
                    ", BanchiDisegno: " + aula.isBanchiDisegno());
        }


        // Imposta i dati nella TableView
        tableViewAule.setItems(data);
    }

    public void indietro(ActionEvent event) {
        try {
            ChangePage.getChangePage().cambiaPagina("/view/Docente/CercaFiltri.fxml", this.userBean);
        } catch (SQLException | ItemNotFoundException | SystemException e) {
            getAlert().showAndWait();
        }

    }
}
