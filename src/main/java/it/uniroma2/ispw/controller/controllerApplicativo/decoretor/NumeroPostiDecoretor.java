package it.uniroma2.ispw.controller.controllerApplicativo.decoretor;


import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.model.aula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.ArrayList;
import java.util.List;

    public class NumeroPostiDecoretor extends AulaComponent {


    private List<AulaBean> ControllaNumeroPosti(AulaBean aulaBean) throws SystemException {
        List<AulaBean> auleBean = new ArrayList<>();

        PrenotazioneAulaDBMS auleDao = new PrenotazioneAulaDBMS();
        List<AulaModel> lista = auleDao.getAllAuleNumeroPosti(aulaBean);


        for (AulaModel aulaModel : lista) {
            aulaBean=new AulaBean();
            aulaBean.setIdAula(aulaModel.getIdAula());
            aulaBean.setPosti(aulaModel.getNumeroPosti());
            auleBean.add(aulaBean);
        }
        return auleBean;
    }

    @Override
    public List<AulaBean> getAule() {
        return List.of();
    }

        @Override
        public List<AulaBean> getAule(int numeroPosti) throws SystemException {
            return List.of();
        }

        @Override
    public List<AulaBean> getAule(AulaBean aulaBean) throws SystemException {
        List<AulaBean> aule;
        aule = this.ControllaNumeroPosti(aulaBean);
        return aule;
    }


}

