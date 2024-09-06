package it.uniroma2.ispw.controller.controllergrafico1.studente;

import it.uniroma2.ispw.facade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.bean.PostoBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.controller.controllergrafico2.studente.PrenotazionePostoContext;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

import java.sql.SQLException;
import java.util.List;

public class SelezionePostiController extends ControllerGrafico {

    @FXML
    private HBox premiumHbox;

    @FXML
    private AnchorPane seatsPane;

    @FXML
    private Button cercaAulaID;

    @FXML
    private Button cercaAulaProfMat;

    @FXML
    private Button confirmButton;

    private PrenotazioneAulaBean pab;
    private UserBean usr;
    private ManIntheMiddleFaçade manIntheMiddleFaçade = new ManIntheMiddleFaçade();
    private List<PostoBean> postoBeans;

    @FXML
    void prenota(ActionEvent event) throws SystemException, SQLException {
    PostoBean postoBean= getPrenotato(postoBeans);
    String msg= manIntheMiddleFaçade.insertPostoIntodb(postoBean,pab,this.usr);
        Alert alert = getAlert(msg);
        alert.showAndWait();

    }

    private PostoBean getPrenotato(List<PostoBean> postoBeans) {
        for (PostoBean postoBean : postoBeans) {
            if(postoBean.isPrenotato())
                return postoBean;
        }
    return null;
    }

    @FXML
    void cercaAulaperProfEMateria(ActionEvent event) {
    }

    @Override
    public void setPrenotazioneAulaBeans(PrenotazioneAulaBean pab) {
        this.pab = pab;

        int capienzaAula = 0;
        try {
            capienzaAula = manIntheMiddleFaçade.getCapienzaAula(pab.getIdAula());
            this.postoBeans = manIntheMiddleFaçade.getPostiBean(pab);
            postiPrinter(postoBeans, capienzaAula);
        } catch (SQLException e) {
            getAlert();
        }
        double paneWidth = Screen.getPrimary().getBounds().getWidth();
        seatsPane.setPrefWidth(paneWidth);

    }

    @Override
    public void inizializza(UserBean cred) {
        this.usr = cred;
    }

    public static PostoBean isPostoBeanInlist(List<PostoBean> postoBeans, String posto) {
        for (PostoBean pb : postoBeans) {
            if (pb.getIdPosto().equals(posto)) {
                return pb;
            }
        }
        return null;
    }

    public void postiPrinter(List<PostoBean> posti, int capienza) {
        int index = 0;
        String postoId = posti.get(0).getIdPosto();
        char firstchar = postoId.charAt(0);

        GridPane selectSeatsWrap1 = new GridPane();
        selectSeatsWrap1.setHgap(10);

        for (int i = capienza; i >= 0; i--) {
            String control = firstchar + String.valueOf(index);
            Button btn = new Button();
            btn.setText(control);
            PostoBean posto = isPostoBeanInlist(posti, control);
            btn.setOnAction(event -> handleSelection(event, capienza));

            if (posto != null) {
                if (!posto.isPrenotato()) {
                    btn.getStyleClass().add("available-seats");
                } else {
                    btn.getStyleClass().add("selected-seats");
                }
            } else {
                btn.setDisable(true);
                btn.getStyleClass().add("booked-seats");
            }
            selectSeatsWrap1.add(btn, i % 15, i / 15);
            index++;
        }
        premiumHbox.getChildren().add(selectSeatsWrap1);
    }

    public void handleSelection(ActionEvent event, int capienza) {
        Button btn = ((Button) event.getSource());
        PrenotazionePostoContext ppc = new PrenotazionePostoContext(btn.getText());
        postoBeans = manIntheMiddleFaçade.selezionaPosto(ppc);
        premiumHbox.getChildren().clear();
        postiPrinter(postoBeans, capienza);
        confirmButton.setVisible(true);

    }


    @Override
    protected Alert getAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Prenotazione Confermata");
        alert.setHeaderText(null);
        alert.setContentText(msg);


        alert.setOnHidden(evt -> {
            try {
                ChangePage.getChangePage().cambiaPagina("/view/HomeStudente.fxml", usr);
            } catch (SQLException e) {
                getAlert();
            } catch (ItemNotFoundException e) {
               getAlert(e.getMessage());
            }
        });
        return alert;
    }

}
