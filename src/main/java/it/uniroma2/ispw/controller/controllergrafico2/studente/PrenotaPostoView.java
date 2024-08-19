package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.Façade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.bean.PostoBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrenotaPostoView extends TemplateView {
    public PrenotaPostoView(UserBean userBean) {
        super(userBean);
    }

    ManIntheMiddleFaçade intheMiddle = new ManIntheMiddleFaçade();

    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException, SQLException {
        int choice;
        boolean cond = true;
        while (cond) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> carcaAulaByCognomeProfessoreEMateria();
                case 2 -> cercaAulaByIdPrenotazione();
                case 3 -> cond = false;
                default -> System.out.println("Choice invalido");
            }
        }
    }

    private void carcaAulaByCognomeProfessoreEMateria() throws IOException, SQLException {
        PrenotazioneAulaBean pab = new PrenotazioneAulaBean();

        String cognomeProfessore = getDesiredIn("cognome professore", "inserisci cognome del professore: ");
        String nomeMateria = getDesiredIn("materia", "inserisci nome materia: ");
        pab.setNomeDocente(cognomeProfessore);
        pab.setMateria(nomeMateria);
        List<PrenotazioneAulaBean> pabs = intheMiddle.searchBySurnameAndSubject(pab);
        if (pabs.isEmpty()) {
            System.out.println("Non ci sono aule prenotate con questa materia e con questo nome");
            return;
        }
        printTable(pabs);
    }

    //TODO RINOMINA CON UN nome PIU' appropriato
    private void cercaAulaByIdPrenotazione() {
        PrenotazioneAulaBean pab = new PrenotazioneAulaBean();
        List<PostoBean> postoBeans = new ArrayList<>();

        try {
            String idPrenotazioneAula = getDesiredIn("inserisci Campi richiesti :", "inserisci l'id della prenotazione aula :");
            pab.setIdPrenotazioneAula(idPrenotazioneAula);
            boolean choice = true;
            do {
                postoBeans = intheMiddle.getPostiBean(pab);

                postiPrinter(postoBeans);

                String posto = getDesiredIn("scelta posto", "inserisci il posto che vuoi prenotare:").toUpperCase();
                PrenotazionePostoContext ppc = new PrenotazionePostoContext(posto);
                postoBeans = intheMiddle.selezionaPosto(ppc);
                postiPrinter(postoBeans);

                if (chiediConferma(ppc)) {
                    PostoBean postoBean = isPostoBeanInlist(postoBeans, ppc.getPostoId());
                    System.out.println(intheMiddle.insertPostoIntodb(postoBean, pab, this.usrBean));
                    choice=false;
                }
            }while (choice);
        } catch(SQLException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        } catch(SystemException e){
            throw new RuntimeException(e);
        }
    }


    private boolean chiediConferma(PrenotazionePostoContext posto) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("stai prenotando il posto: " + posto.getPostoId() + " vuoi confermare (Y/n) :");
        String inputStr = input.readLine();
        switch (inputStr.toLowerCase()) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Input non valido. Si prega di rispondere con 'Y' o 'N'.");
                return chiediConferma(posto);
        }
    }


    @Override
    protected List<String> getOptions() {
        return List.of("cerca lezione per cognome professore e nome materia", "prenota posto per id prenotazione aula", "esci");
    }

    @Override
    protected String getHeader() {
        return "Prenotazione Posto";
    }

    public static void postiPrinter(List<PostoBean> posti) {
        int index = 0;
        String postoId = posti.get(0).getIdPosto();
        char firstchar = postoId.charAt(0);

        System.out.println(ANSI_CYAN + "  " + "_".repeat(33) + ANSI_RESET);
        for (int i = 0; i < 8; i++) {
            System.out.print(ANSI_CYAN + " | " + ANSI_RESET);
            for (int j = 0; j < 8; j++) {
                String control = firstchar + String.valueOf(index);
                PostoBean posto = isPostoBeanInlist(posti, control);

                if (posto != null) {
                    String color = posto.isPrenotato() ? ANSI_CYAN : ANSI_RESET;
                    System.out.print(color + formatWithTreeDigit(control) + "|" + ANSI_RESET);
                } else {
                    System.out.print(ANSI_RED + formatWithTreeDigit(control) + "|" + ANSI_RESET);
                }

                index++;
            }
            System.out.println(ANSI_CYAN + "|" + ANSI_RESET);
        }
        System.out.println(ANSI_CYAN + "  " + "_".repeat(33) + ANSI_RESET);
    }

    public static PostoBean isPostoBeanInlist(List<PostoBean> postoBeans, String posto) {
        for (PostoBean pb : postoBeans) {
            if (pb.getIdPosto().equals(posto)) {
                return pb;
            }
        }
        return null;
    }


}