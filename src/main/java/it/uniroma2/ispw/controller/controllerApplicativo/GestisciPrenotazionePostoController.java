package it.uniroma2.ispw.controller.controllerApplicativo;
import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.posto.dao.PostoDAO;
import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;
import it.uniroma2.ispw.model.prenotazionePosto.dao.PrenotazionePostoDAO;
import it.uniroma2.ispw.model.prenotazionePosto.dao.PrenotazionePostoDBMS;
import it.uniroma2.ispw.model.prenotazionePosto.dao.PrenotazionePostoFS;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestisciPrenotazionePostoController {
    private PrenotazionePostoDAO prenotazionePostoDao;
    private PostoDAO postoDao;

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

    //come gestire gli errori
    public void removePrenotazione(PrenotazionePostoBean pb) throws ItemNotFoundException, SQLException {

        PrenotazionePostoModel ppm=prenotazionePostoDao.getPrenotazioneByid(pb.getIdPrenotazionePosto());
        if(ppm==null) {
            throw new ItemNotFoundException("id prenotazione non trovata");
        }
        prenotazionePostoDao.rimuoviPrenotazionePosto(ppm);
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

    //TODO cambiare object in model
    public List<Object> getAuleByMateria(PrenotazioneAulaBean pab) {
        return List.of();

    }
    public void getPrenotazioniAuleBySubject(PrenotazioneAulaBean pab) {
    }

    public void getPrenotazioneAauleByTeacher(PrenotazioneAulaBean pab) {
    }

    public void getPrenotazioneAauleByTimeOrDate(PrenotazioneAulaBean pab) {
    }

    public void prenotaPostoByIdAula(PrenotazioneAulaBean pab) {
    }
}
