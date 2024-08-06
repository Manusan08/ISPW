package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.Prenotazione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PrendiDatiPrenotazioneView {
    public void mostraFormPrenotazione() {
        System.out.println("Compila il form di prenotazione:");
    }

    public Prenotazione prendiDatiPrenotazione(AulaBean aulaSelezionata) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setAula(aulaSelezionata);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        System.out.print("Inserisci il nome della materia: ");
        Scanner scanner;
        scanner = null;
        prenotazione.setNomeMateria(scanner.nextLine());

        System.out.print("Inserisci il nome del professore: ");
        prenotazione.setNomeProfessore(scanner.nextLine());

        try {
            System.out.print("Inserisci l'orario della lezione (dd/MM/yyyy HH:mm): ");
            String orarioInput = scanner.nextLine();
            prenotazione.setOrario(dateFormat.parse(orarioInput));
        } catch (ParseException e) {
            System.out.println("Formato data/ora non valido");
        }

        return prenotazione;
    }
}
