package it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciAuleController;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CercaAulaPerFiltriView extends TemplateView {



     GestisciAuleController gestisciAuleController = new GestisciAuleController();
    public CercaAulaPerFiltriView(UserBean usrBean) {
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

     /*   // Filtro per la data della lezione
        System.out.print("Data della lezione (formato YYYY-MM-DD): ");
        String dataLezioneInput = scanner.nextLine();
        Orario dataLezione = null;
        // Ulteriori dettagli per la prenotazione
        System.out.print("Nome della materia: ");
        String nomeMateria = scanner.nextLine();

        System.out.print("Nome del professore: ");
        String nomeProfessore = scanner.nextLine();*/

        // Chiamata al controller per verificare la disponibilità dell'aula
        AulaBean aulaBean = new AulaBean();
        aulaBean.setPosti(postiMinimi);

        aulaBean.setProiettore(proiettoreRichiesto);

        aulaBean.setComputer(computerRichiesto);
        aulaBean.setBanchiDisegno(banchiDaDisegno);
        List<AulaBean> aulabeanlist= gestisciAuleController.chekAula(aulaBean);
        printTable(aulabeanlist);
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



