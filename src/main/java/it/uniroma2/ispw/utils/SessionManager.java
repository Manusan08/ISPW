package it.uniroma2.ispw.utils;

import it.uniroma2.ispw.bean.DocenteBean;
import it.uniroma2.ispw.bean.IdSessioneBean;
import it.uniroma2.ispw.bean.StudenteBean;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private List<Session> sessioniAttive = new ArrayList<>();

    private static SessionManager instance = null;

    protected SessionManager(int i) {
    }

    public static SessionManager getSessionManager() {
        if (SessionManager.instance == null) {
            SessionManager.instance = new SessionManager(0);
        }
        return instance;
    }


    public Session createSession(DocenteBean docenteBean, StudenteBean studenteBean, IdSessioneBean idSession) {
        return new Session(docenteBean, studenteBean, idSession);
    }

    public Session getSessionFromId(IdSessioneBean id) {
        for (Session session : sessioniAttive) {
            if (session.getIdSessionBean().getId() == id.getId()) {
                return session;
            }
        }
        return null;
    }

    // Aggiunge una nuova sessione attive
    public void aggiungiSessione(Session sessione) {
        sessioniAttive.add(sessione);
    }

    // Rimuove una sessione attiva
    public void rimuoviSessione(IdSessioneBean idSession) {
        sessioniAttive.removeIf(sessione -> sessione.getIdSessionBean().getId() == idSession.getId());
    }
}

