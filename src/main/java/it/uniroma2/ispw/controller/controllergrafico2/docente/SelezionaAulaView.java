package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;

import java.util.List;
import java.util.Scanner;

public class SelezionaAulaView {

    public String selezionaAula(List<AulaBean> aule) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aule disponibili:");
        for (int i = 0; i < aule.size(); i++) {
            AulaBean aula = aule.get(i);
            System.out.println((i + 1) + ". ID: " + aula.getIdAula() + ", Posti: " + aula.getPosti() + ", Orario: " + aula.getOrarioLezione() + ", Dispositivi: " + aula.getDispositivi());
        }

        System.out.print("Seleziona il numero dell'aula desiderata: ");
        int scelta = Integer.parseInt(scanner.nextLine());

        while (scelta < 1 || scelta > aule.size()) {
            System.out.print("Scelta non valida. Riprova: ");
            scelta = Integer.parseInt(scanner.nextLine());
        }

        scanner.close();

        return aule.get(scelta - 1).getIdAula();
    }
}
