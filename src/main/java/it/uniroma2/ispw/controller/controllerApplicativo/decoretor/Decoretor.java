package it.uniroma2.ispw.controller.controllerApplicativo.decoretor;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public abstract class Decoretor extends AulaComponent {

    private AulaComponent component;

    public Decoretor(AulaComponent component) {
        this.component = component;
    }

    @Override
    public List<AulaBean> getAule() {
        List<AulaBean> aule =this.component.getAule();
        return aule;
    }
    @Override
    public List<AulaBean> getAule(int numeroPosti) throws SystemException {
        List<AulaBean> aule =this.component.getAule(numeroPosti);
        return aule;
    }

}

