package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciAuleController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class GestisciPrenotazioneView extends TemplateView {
    public GestisciPrenotazioneView(UserBean usrBean) {
    }

    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {

    }

    @Override
    protected List<String> getOptions() {
        return null;
    }

    @Override
    protected String getHeader() {
        return null;
    }
/*
    public GestisciPrenotazioneView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws IOException {
        int choice;
        boolean cond= true;

        while (cond){
            choice =this.userChoice();
            switch (choice){
                case 1->eliminaPrenotazione();
                case 2->visualizzaPrenotazioni();
                case 3 -> cond=false;
                default -> System.out.println("riprova");
            }
        }
        System.exit(0);
    }

    private void eliminaPrenotazione() {
        AulaBean ab=new AulaBean();
            try{
                ab.setIdAula(getDesiredIn("id","inserisci l'id della prenotazione"));
                new GestisciAuleController().removePrenotazione();
            }catch (IOException e){
                System.out.println("Impossibile leggere i dati ");
            }
    }

    void visualizzaPrenotazioni(){
        new GestisciAuleController().getAllReservation();
    }

    @Override
    protected List<String> getOptions() {
        return List.of("elimina prenotazione","visualizza prenotazione");
    }

    @Override
    protected String getHeader() {
        return "GESTIONE PRENOTAZIONI";
    }

*/
}
