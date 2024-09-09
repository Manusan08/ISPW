package it.uniroma2.ispw.controller;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;

import it.uniroma2.ispw.controller.factory.PrenotazioneAulaFactory;
import it.uniroma2.ispw.controller.factory.PrenotazioneAula;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.UserModel;

import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaFS;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestisciCreazionePrenotazioneAulaController {


    private final PrenotazioneAulaDAO prenotazioneAulaDAO;

    public GestisciCreazionePrenotazioneAulaController() {
        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {

            prenotazioneAulaDAO = new PrenotazioneAulaDBMS();
        } else {
            try {

                prenotazioneAulaDAO = new PrenotazioneAulaFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }

    public boolean prenota(PrenotazioneAulaBean prenotazione) {
        PrenotazioneAulaFactory factory = new PrenotazioneAulaFactory();
        PrenotazioneAula prenotazioneAula = factory.creaPrenotazione(prenotazione);
        if (prenotazioneAula.verificaPrenotazione()) {

            prenotazioneAula.salvaPrenotazione();
            return true;
        } return false;

    }







    public List<PrenotazioneAulaBean> getBookedClassByteacherNameAndSubject(PrenotazioneAulaBean prenotazioneAulaBean, UserModel usr) throws SQLException, ItemNotFoundException {
        List<PrenotazioneAulaBean> prenotazioneAule = new ArrayList<>();
        PrenotazioneAulaModel pam = new PrenotazioneAulaModel(prenotazioneAulaBean.getNomeDocente(),prenotazioneAulaBean.getMateria());

        List<PrenotazioneAulaModel> pams;

            pams = prenotazioneAulaDAO.getPrenotazioniAuleByProfessorAndSubject(pam, usr);
            for (PrenotazioneAulaModel prenotazione : pams) {
                PrenotazioneAulaBean pab = new PrenotazioneAulaBean(
                        prenotazione.getIdPrenotazioneAula(),
                        prenotazione.getNomeProfessore(),
                        prenotazione.getDatalezione(),
                        prenotazione.getOraLezione(),
                        prenotazione.getDescrizione(),
                        prenotazione.getMateria(),
                        prenotazione.getIdAula()
                );
                prenotazioneAule.add(pab);

        }
        return  prenotazioneAule;
    }

    public boolean modificaDescrizionePrenotazione() {
        return true;
    }

    public void removePrenotazione() {
        //dsds
    }



    public int getCapienzaAula(String aulaId) throws SQLException {
        PrenotazioneAulaModel pam=new PrenotazioneAulaModel();
        pam.setIdAula(aulaId);

        return prenotazioneAulaDAO.getCapienzaAula(pam);
    }

    public List<PrenotazioneAulaBean> getAvailableClass() {
        List<PrenotazioneAulaModel> aulaModels  =prenotazioneAulaDAO.getAvailableClass();
        List<PrenotazioneAulaBean> pabs = new ArrayList<>();
        for (PrenotazioneAulaModel aulaModel : aulaModels) {
            PrenotazioneAulaBean prab =new PrenotazioneAulaBean();
            prab.setIdAula(aulaModel.getIdAula());
            prab.setOraLezione(aulaModel.getOraLezione());
            pabs.add(prab);
        }
        return pabs;

    }


}
