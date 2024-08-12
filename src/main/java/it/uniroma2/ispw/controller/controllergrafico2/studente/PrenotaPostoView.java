package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazionePostoController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.utils.DateParser;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class PrenotaPostoView extends TemplateView {
    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {
        int choice ;
        boolean cond =true;
        while(cond){
            choice=this.userChoice();
            switch (choice) {
                case 1 -> carcaPerDataEOrario();
                case 2 -> cercaPerProfessore();
                case 3 -> cercaPerMateria();
                case 4->prenotaPosto();
                case 5->cond=false;
                default -> System.out.println("Choice invalido");
            }

        }
    }
    public void PrenotaPostoView(UserBean usrBean) {
        PrenotazioneAulaBean pab = new PrenotazioneAulaBean();

        try {
            String idPrenotazioneAula=getDesiredIn("id prenotazione aula","inserisci l'id della prenotazione aula");
            pab.setIdPrenotazioneAula(idPrenotazioneAula);
            new GestisciPrenotazionePostoController().prenotaPostoByIdAula(pab);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    private void prenotaPosto() {
    }

    private void cercaPerMateria() {
        PrenotazioneAulaBean pab =new PrenotazioneAulaBean();
            try {
                pab.setMateria(getDesiredIn("nome materia", "inserisci nome materia:"));
                new GestisciPrenotazionePostoController().getPrenotazioniAuleBySubject(pab);
            } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }

    private void cercaPerProfessore() {
        PrenotazioneAulaBean pab =new PrenotazioneAulaBean();
        try {
            String nomeDocente=getDesiredIn("nome docente:","inserisci il nome del docente:").toLowerCase();
            String cognomeDocente=getDesiredIn("cognome docente","inserisci il cognome del docente").toLowerCase();

            pab.setNomeDocente(nomeDocente + " " + cognomeDocente);
            new GestisciPrenotazionePostoController().getPrenotazioneAauleByTeacher(pab);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void carcaPerDataEOrario() {
        PrenotazioneAulaBean pab = new PrenotazioneAulaBean();
        Orario orario = null;
        try {
            String fasciaOraria = getDesiredIn("inserisci fascia oraria:", "scegli tra i seguenti orari:" + getFasceOrarieAsString());
            String data = getDesiredIn("inserisci la data", "inserisci la data del giorno della lezione nel formato dd/mm/yyyy");
            Date dataLezione = DateParser.parseStringToDate(data);


            pab.setGiornoLezione(dataLezione);
            pab.setOraLezione(orario.fromString(fasciaOraria));
            new GestisciPrenotazionePostoController().getPrenotazioneAauleByTimeOrDate(pab);

        } catch (IOException | InvalidDataException e) {
            throw new RuntimeException(e);
        }

    }


    private String getFasceOrarieAsString() {
        StringBuilder builder = new StringBuilder();
        for (Orario orario : Orario.values()) {
            builder.append("- ").append(orario.getFasciaOraria()).append("\n");
        }
        return builder.toString();
    }

    @Override
    protected List<String> getOptions() {
        return List.of("cerca per data e orario","cerca per nome professore" ,"cerca per materia");
    }

    @Override
    protected String getHeader() {
        return "Prenotazione Posto";
    }


}
