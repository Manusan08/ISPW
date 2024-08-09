package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazioneAulaController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.DateParser;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.util.List;

public class PrenotaPostoView extends TemplateView{
    @Override
    public void control() throws SystemException, IOException {
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

    private void cercaPerProfessore() {
        AulaBean aulaBean = new AulaBean();
        try {
            aulaBean.setNomeDocente(getDesiredIn("Nome Docente", "inserisci il nome docente"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printTable(new GestisciPrenotazioneAulaController().getAuleByMateria(aulaBean));
        }


    private void carcaPerOrario() {
        AulaBean aulaBean = new AulaBean();
        try {
            aulaBean.setOrarioLezione(DateParser.parseStringToDate(getDesiredIn("data lezione", "inserisci data e ora nel formato gg/mm/yyyy")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        }
        printTable(new GestisciPrenotazioneAulaController().getAuleByMateria(aulaBean));
    }

    private void cercaPerMateria() {
        AulaBean aulaBean = new AulaBean();
        try {
            aulaBean.setMateria(getDesiredIn("Materia", "inserisci il nome della materia"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printTable(new GestisciPrenotazioneAulaController().getAuleByMateria(aulaBean));
    }

    @Override
    protected List<String> getOptions() {
        return List.of("Cerca per orario","Cerca per professore","Cerca per materia");
    }

    @Override
    protected String getHeader() {
        return "PRENOTAZIONE AULA";
    }


}
