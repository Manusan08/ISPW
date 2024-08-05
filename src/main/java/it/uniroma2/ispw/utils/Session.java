package it.uniroma2.ispw.utils;

import it.uniroma2.ispw.bean.DocenteBean;
import it.uniroma2.ispw.bean.IdSessioneBean;
import it.uniroma2.ispw.bean.StudenteBean;
import it.uniroma2.ispw.bean.UserBean;

public class Session {
    private final DocenteBean docenteBean;
    private final StudenteBean studenteBean;

    private final IdSessioneBean idSession;

    public DocenteBean getDocenteBean() {
        return docenteBean;
    }

    public StudenteBean getStudenteBean() {
        return studenteBean;
    }

    public Session(DocenteBean docenteBean, StudenteBean studenteBean, IdSessioneBean idSession) {
        this.docenteBean = docenteBean;
        this.studenteBean = studenteBean;
        this.idSession = idSession;
    }

    public IdSessioneBean getIdSessionBean() {
        return idSession;
    }

    public UserBean getUserBean() {
        return null;
    }
}
