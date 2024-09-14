package it.uniroma2.ispw.view.cli.studente;

import it.uniroma2.ispw.bean.PostoBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.facade.StudenteFacade;
import it.uniroma2.ispw.view.cli.TemplateView;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrenotaPostoView extends TemplateView {
    public PrenotaPostoView(UserBean userBean) {
        super(userBean);
    }

    StudenteFacade intheMiddle = new StudenteFacade();

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException, SQLException, CampiVuotiExeption {
        int choice;
        boolean cond = true;
        while (cond) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> carcaAulaByCognomeProfessoreEMateria();
                case 2 -> cercaAulaByIdPrenotazione();
                case 3 -> cond = false;
                default -> System.out.println("scela non valida");
            }
        }
    }

    private void carcaAulaByCognomeProfessoreEMateria() {


        try {

            PrenotazioneAulaBean pab = new PrenotazioneAulaBean();
            String cognomeProfessore = getDesiredIn("cognome professore", "inserisci cognome del professore: ");
            String nomeMateria = getDesiredIn("materia", "inserisci nome materia: ");
            pab.setNomeDocente(cognomeProfessore);
            pab.setMateria(nomeMateria);

            List<PrenotazioneAulaBean> pabs;


            pabs = intheMiddle.searchBySurnameAndSubject(pab, this.usrBean);
            if (pabs == null || pabs.isEmpty()) {
                System.out.println("Nessuna prenotazione trovata per il professore e la materia inseriti.");
            } else {
                printTable(pabs);
            }

        } catch (ItemNotFoundException | CampiVuotiExeption | IOException | SystemException e) {
            System.out.println(e.getMessage());
        }

    }

    private void cercaAulaByIdPrenotazione() {
        PrenotazioneAulaBean pab = new PrenotazioneAulaBean();
        List<PostoBean> postoBeans;

        try {
            String idPrenotazioneAula = getDesiredIn("inserisci Campi richiesti :", "inserisci l'id della prenotazione aula :");
            pab.setIdPrenotazioneAula(idPrenotazioneAula);
            boolean choice = true;
            do {
                postoBeans = intheMiddle.getPostiBean(pab);
                String aulaId=postoBeans.getFirst().getIdAula();
                int capienza = intheMiddle.getCapienzaAula(aulaId);

                postiPrinter(postoBeans, capienza);

                String posto = getDesiredIn("scelta posto", "inserisci il posto che vuoi prenotare:").toUpperCase();
                PrenotazionePostoContext ppc = new PrenotazionePostoContext(posto);
                postoBeans = intheMiddle.selezionaPosto(ppc);
                postiPrinter(postoBeans, capienza);

                if (chiediConferma(ppc)) {
                    PostoBean postoBean = isPostoBeanInlist(postoBeans, ppc.getPostoId());
                    System.out.println(intheMiddle.insertPostoIntodb(postoBean, pab, this.usrBean));
                    choice = false;
                }
            } while (choice);
        } catch (SQLException | SystemException e) {
            throw new RuntimeException(e);
        } catch (CampiVuotiExeption | IOException e1){
            System.out.println(e1.getMessage());
        }
    }


    private boolean chiediConferma(PrenotazionePostoContext posto) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("stai prenotando il posto: " + posto.getPostoId() + " vuoi confermare (Y/n) :");
            String inputStr = input.readLine();
            if (inputStr.equalsIgnoreCase("y")) {
                return true;
            } else if (inputStr.equalsIgnoreCase("n"))
                return false;
            else {
                System.out.println("Input non valido. Si prega di rispondere con 'Y' o 'N'.");
            }

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

    public static void postiPrinter(List<PostoBean> posti, int capienza) {
        int index = 0;
        String postoId = posti.get(0).getIdPosto();
        char firstchar = postoId.charAt(0);
        System.out.println(ANSI_CYAN + "  " + "_".repeat(62) + ANSI_RESET);
        for (int i = 0; i < capienza / 10; i++) {
            System.out.print(ANSI_CYAN + " | " + ANSI_RESET);
            for (int j = 0; j < 10; j++) {
                String control = firstchar + String.valueOf(index);
                PostoBean posto = isPostoBeanInlist(posti, control);

                if (posto != null) {
                    String color = posto.isPrenotato() ? ANSI_CYAN : ANSI_RESET;
                    System.out.print(color + formatWithFiveDigits(control) + "|" + ANSI_RESET);
                } else {
                    System.out.print(ANSI_RED + formatWithFiveDigits(control) + "|" + ANSI_RESET);
                }
                index++;
            }
            System.out.println(ANSI_CYAN + "|" + ANSI_RESET);
        }
        System.out.println(ANSI_CYAN + "  " + "_".repeat(62) + ANSI_RESET);
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