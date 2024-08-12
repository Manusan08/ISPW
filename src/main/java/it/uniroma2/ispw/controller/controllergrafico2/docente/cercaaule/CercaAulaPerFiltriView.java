package it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciAuleController;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.controller.controllergrafico2.docente.prenotaaula.PrenotazioneAulaView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CercaAulaPerFiltriView extends TemplateView {



     GestisciAuleController gestisciAuleController = new GestisciAuleController();
    public CercaAulaPerFiltriView(UserBean usrBean) {
        super(usrBean);
    }



    @Override
    public void control() throws SystemException, InvalidDataException, IOException, ItemNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci i dettagli per la prenotazione dell'aula:");

        // Filtro per il numero di posti
        System.out.print("Numero di posti minimo: ");
        int postiMinimi = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline

        // Filtro per la presenza di un proiettore
        System.out.print("Proiettore richiesto (true/false): ");
        boolean proiettoreRichiesto = scanner.nextBoolean();
        System.out.print(proiettoreRichiesto);

        scanner.nextLine(); // Consuma il newline

        // Filtro per la presenza di un proiettore
        System.out.print("computer per ogni banco richiesto (true/false): ");
        boolean computerRichiesto = scanner.nextBoolean();
        scanner.nextLine(); // Consuma il newline

        // Filtro per la presenza di un proiettore
        System.out.print("Bnachi da disegno richiesti (true/false): ");
        boolean banchiDaDisegno = scanner.nextBoolean();
        scanner.nextLine(); // Consuma il newline


        // Chiamata al controller per verificare la disponibilità dell'aula
        AulaBean aulaBean = new AulaBean();
        aulaBean.setPosti(postiMinimi);

        aulaBean.setProiettore(proiettoreRichiesto);

        aulaBean.setComputer(computerRichiesto);
        aulaBean.setBanchiDisegno(banchiDaDisegno);
        List<AulaBean> aulabeanlist= gestisciAuleController.chekAula(aulaBean);
        printTable(aulabeanlist);
        System.out.println("Aule disponibili:");
        for (int i = 0; i < aulabeanlist.size(); i++) {
            AulaBean ab = aulabeanlist.get(i);
            System.out.println((i + 1) + ". Aula ID: " + ab.getIdAula() + ", Posti: " + ab.getPosti() + ", Proiettore: " + ab.isProiettore() + ", Computer: " + ab.isComputer() + ", Banchi da disegno: " + ab.isBanchiDisegno());
        }

        // Selezione dell'aula da parte dell'utente
        System.out.print("Seleziona il numero dell'aula desiderata: ");
        int scelta = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline

        if (scelta < 1 || scelta > aulabeanlist.size()) {
            System.out.println("Scelta non valida.");
            return;
        }
        AulaBean aulaSelezionata = aulabeanlist.get(scelta - 1);
        PrenotazioneAulaView prenotazioneView = new PrenotazioneAulaView(aulaSelezionata, this.usrBean);
        prenotazioneView.start();

    }

    @Override
    public List<String> getOptions() {
        return List.of("Prenota aula", "Torna indietro");
    }

    @Override
    public String getHeader() {
        return "PRENOTA AULA";
    }
}



