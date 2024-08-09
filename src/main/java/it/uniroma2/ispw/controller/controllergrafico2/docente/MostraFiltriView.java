package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.decoretor.NumeroPostiDecoretor;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;
import java.util.Scanner;

public class MostraFiltriView {
    public MostraFiltriView(UserBean usrBean) {
    }

    public List<AulaBean> control() throws SystemException {
        AulaBean filtro =new AulaBean();
       // SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        List<AulaBean> auleBean;
        Scanner scanner=new Scanner(System.in);

      /*  try {
            System.out.print("Inserisci l'orario della lezione (dd/MM/yyyy HH:mm): ");
            String orarioInput = scanner.nextLine();
            filtro.setOrarioLezione((Date) dateFormat.parse(orarioInput));
        } catch (ParseException e) {
            System.out.println("Formato data/ora non valido");
        }*/

        System.out.print("Inserisci la capienza desiderata: ");
        int posti = scanner.nextInt();
        filtro.setPosti(posti);
        NumeroPostiDecoretor numeroPostiDecoretor=new NumeroPostiDecoretor();
        auleBean=numeroPostiDecoretor.getAule(filtro);

        /*System.out.print("Inserisci i dispositivi richiesti (separati da virgola): ");
        String dispositiviInput = scanner.nextLine();
        List<String> dispositivi = new ArrayList<>();
        for (String dispositivo : dispositiviInput.split(",")) {
            dispositivi.add(dispositivo.trim());
        }
        filtro.setDispositivi(dispositivi);*/

        //AulaComponent controller = new NumeroPostiDecoretor();
 // chiamo il controller applicativo gli mando i dati appena presi dal filtro, mi ritorna l'ID delle aule poi
        // scelgo quella che voglio tra     quelle disponibili, chiamo la view  dati prenotazioneView
       // PrendiDatiPrenotazioneView prendiDatiPrenotazioneView = new PrendiDatiPrenotazioneView();
      //  prendiDatiPrenotazioneView.prendiDatiPrenotazione(filtro);


        return auleBean;
    }
}
