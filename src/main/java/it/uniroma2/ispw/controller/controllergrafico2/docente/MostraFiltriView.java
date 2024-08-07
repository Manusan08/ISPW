package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.controller.controllerApplicativo.decoretor.AulaComponent;
import it.uniroma2.ispw.controller.controllerApplicativo.decoretor.NumeroPostiDecoretor;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MostraFiltriView {
    public AulaBean mostraFiltri() {
        AulaBean filtro = new AulaBean();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Scanner scanner;
        scanner = null;
      /*  try {
            System.out.print("Inserisci l'orario della lezione (dd/MM/yyyy HH:mm): ");
            String orarioInput = scanner.nextLine();
            filtro.setOrarioLezione((Date) dateFormat.parse(orarioInput));
        } catch (ParseException e) {
            System.out.println("Formato data/ora non valido");
        }*/

        System.out.print("Inserisci la capienza desiderata: ");
        int posti = Integer.parseInt(scanner.nextLine());


        filtro.setPosti(posti);

        /*System.out.print("Inserisci i dispositivi richiesti (separati da virgola): ");
        String dispositiviInput = scanner.nextLine();
        List<String> dispositivi = new ArrayList<>();
        for (String dispositivo : dispositiviInput.split(",")) {
            dispositivi.add(dispositivo.trim());
        }
        filtro.setDispositivi(dispositivi);*/

        //AulaComponent controller = new NumeroPostiDecoretor();
 // chiamo il controller applicativo gli mando i dati appena presi dal filtro, mi ritorna l'ID delle aule poi
        // scelgo quella che voglio tra     quelle disponibili, chiamo la view  dati prenotazioneView
        PrendiDatiPrenotazioneView prendiDatiPrenotazioneView = new PrendiDatiPrenotazioneView();
        prendiDatiPrenotazioneView.prendiDatiPrenotazione(filtro);
        return filtro;
    }
}
