package it.uniroma2.ispw.view.cli.docente.prenotaaula;

import it.uniroma2.ispw.utils.facade.DocenteFacade;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;

import it.uniroma2.ispw.enums.Orario;




import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PrenotazioneAulaView {


    private final AulaBean aulaSelezionata;
    private final UserBean userBean;
    private final DocenteFacade docenteFacade = new DocenteFacade();

    public PrenotazioneAulaView(AulaBean aulaSelezionata, UserBean usrBean) {

        this.aulaSelezionata = aulaSelezionata;
        this.userBean = usrBean;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        LocalDate dataLezione = richiediData();
        Orario orario = richiediOrarioLezione();

        System.out.print("Descrizione della lezione: ");
        String descrizione = scanner.nextLine();
        System.out.print("Materia: ");
        String materia = scanner.nextLine();
        System.out.print("Prenotazione ricorrente (true/false): ");
        boolean isRicorrente = scanner.nextBoolean();

        scanner.nextLine();
        LocalDate dataFine = null;

        if (isRicorrente) {
            System.out.print("Inserisci la data di fine ricorrenza (yyyy-mm-dd): ");
            String dataFineStr = scanner.nextLine();
            dataFine = LocalDate.parse(dataFineStr, DateTimeFormatter.ISO_LOCAL_DATE);

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
        ConfermPrenotazioneView.confermaPrenotazione(prenotazioneAulaBean);
        if (docenteFacade.prenota(prenotazioneAulaBean))
            System.out.println("Prenotazione creata con successo.");


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

    private LocalDate richiediData() {
        Scanner scanner = new Scanner(System.in);
        LocalDate dataLezione = null;

        while (dataLezione == null) {
            System.out.print("Inserisci la data della lezione nel formato (yyyy-mm-dd): ");
            String dataLezioneStr = scanner.nextLine();

            try {
                dataLezione = LocalDate.parse(dataLezioneStr, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("Formato data non valido. Riprova.");
            }
        }

        return dataLezione;
    }

}
