package it.uniroma2.ispw.controller;

import it.uniroma2.ispw.bean.PostoBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.observer.observers.PostoObserver;
import it.uniroma2.ispw.controller.observer.subject.PrenotazionePostoSubject;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.model.posto.PostoModel;
import it.uniroma2.ispw.model.posto.dao.PostoDAO;
import it.uniroma2.ispw.model.posto.dao.PostoDAOFactory;
import it.uniroma2.ispw.model.posto.dao.PostoDAOFactoryImpl;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneposto.PrenotazionePostoModel;
import it.uniroma2.ispw.model.prenotazioneposto.dao.PrenotazionePostoDAO;
import it.uniroma2.ispw.model.prenotazioneposto.dao.PrenotazionePostoDAOFactory;
import it.uniroma2.ispw.model.prenotazioneposto.dao.PrenotazionePostoDAOFactoryImpl;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestisciPrenotazionePostoController {
    private final PrenotazionePostoDAO prenotazionePostoDao;
    private final PostoDAO postoDAO;


    public GestisciPrenotazionePostoController() {
        PostoDAOFactory daoFactory = new PostoDAOFactoryImpl();
        postoDAO = daoFactory.getDao();
        PrenotazionePostoDAOFactory daosFactory = new PrenotazionePostoDAOFactoryImpl();
        prenotazionePostoDao = daosFactory.getDao();
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
        UserModel usm=new UserModel(userBean.getEmail(),userBean.getRuolo(),userBean.getNome());
        List<PrenotazionePostoModel> postiModel;
        List<PrenotazionePostoBean> prenotazioniPostiBean = new ArrayList<>();


        postiModel = prenotazionePostoDao.getAllReservations(usm);
        for (PrenotazionePostoModel ppm : postiModel) {
            PrenotazionePostoBean prenotazioneBean = new PrenotazionePostoBean(

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

}

