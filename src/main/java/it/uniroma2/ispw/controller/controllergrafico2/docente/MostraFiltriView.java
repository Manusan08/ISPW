package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;

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
        try {
            System.out.print("Inserisci l'orario della lezione (dd/MM/yyyy HH:mm): ");
            String orarioInput = scanner.nextLine();
            filtro.setOrarioLezione((Date) dateFormat.parse(orarioInput));
        } catch (ParseException e) {
            System.out.println("Formato data/ora non valido");
        }

        System.out.print("Inserisci la capienza desiderata: ");
        filtro.setPosti(Integer.parseInt(scanner.nextLine()));

        System.out.print("Inserisci i dispositivi richiesti (separati da virgola): ");
        String dispositiviInput = scanner.nextLine();
        List<String> dispositivi = new ArrayList<>();
        for (String dispositivo : dispositiviInput.split(",")) {
            dispositivi.add(dispositivo.trim());
        }
        filtro.setDispositivi(dispositivi);

        return filtro;
    }
}
