package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.Prenotazione;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ConfermaPrenotazioneView {
    public boolean confermaPrenotazione(Prenotazione prenotazione) {
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
}
