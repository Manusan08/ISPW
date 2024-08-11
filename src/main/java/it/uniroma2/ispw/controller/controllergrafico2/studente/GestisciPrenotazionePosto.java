package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazionePostoController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GestisciPrenotazionePosto extends TemplateView {

    public GestisciPrenotazionePosto(UserBean usrBean) {
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
    //come gestisco il caso in cui non è stato possibile eliminare la prenotazione?
    private void eliminaPrenotazione() {
        PrenotazionePostoBean ab=new PrenotazionePostoBean();
            try{
                ab.setIdAula(getDesiredIn("id","inserisci l'id della prenotazione"));
                new GestisciPrenotazionePostoController().removePrenotazione(ab);
            }catch (IOException e){
                System.out.println("Impossibile leggere i dati ");
            } catch (SQLException | ItemNotFoundException e) {
                throw new RuntimeException(e);
            }
    }

    void visualizzaPrenotazioni(){
        PrenotazionePostoBean pb= new PrenotazionePostoBean();
        pb.setEmail(this.usrBean.getEmail());
        printTable(new GestisciPrenotazionePostoController().getAllReservation(pb));

    }

    @Override
    protected List<String> getOptions() {
        return List.of("elimina prenotazione","visualizza prenotazione");
    }

    @Override
    protected String getHeader() {
        return "GESTIONE PRENOTAZIONI";
    }

}
