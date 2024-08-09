package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class GestisciPrenotazioneController {

    private AulaDAO aulaDAO;

    public GestisciPrenotazioneController() {
        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            aulaDAO = new AulaDBMS();
        } else {
            try {
                aulaDAO = new AulaFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }

    public List<AulaBean> getAllAule() {
        List<AulaBean> aulaBeanList = new ArrayList<>();
        for (AulaModel c : aulaDAO.getAllAule()) {
            AulaBean cb = new AulaBean(c.getIdAula(), c.getNumeroPosti());

            aulaBeanList.add(cb);
        }
        return aulaBeanList;

    }

    public AulaModel getAulaById(String aula) throws ItemNotFoundException {
        return aulaDAO.getAulaById(aula);
    }

    public AulaModel getAulaByCognoem(String aula) throws ItemNotFoundException {
        return aulaDAO.getAulaByCognome(aula);
    }

    public AulaModel getAulaByMateria(String aula) throws ItemNotFoundException {
        return aulaDAO.getAulaByMateria(aula);
    }

    public void removePrenotazione() {
    }

    public void getAllReservation() {
    }

    public List<Object> getAuleByMateria(AulaBean aulaBean) {
        return List.of();
    }

    public List<Object> getAuleByDay(AulaBean ab) {
        return List.of();
    }

    public boolean prenota() {//questo metodo deve fare la prenotazione dell'aula
        return true;
    }

    public List<Object> getPrenotazioneByName(UserBean us) {
        //TODO
        return List.of();
    }

    public boolean modificaDescrizionePrenotazione(PrenotazioneAulaBean pb) {
        //TODO
        return true;
    }
}