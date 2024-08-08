package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PrendiDatiPrenotazioneView {
    public void mostraFormPrenotazione() {
        System.out.println("Compila il form di prenotazione:");
    }

    public PrenotazioneBean prendiDatiPrenotazione(AulaBean aulaSelezionata) {
        PrenotazioneBean prenotazione = new PrenotazioneBean();
        prenotazione.setAula(aulaSelezionata);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        System.out.print("Inserisci il nome della materia: ");
        Scanner scanner;
        scanner = null;
        prenotazione.setNomeMateria(scanner.nextLine());

        //forse possiamo prendere automaticamente i dati dalla singleton
        System.out.print("Inserisci il nome del professore: ");
        prenotazione.setNomeProfessore(scanner.nextLine());
        scanner.close();

        try {
            System.out.print("Inserisci l'orario della lezione (dd/MM/yyyy HH:mm): ");
            String orarioInput = scanner.nextLine();
            prenotazione.setOrario(dateFormat.parse(orarioInput));
        } catch (ParseException e) {
            System.out.println("Formato data/ora non valido");
        }
        ConfermaPrenotazioneView confermaPrenotazioneView= new ConfermaPrenotazioneView();
        confermaPrenotazioneView.confermaPrenotazione(prenotazione);
        return prenotazione;
    }
}
