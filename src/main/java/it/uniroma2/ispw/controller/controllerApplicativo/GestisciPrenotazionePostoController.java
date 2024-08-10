package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;
import it.uniroma2.ispw.model.prenotazionePosto.dao.PrenotazionePostoDAO;
import it.uniroma2.ispw.model.prenotazionePosto.dao.PrenotazionePostoDBMS;
import it.uniroma2.ispw.model.prenotazionePosto.dao.PrenotazionePostoFS;

import java.util.ArrayList;
import java.util.List;

public class GestisciPrenotazionePostoController {
    private PrenotazionePostoDAO prenotazionePostoDao;

    public GestisciPrenotazionePostoController() {
        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            prenotazionePostoDao=  new PrenotazionePostoDBMS();
        } else {
            try {prenotazionePostoDao =  new PrenotazionePostoFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Object> getPrenotazioneByName(UserBean us) {
        return List.of();
    }

    public void removePrenotazione() {
    }

    public List<PrenotazionePostoBean> getAllReservation(PrenotazionePostoBean pb) {
        List<PrenotazionePostoModel> postiModel;
        List<PrenotazionePostoBean> prenotazioniPostiBean = new ArrayList<>();

        postiModel = prenotazionePostoDao.getAllReservations(pb);

        for (PrenotazionePostoModel ppm : postiModel) {
            PrenotazionePostoBean prenotazioneBean = new PrenotazionePostoBean(ppm.getNomeDocente(),ppm.getIdPosto(), ppm.getIdAula(), ppm.getMateria(), ppm.getGiornoLezione(), ppm.getOraLezione(),ppm.getIdPrenotazionePosto());
            prenotazioniPostiBean.add(prenotazioneBean);
        }
        return prenotazioniPostiBean;
    }




    public List<Object> getAulaByMateria(PrenotazioneAulaBean pab) {
        return List.of();
    }
}

    /*public List <PrenotazionePostoBean> getAllReservation(PrenotazionePostoBean pb) {
        PrenotazionePostoDAO.getAulaByMateria(pb);

        return List.of(pb);
    }
    public boolean prenota() {//questo metodo deve fare la prenotazione dell'aula
        return true;
    }


    public List<Object> getPrenotazioneByName(UserBean us) {
        //TODO
        return List.of();
    }
    public AulaModel getAulaByCognoem(String aula) throws ItemNotFoundException {
        return aulaDAO.getAulaByCognome(aula);
    }
    public List<PrenotazionePostoBean> getAulaByMateria(PrenotazioneAulaBean aula) throws ItemNotFoundException {
        return PrenotazionePostoDAO.getAulaByMateria(aula);
    }


    public void removePrenotazione() {
    }
}

 */
