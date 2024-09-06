package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciCreazionePrenotazioneAulaController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.DateParser;
import it.uniroma2.ispw.utils.exception.FormatoDataNonValidoException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.util.List;

public class VisualizzaLezioniView extends TemplateView{
    public VisualizzaLezioniView(UserBean usrBean) {
    }

    @Override
    public void control() throws SystemException, IOException, ItemNotFoundException {
        int choice ;
        boolean cond =true;
        while(cond){
            choice=this.userChoice();
            switch (choice) {
                case 1 -> carcaPerOrario();
                case 2 -> cercaPerProfessore();
                case 3 -> cercaPerMateria();
                case 4->cond=false;
                default -> System.out.println("Choice invalido");
            }

        }
    }

    private void cercaPerProfessore() throws ItemNotFoundException {
        PrenotazioneAulaBean pab = new PrenotazioneAulaBean();
        try {
            pab.setNomeDocente(getDesiredIn("Nome Docente", "inserisci il nome docente"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printTable(new GestisciCreazionePrenotazioneAulaController().getAulaByProfessore(pab));
    }


    private void carcaPerOrario() {
        PrenotazioneAulaBean pab = new PrenotazioneAulaBean();
        try {
            pab.setOrarioLezione(DateParser.parseStringToDate(getDesiredIn("data lezione", "inserisci data e ora nel formato gg/mm/yyyy")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch ( FormatoDataNonValidoException e) {
            throw new RuntimeException(e);
        }
        printTable(new GestisciCreazionePrenotazioneAulaController().getAulaByOrario(pab));

    }


    private void cercaPerMateria() {
        PrenotazioneAulaBean pab = new PrenotazioneAulaBean();
        try {
            pab.setMateria(getDesiredIn("Materia", "inserisci il nome della materia"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printTable(new GestisciCreazionePrenotazioneAulaController().getAuleByMateria(pab));
    }

    @Override
    protected List<String> getOptions() {
        return List.of("Cerca per orario","Cerca per professore","Cerca per materia");
    }

    @Override
    protected String getHeader() {
        return "PRENOTAZIONE POSTO";
    }


}
