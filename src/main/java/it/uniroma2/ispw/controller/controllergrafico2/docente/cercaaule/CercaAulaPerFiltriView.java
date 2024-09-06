package it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerapplicativo.GestisciAuleController;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.controller.controllergrafico2.docente.prenotaaula.PrenotazioneAulaView;
import it.uniroma2.ispw.facade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.util.List;
import java.util.Scanner;

public class CercaAulaPerFiltriView extends TemplateView {


    private ManIntheMiddleFaçade intheMiddleFaçade = new ManIntheMiddleFaçade();

    public CercaAulaPerFiltriView(UserBean usrBean) {
        super(usrBean);
    }


    @Override
    public void control()  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci i dettagli per la prenotazione dell'aula:");
        System.out.print("Numero di posti minimo: ");
        int postiMinimi = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Proiettore richiesto (true/false): ");
        boolean proiettoreRichiesto = scanner.nextBoolean();
        System.out.print(proiettoreRichiesto);

        scanner.nextLine();

        System.out.print("computer per ogni banco richiesto (true/false): ");
        boolean computerRichiesto = scanner.nextBoolean();
        scanner.nextLine();


        System.out.print("Banchi da disegno richiesti (true/false): ");
        boolean banchiDaDisegno = scanner.nextBoolean();
        scanner.nextLine(); // Consuma il newline


        AulaBean aulaBean = new AulaBean();
        aulaBean.setPosti(postiMinimi);
        aulaBean.setProiettore(proiettoreRichiesto);
        aulaBean.setComputer(computerRichiesto);
        aulaBean.setBanchiDisegno(banchiDaDisegno);

        List<AulaBean> aulabeanlist;
        try {
            aulabeanlist = intheMiddleFaçade.chekAula(aulaBean);
            printTable(aulabeanlist);
            System.out.println("Aule disponibili:");
            for (int i = 0; i < aulabeanlist.size(); i++) {
                AulaBean ab = aulabeanlist.get(i);
                System.out.println((i + 1) + ". Aula ID: " + ab.getIdAula() + ", Posti: " + ab.getPosti() + ", Proiettore: " + ab.isProiettore() + ", Computer: " + ab.isComputer() + ", Banchi da disegno: " + ab.isBanchiDisegno());
            }

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
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }

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



