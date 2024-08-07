package it.uniroma2.ispw.controller.controllerApplicativo.decoretor;


import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.dao.AulaDao;
import it.uniroma2.ispw.model.AulaModel;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public class NumeroPostiDecoretor extends Decoretor {


    public NumeroPostiDecoretor(AulaComponent component) {
        super(component);

    }

    private List<AulaBean> ControllaNumeroPosti(int numeroPosti) throws SystemException {
        AulaBean aulaBean = null;
        List<AulaBean> auleBean = null;

        AulaDao auleDao = new AulaDao();
        List<AulaModel> lista = auleDao.getAllAuleNumeroPosti(numeroPosti);


        for (AulaModel aulaModel : lista) {
            aulaBean=new AulaBean();
            aulaBean.setIdAula(aulaModel.getIdAula());
            auleBean.add(aulaBean);
        }
        return auleBean;
    }

    @Override
    public List<AulaBean> getAule(int numeroPosti) throws SystemException {
        List<AulaBean> aule;
        aule = this.ControllaNumeroPosti(numeroPosti);
        return aule;
    }


}

