package it.uniroma2.ispw.controller.controllerApplicativo.decoretor;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public abstract class AulaComponent {
    public abstract List<AulaBean> getAule();
    public abstract List<AulaBean> getAule(int numeroPosti) throws SystemException;

    public abstract List<AulaBean> getAule(AulaBean aulaBean) throws SystemException;
}
