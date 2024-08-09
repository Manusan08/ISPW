package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazioneAulaController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.utils.DateParser;
import it.uniroma2.ispw.utils.exception.InvalidDataException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class PrenotaAulaView extends TemplateView {
    @Override
    public void control() throws InvalidDataException, IOException {
        int choice;
        boolean cond = true;

        while (cond) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> prenotaAulaDisponibile();
                case 2 -> cond = false;
                default -> System.out.println("riprova");
            }
        }
    }

    private void prenotaAulaDisponibile() throws InvalidDataException, IOException {
        AulaBean ab;
        ab = ricercaPerCampi();
        printTable(new GestisciPrenotazioneAulaController().getAuleByDay(ab));

        if (prenotaAula(ab)) {
            inserisciCampiDiricerca(ab);
        } else {
            System.out.println("la prenotazione non è andata a buon fine");
        }

    }

    private boolean prenotaAula(AulaBean ab) throws IOException {
        String aulaId = getDesiredIn("Id aula:", "inserisci idAula che vuoi prenotare");
        ab.setIdAula(aulaId);
        return new GestisciPrenotazioneAulaController().prenota();

    }
    //TODO Da valutare con i pattern
    private AulaBean ricercaPerCampi() throws IOException, InvalidDataException {
        printHeader("inserisci campi di ricerca:");

        AulaBean ab = new AulaBean();
        String inputStr = getDesiredIn("Ora lezione", "Inserisci l'orario della lezione (dd/MM/yyyy HH:mm):");
        java.sql.Date dataLezione = DateParser.parseStringToDate(inputStr);
        ab.setOrarioLezione(dataLezione);

        return ab;
    }

    public AulaBean inserisciCampiDiricerca(AulaBean ab) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Inserisci il nome del docente: ");
            ab.setNomeDocente(scanner.nextLine());

            System.out.print("Inserisci la materia: ");
            ab.setMateria(scanner.nextLine());

            System.out.print("Inserisci la descrizione: ");
            ab.setDescrizione(scanner.nextLine());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        scanner.close();
        return ab;
    }


    //TODO forse si puo' inserire per la conferma
    public boolean confermaPrenotazione(PrenotazioneAulaBean prenotazione) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println("Conferma i dettagli della prenotazione:");
        System.out.println("Materia: " + prenotazione.getNomeMateria());
        System.out.println("Professore: " + prenotazione.getNomeProfessore());
        System.out.println("Orario: " + dateFormat.format(prenotazione.getOrario()));
        System.out.print("Confermi? (s/n): ");
        Scanner scanner = null;
        String conferma = scanner.nextLine();
        return conferma.equalsIgnoreCase("s");
    }

    @Override
    protected List<String> getOptions() {
        return List.of("mostra aule disponibili");
    }

    @Override
    protected String getHeader() {
        return "Prenotazione Aula";
    }


}
