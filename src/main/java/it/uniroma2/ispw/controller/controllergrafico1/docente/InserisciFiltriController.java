package it.uniroma2.ispw.controller.controllergrafico1.docente;



        import it.uniroma2.ispw.Façade.ManIntheMiddleFaçade;
        import it.uniroma2.ispw.bean.AulaBean;
        import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
        import it.uniroma2.ispw.bean.UserBean;
        import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
        import it.uniroma2.ispw.enums.Orario;
        import it.uniroma2.ispw.utils.ChangePage;
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
    private  ManIntheMiddleFaçade intheMiddleFaçade=new ManIntheMiddleFaçade();

    @FXML
    void avantiBottoneAction(ActionEvent event) {
        try {
            // Acquisizione dati dall'interfaccia utente
            Date dataLezione = Date.valueOf(dateID.getValue());

            // Usa il metodo Orario.fromString() per ottenere l'enum corretto
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
            boolean risultato = intheMiddleFaçade.prenota(prenotazioneAulaBean);

            // Mostra il risultato su una nuova finestra o pop-up
            if (risultato) {
                // Mostra un pop-up di conferma
                Alert alert = getAlert();

                // Mostra il pop-up
                alert.showAndWait();
            } else {
                // Gestione dell'errore in caso di prenotazione fallita
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore di Prenotazione");
                alert.setHeaderText(null);
                alert.setContentText("Si è verificato un errore durante la prenotazione. Riprova.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Gestione dell'errore per qualsiasi eccezione
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Si è verificato un errore: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private Alert getAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Prenotazione Confermata");
        alert.setHeaderText(null);
        alert.setContentText("La prenotazione è stata confermata con successo!");

        // Cambia pagina al momento della chiusura del pop-up
        alert.setOnHidden(evt -> {
            try {
                // Passa l'aula selezionata alla prossima pagina
                ChangePage.getChangePage().cambiaPagina("/view/HomeDocente.fxml", userBean);
            } catch (SystemException e) {
                // Gestisci l'eccezione
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return alert;
    }


    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean=cred;
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
    public void setAulaBean(AulaBean aulaBean){
        this.aulaBean=aulaBean;
    }


}

