package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PostoBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.observers.Observer;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.observers.PostoObserver;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.subject.PrenotazionePostoSubject;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.model.posto.PostoModel;
import it.uniroma2.ispw.model.posto.dao.PostoDAO;
import it.uniroma2.ispw.model.posto.dao.PostoDBMS;
import it.uniroma2.ispw.model.prenotazioneAula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;
import it.uniroma2.ispw.model.prenotazionePosto.dao.PrenotazionePostoDAO;
import it.uniroma2.ispw.model.prenotazionePosto.dao.PrenotazionePostoDBMS;
import it.uniroma2.ispw.model.prenotazionePosto.dao.PrenotazionePostoFS;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import org.apache.commons.lang3.RandomStringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestisciPrenotazionePostoController {
    private PrenotazionePostoDAO prenotazionePostoDao;
    private PostoDAO postoDAO;
    private PrenotazioneAulaDAO prenotazioneAulaDAO;

    public GestisciPrenotazionePostoController() {
        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            prenotazionePostoDao = new PrenotazionePostoDBMS();
            postoDAO = new PostoDBMS();
            prenotazioneAulaDAO = new PrenotazioneAulaDBMS();
        } else {
            try {
                prenotazionePostoDao = new PrenotazionePostoFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String prenotaPosto(PostoBean pb, PrenotazioneAulaBean pab, UserModel usrm) throws SystemException, SQLException {

        String idPrenotazione = RandomStringUtils.randomAlphanumeric(15);

        PrenotazionePostoModel ppm = new PrenotazionePostoModel(
                idPrenotazione,
                pb.getIdAula(),
                usrm.getEmail(),
                pb.getPostoId(),
                pab.getIdPrenotazioneAula()
        );
        return prenotazionePostoDao.inserisciPrenotazione(ppm);
    }


    public void removePrenotazione(PrenotazionePostoBean pb) throws ItemNotFoundException, SQLException {
        PrenotazionePostoModel ppm = prenotazionePostoDao.getPrenotazioneByid(pb.getIdPrenotazionePosto());
        if (ppm == null) {
            throw new ItemNotFoundException("id prenotazione non trovata");
        }
        prenotazionePostoDao.rimuoviPrenotazionePosto(ppm);
    }

    public List<PrenotazionePostoBean> getAllReservation(UserBean userBean) throws ItemNotFoundException {
        List<PrenotazionePostoModel> postiModel;
        List<PrenotazionePostoBean> prenotazioniPostiBean = new ArrayList<>();

        postiModel = prenotazionePostoDao.getAllReservations(userBean);
        for (PrenotazionePostoModel ppm : postiModel) {
            PrenotazionePostoBean prenotazioneBean = new PrenotazionePostoBean(
                    ppm.getNomeDocente(),
                    ppm.getIdPosto(),
                    ppm.getIdAula(),
                    ppm.getMateria(),
                    ppm.getGiornoLezione(),
                    ppm.getOraLezione(),
                    ppm.getIdPrenotazionePosto(),
                    ppm.getIdPrenotazioneAula());

            prenotazioniPostiBean.add(prenotazioneBean);
        }
        return prenotazioniPostiBean;
    }


    public PrenotazionePostoSubject getAvailablePostiByPrenotazioneAulaid(PrenotazioneAulaBean pab) throws SQLException {
        PrenotazioneAulaModel pam=new PrenotazioneAulaModel(pab.getIdPrenotazioneAula());

        List<PostoModel> postiModel;
        postiModel = postoDAO.getAvailablePosti(pam);
        PrenotazionePostoSubject pps = new PrenotazionePostoSubject();

        for (PostoModel pm : postiModel) {
            PostoObserver po = new PostoObserver(pm.getPostoId(), pm.getIdAula(), pps);
            pps.attach(po);
        }
        return pps;
    }

    public List<PostoBean> postoObserverToPostoBean(List<Observer> observers) {
        List<PostoBean> postoBeans = new ArrayList<>();
        if (observers.isEmpty())
            return postoBeans;
        for (Observer ob : observers) {
            if (ob instanceof PostoObserver po) {
                PostoBean postoBean = new PostoBean(po.getPostoId(), po.getIdAula(), po.isPrenotato());
                postoBeans.add(postoBean);
            }
        }
        return postoBeans;
    }

}

