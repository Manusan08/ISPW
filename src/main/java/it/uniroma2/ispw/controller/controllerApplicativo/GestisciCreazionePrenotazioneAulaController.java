package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.factory.ConcretePrenotazioneAulaFactory;
import it.uniroma2.ispw.controller.controllerApplicativo.factory.PrenotazioneAula;
import it.uniroma2.ispw.controller.controllergrafico2.docente.prenotaaula.ConfermPrenotazioneView;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;
import it.uniroma2.ispw.model.prenotazioneAula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaFS;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestisciCreazionePrenotazioneAulaController {
    private AulaDAO aulaDAO;

    private PrenotazioneAulaDAO prenotazioneAulaDAO;
    private ConcretePrenotazioneAulaFactory prenotazioneFactory;
    public GestisciCreazionePrenotazioneAulaController() {
        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            aulaDAO = new AulaDBMS();
            prenotazioneAulaDAO = new PrenotazioneAulaDBMS();
        } else {
            try {
                aulaDAO = new AulaFS();
                prenotazioneAulaDAO = new PrenotazioneAulaFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            this.prenotazioneFactory = new ConcretePrenotazioneAulaFactory();
        }
    }

    public boolean prenota(PrenotazioneAulaBean prenotazione) {
        ConcretePrenotazioneAulaFactory factory = new ConcretePrenotazioneAulaFactory();
        PrenotazioneAula prenotazioneAula = factory.creaPrenotazione(prenotazione);
        if (prenotazioneAula.verificaPrenotazione()) {
           // ConfermPrenotazioneView.confermaPrenotazione(prenotazione);
            prenotazioneAula.salvaPrenotazione();
            return true;
        } return false;

    }

    public void VerificaDisponiblitàByID(String iDAula){

    }
    public PrenotazioneAulaBean PrenotaAulaById(String iDAula){
        PrenotazioneAulaBean prenotazioneAulaBean= new PrenotazioneAulaBean();
        return prenotazioneAulaBean;
    }

    public List<Object> getAuleByMateria(PrenotazioneAulaBean pab) {
        return List.of();
    }

    public List<Object> getAulaByProfessore(PrenotazioneAulaBean pab) {
        return new ArrayList<>();
    }

    public List<Object> getAulaByOrario(PrenotazioneAulaBean pab) {
        return new ArrayList<>();
    }
    public List<PrenotazioneAulaBean> getBookedClassByteacherNameAndSubject(PrenotazioneAulaBean prenotazioneAulaBean, UserModel usr) throws SQLException, SQLException, ItemNotFoundException {
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

    public boolean modificaDescrizionePrenotazione(PrenotazioneAulaBean pb) {
        return true;
    }

    public void removePrenotazione() {
    }

    public List<Object> getPrenotazioneByName(UserBean us) {
        return null;
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
