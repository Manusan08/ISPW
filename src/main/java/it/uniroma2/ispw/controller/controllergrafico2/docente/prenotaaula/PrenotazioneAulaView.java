package it.uniroma2.ispw.controller.controllergrafico2.docente.prenotaaula;

import it.uniroma2.ispw.Façade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciCreazionePrenotazioneAulaController;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.utils.exception.InvalidDataException;

import java.sql.Date;
import java.util.Scanner;

public class PrenotazioneAulaView {

    private GestisciCreazionePrenotazioneAulaController gestisciPrenotazioneController;
    private AulaBean aulaSelezionata;
    private UserBean userBean;
    private ManIntheMiddleFaçade intheMiddleFaçade=new ManIntheMiddleFaçade();
    public PrenotazioneAulaView(AulaBean aulaSelezionata, UserBean usrBean) {

        this.aulaSelezionata = aulaSelezionata;
        this.userBean=usrBean;
    }
    public void start() throws InvalidDataException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci la data della lezione (yyyy-mm-dd): ");
        String dataLezioneStr = scanner.nextLine();
        Date dataLezione = Date.valueOf(dataLezioneStr);

        System.out.println("Inserisci l'orario della lezione (scegli tra le opzioni):");
        System.out.println("1. 12:00-14:00");
        System.out.println("2. 14:00-16:00");
        System.out.println("3. 16:00-18:00");
        System.out.println("4. 18:00-20:00");

        Orario orario = null;
        while (orario == null) {
            System.out.print("Inserisci il numero corrispondente alla fascia oraria: ");
            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    orario = Orario.FASCIAUNO;
                    break;
                case "2":
                    orario = Orario.FASCIADUE;
                    break;
                case "3":
                    orario = Orario.FASCIATRE;
                    break;
                case "4":
                    orario = Orario.FASCIAQUATTRO;
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
        System.out.println("Hai scelto la fascia oraria: " + orario.getFasciaOraria());

        System.out.print("Descrizione della lezione: ");
        String descrizione = scanner.nextLine();

        System.out.print("Materia: ");
        String materia = scanner.nextLine();

       // System.out.print("Nome Professore: ");
        String nomeProfessore = userBean.getNome();

        //System.out.print("Inserisci la tua email: ");
        String emailDocente = userBean.getEmail();

        // Determina se la prenotazione è ricorrente
        System.out.print("Prenotazione ricorrente (true/false): ");
        boolean isRicorrente = scanner.nextBoolean();
        scanner.nextLine(); // Consuma il newline

        Date dataFine = null;


        if (isRicorrente) {
            System.out.print("Inserisci la data di fine ricorrenza (yyyy-mm-dd): ");
            String dataFineStr = scanner.nextLine();
            dataFine = Date.valueOf(dataFineStr);


        }
        PrenotazioneAulaBean prenotazioneAulaBean = new PrenotazioneAulaBean();
        prenotazioneAulaBean.setIdAula(aulaSelezionata.getIdAula());
        prenotazioneAulaBean.setEmail(emailDocente);
        prenotazioneAulaBean.setOraLezione(orario);
        prenotazioneAulaBean.setGiornoLezione(dataLezione);
        prenotazioneAulaBean.setDescrizione(descrizione);
        prenotazioneAulaBean.setMateria(materia);
        prenotazioneAulaBean.setNomeDocente(nomeProfessore);
        prenotazioneAulaBean.setRicorente(isRicorrente);
        prenotazioneAulaBean.setDataFine(dataFine);
        prenotazioneAulaBean.setIdPrenotazioneAula(null);
        // Creazione della prenotazione
        //boolean vif = gestisciPrenotazioneController.prenota(prenotazioneAulaBean);

        boolean vif= intheMiddleFaçade.prenota(prenotazioneAulaBean);

        if (vif = true) {
            System.out.println("Prenotazione creata con successo.");
        } else {
            System.out.println("Prenotazione creata con successo.");
        }
    }

}
