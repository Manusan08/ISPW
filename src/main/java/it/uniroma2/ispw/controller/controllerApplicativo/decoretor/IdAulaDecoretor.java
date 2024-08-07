package it.uniroma2.ispw.controller.controllerApplicativo.decoretor;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public class IdAulaDecoretor extends Decoretor {
    public IdAulaDecoretor(AulaComponent component) {
        super(component);
    }

    @Override
    public List<AulaBean> getAule(AulaBean aulaBean) throws SystemException {
        return List.of();
    }
}
