package it.uniroma2.ispw.controller;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDAOFactory;
import it.uniroma2.ispw.model.aula.dao.AulaDAOFactoryImpl;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.ArrayList;
import java.util.List;

public class GestisciAuleController {
    private final AulaDAO aulaDAO;

    public GestisciAuleController() {
        AulaDAOFactory daoFactory = new AulaDAOFactoryImpl();
        aulaDAO = daoFactory.getDao();
    }


    public List<AulaBean> chekAula(AulaBean aulaBean) throws ItemNotFoundException, SystemException {

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

    public List<AulaBean> getAllAule() throws SystemException {
        List<AulaBean> aulaBeanList = new ArrayList<>();
        for (AulaModel c : aulaDAO.getAllAule()) {
            AulaBean cb = new AulaBean(c.getIdAula(), c.getNumeroPosti(), c.isProiettore(), c.isComputer(), c.isBanchiDisegno());

            aulaBeanList.add(cb);
        }
        return aulaBeanList;

    }

    public AulaBean getAulaById(String idaula) throws ItemNotFoundException, SystemException {

        AulaModel c = aulaDAO.getAulaById(idaula);
        return new AulaBean(c.getIdAula(), c.getNumeroPosti(), c.isComputer(), c.isProiettore(), c.isBanchiDisegno());

    }

}









