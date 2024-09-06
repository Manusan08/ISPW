package it.uniroma2.ispw.controller.controllergrafico2.docente.prenotaaula;

import it.uniroma2.ispw.Façade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciCreazionePrenotazioneAulaController;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.utils.DateParser;
import it.uniroma2.ispw.utils.exception.FormatoDataNonValidoException;

import java.sql.Date;
import java.util.Scanner;

public class PrenotazioneAulaView {

    private GestisciCreazionePrenotazioneAulaController gestisciPrenotazioneController;
    private AulaBean aulaSelezionata;
    private UserBean userBean;
    private ManIntheMiddleFaçade intheMiddleFaçade = new ManIntheMiddleFaçade();

    public PrenotazioneAulaView(AulaBean aulaSelezionata, UserBean usrBean) {

        this.aulaSelezionata = aulaSelezionata;
        this.userBean = usrBean;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        try {

            Date dataLezione = richiediData();
            Orario orario = richiediOrarioLezione();

            System.out.print("Descrizione della lezione: ");
            String descrizione = scanner.nextLine();
            System.out.print("Materia: ");
            String materia = scanner.nextLine();
            System.out.print("Prenotazione ricorrente (true/false): ");
            boolean isRicorrente = scanner.nextBoolean();

            scanner.nextLine();
            Date dataFine = null;

            if (isRicorrente) {
                System.out.print("Inserisci la data di fine ricorrenza (yyyy-mm-dd): ");
                String dataFineStr = scanner.nextLine();
                dataFine = Date.valueOf(dataFineStr);

            }
            PrenotazioneAulaBean prenotazioneAulaBean = new PrenotazioneAulaBean();

            prenotazioneAulaBean.setIdAula(aulaSelezionata.getIdAula());
            prenotazioneAulaBean.setEmail(userBean.getEmail());
            prenotazioneAulaBean.setOraLezione(orario);
            prenotazioneAulaBean.setGiornoLezione(dataLezione);
            prenotazioneAulaBean.setDescrizione(descrizione);
            prenotazioneAulaBean.setMateria(materia);
            prenotazioneAulaBean.setNomeDocente(userBean.getNome());
            prenotazioneAulaBean.setRicorente(isRicorrente);
            prenotazioneAulaBean.setDataFine(dataFine);

            if (intheMiddleFaçade.prenota(prenotazioneAulaBean))
                System.out.println("Prenotazione creata con successo.");

        } catch (FormatoDataNonValidoException e) {
            System.out.println(e.getMessage());
        }

    }

    private Orario richiediOrarioLezione() {
        Scanner scanner = new Scanner(System.in);
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

        return orario;
    }

    private Date richiediData() throws FormatoDataNonValidoException {
        Scanner scanner = new Scanner(System.in);
        Date dataLezione = null;

        while (dataLezione == null) {
            System.out.print("Inserisci la data della lezione nel formato (yyyy-mm-dd): ");
            String dataLezioneStr = scanner.nextLine();

            try {
                dataLezione = DateParser.parseStringToDate(dataLezioneStr);
            } catch (FormatoDataNonValidoException e) {
                System.out.println("Formato data non valido. Riprova.");
            }
        }

        return dataLezione;
    }

}
