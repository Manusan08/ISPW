package it.uniroma2.ispw.controller.controllerapplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class GestisciAuleController {
    private final AulaDAO aulaDAO;

    public GestisciAuleController() {
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


    public List<AulaBean> chekAula(AulaBean aulaBean) throws ItemNotFoundException {

        List<AulaBean> aulaBeanList = new ArrayList<>();
        AulaModel aulaM = new AulaModel();
        aulaM.setNumeroPosti(aulaBean.getPosti());
        aulaM.setProiettore(aulaBean.isProiettore());
        aulaM.setComputer(aulaBean.isComputer());
        aulaM.setBanchiDisegno(aulaBean.isBanchiDisegno());
        for (AulaModel aulaModel : aulaDAO.getAulaByFiltri(aulaM)) {
            AulaBean ab = new AulaBean(aulaModel.getIdAula(), aulaModel.getNumeroPosti(), aulaModel.isProiettore(), aulaModel.isBanchiDisegno(), aulaModel.isComputer());
            aulaBeanList.add(ab);
        }

        return aulaBeanList;
    }

    public List<AulaBean> getAllAule() {
        List<AulaBean> aulaBeanList = new ArrayList<>();
        for (AulaModel c : aulaDAO.getAllAule()) {
            AulaBean cb = new AulaBean(c.getIdAula(), c.getNumeroPosti(), c.isProiettore(), c.isComputer(), c.isBanchiDisegno());

            aulaBeanList.add(cb);
        }
        return aulaBeanList;

    }

    public AulaBean getAulaById(String idaula) throws ItemNotFoundException {

        AulaModel c = aulaDAO.getAulaById(idaula);
        return new AulaBean(c.getIdAula(), c.getNumeroPosti(), c.isComputer(), c.isProiettore(), c.isBanchiDisegno());

    }

}









