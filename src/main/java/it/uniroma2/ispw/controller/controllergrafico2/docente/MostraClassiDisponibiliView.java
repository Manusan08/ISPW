package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;

import java.util.List;

public class MostraClassiDisponibiliView {
    public void mostraClassiDisponibili(List<AulaBean> classi) {
        System.out.println("Classi disponibili:");
        boolean hasAvailableClasses = false;

        for (int i = 0; i < classi.size(); i++) {
            AulaBean aula = classi.get(i);
            if (!aula.isPrenotata()) {
                hasAvailableClasses = true;
                System.out.println((i + 1) + ". Aula ID: " + aula.getIdAula() +
                        ", Capienza: " + aula.getPosti() +
                        ", Dispositivi: " + (aula.getDispositivi() != null ? String.join(", ", aula.getDispositivi()) : "Nessuno") +
                        ", Prenotata: " + (aula.isPrenotata() ? "Sì" : "No"));
            }
        }

        if (!hasAvailableClasses) {
            System.out.println("Non ci sono aule disponibili.");
        }
    }

}
