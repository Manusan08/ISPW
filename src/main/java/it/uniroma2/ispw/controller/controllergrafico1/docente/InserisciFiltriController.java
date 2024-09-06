package it.uniroma2.ispw.controller.controllergrafico1.docente;


import it.uniroma2.ispw.Façade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.sql.SQLException;

public class InserisciFiltriController extends ControllerGrafico {
    private UserBean userBean;
    private AulaBean aulaBean;
    @FXML
    private ChoiceBox<String> orarioID;

    @FXML
    private Button avantiID;

    @FXML
    private DatePicker dateFineID;

    @FXML
    private DatePicker dateID;

    @FXML
    private TextField descrizioneID;

    @FXML
    private TextField materiaID;

    @FXML
    private CheckBox ricorrenteID;
    private ManIntheMiddleFaçade intheMiddleFaçade = new ManIntheMiddleFaçade();

    @FXML
    void avantiBottoneAction(ActionEvent event) {
        try {
            Date dataLezione = Date.valueOf(dateID.getValue());

            Orario orario = Orario.fromString(orarioID.getValue());
            if (orario == null) {
                throw new IllegalArgumentException("L'orario selezionato non è valido.");
            }

            String descrizione = descrizioneID.getText();
            String materia = materiaID.getText();
            boolean isRicorrente = ricorrenteID.isSelected();
            Date dataFine = isRicorrente ? Date.valueOf(dateFineID.getValue()) : null;

            // Creazione del bean per la prenotazione
            PrenotazioneAulaBean prenotazioneAulaBean = new PrenotazioneAulaBean();
            prenotazioneAulaBean.setIdAula(aulaBean.getIdAula());
            prenotazioneAulaBean.setGiornoLezione(dataLezione);
            prenotazioneAulaBean.setOraLezione(orario);
            prenotazioneAulaBean.setDescrizione(descrizione);
            prenotazioneAulaBean.setMateria(materia);
            prenotazioneAulaBean.setNomeDocente(userBean.getNome());
            prenotazioneAulaBean.setEmail(userBean.getEmail());
            prenotazioneAulaBean.setRicorente(isRicorrente);
            prenotazioneAulaBean.setDataFine(dataFine);

            // Chiamata al controller applicativo per gestire la prenotazione
            getAlert(intheMiddleFaçade.prenota(prenotazioneAulaBean)).showAndWait();
        } catch (Exception e) {
            getAlert();
        }
    }

    protected Alert getAlert(boolean risultato) {
        Alert alert;
        if (risultato) {
            alert = showSuccess("Prenotazione Confermata", "La prenotazione è stata confermata con successo!");
        } else {
            alert = showSuccess("Errore di prenotazione", "si è verificato un errore durante la prenotazione.Riprova.");
        }
        // Cambia pagina al momento della chiusura del pop-up
        alert.setOnHidden(evt -> {
            try {
                // Passa l'aula selezionata alla prossima pagina
                ChangePage.getChangePage().cambiaPagina("/view/HomeDocente.fxml", userBean);
            } catch (SystemException | SQLException e) {
                getAlert().showAndWait();

            } catch (ItemNotFoundException e) {
                showAlert("Errore", e.getMessage());
            }
        });
        return alert;
    }


    @Override
    public void inizializza(UserBean cred) {
        this.userBean = cred;
        for (Orario orario : Orario.values()) {
            orarioID.getItems().add(orario.getFasciaOraria());
        }
        ricorrenteID.selectedProperty().addListener((observable, oldValue, newValue) -> {
            dateFineID.setDisable(!newValue);
        });

        // Inizializza il DatePicker dateFineID come disabilitato all'avvio
        dateFineID.setDisable(true);
    }

    @Override
    public void setAulaBean(AulaBean aulaBean) {
        this.aulaBean = aulaBean;
    }


}

