package it.uniroma2.ispw.view.cli.studente;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.GestisciPrenotazionePostoController;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.view.cli.TemplateView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GestisciPrenotazionePostoView extends TemplateView {

    public GestisciPrenotazionePostoView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws IOException, SystemException {
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
    private void eliminaPrenotazione() throws SystemException {
        PrenotazionePostoBean ppb=new PrenotazionePostoBean();
            try{
                ppb.setIdPrenotazionePosto(getDesiredIn("id","inserisci l'id della prenotazione: "));
                new GestisciPrenotazionePostoController().removePrenotazione(ppb);
            } catch (SQLException | ItemNotFoundException | CampiVuotiExeption e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new SystemException(e.getMessage());
            }
    }

    void visualizzaPrenotazioni(){
        try {
            printTable(new GestisciPrenotazionePostoController().getAllReservation(this.usrBean));
        } catch (ItemNotFoundException | SystemException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    protected List<String> getOptions() {
        return List.of("elimina prenotazione","visualizza prenotazione","esci");
    }

    @Override
    protected String getHeader() {
        return "GESTIONE PRENOTAZIONI";
    }

}
