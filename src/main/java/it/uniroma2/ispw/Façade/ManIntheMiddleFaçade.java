package it.uniroma2.ispw.Façade;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PostoBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciCreazionePrenotazioneAulaController;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazionePostoController;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.observers.Observer;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.observers.PostoObserver;
import it.uniroma2.ispw.controller.controllerApplicativo.Observer.subject.PrenotazionePostoSubject;
import it.uniroma2.ispw.controller.controllerApplicativo.factory.ConcretePrenotazioneAulaFactory;
import it.uniroma2.ispw.controller.controllergrafico2.studente.PrenotazionePostoContext;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManIntheMiddleFaçade {
    private GestisciPrenotazionePostoController gestisciPrenotazionePostoController;
    private PrenotazionePostoSubject prenotazionePostoSubject;
    private PostoObserver postoObserver;
    private GestisciCreazionePrenotazioneAulaController gestisciCreazionePrenotazioneAulaController;

    public ManIntheMiddleFaçade() {
        this.gestisciPrenotazionePostoController = new GestisciPrenotazionePostoController();
        this.postoObserver = new PostoObserver();
        this.prenotazionePostoSubject = new PrenotazionePostoSubject();
        this.gestisciCreazionePrenotazioneAulaController = new GestisciCreazionePrenotazioneAulaController();
    }


    public void createPrenotazionePostoSubject(PrenotazioneAulaBean pab) throws SQLException {
        this.prenotazionePostoSubject = this.gestisciPrenotazionePostoController.getAvailablePostiByPrenotazioneAulaid(pab);
    }

    public List<PostoBean> postoObserverToPostoBean(List<Observer> observers) {
        List<PostoBean> postoBeans = new ArrayList<>();
        if (observers.isEmpty())
            return postoBeans;
        for (Observer ob : observers) {
            if (ob instanceof PostoObserver po) {
                PostoBean postoBean = new PostoBean(po.getPostoId(), po.getIdAula(), po.isPrenotato());
                postoBeans.add(postoBean);
            }
        }
        return postoBeans;
    }

    public List<PostoBean> getPostiBean(PrenotazioneAulaBean pab) throws SQLException {
        createPrenotazionePostoSubject(pab);
        return postoObserverToPostoBean(this.prenotazionePostoSubject.getObservers());

    }

    public String insertPostoIntodb(PostoBean postoBean, PrenotazioneAulaBean pab, UserBean usb) throws SystemException, SQLException {
        UserModel usm= usrBeanToUsrModel(usb);
        return this.gestisciPrenotazionePostoController.prenotaPosto(postoBean, pab, usm);
    }


    public List<PostoBean> selezionaPosto(PrenotazionePostoContext ppc) {
        PostoObserver postoObserver1;
        postoObserver1 = isPostoInList(this.prenotazionePostoSubject.getObservers(), ppc);

        if (postoObserver1 != null) {
            if(postoObserver1.isPrenotato()){
                this.prenotazionePostoSubject.setStatusNonEffetutata();
            }
            else {
                this.prenotazionePostoSubject.setStatusEffettuata();
            this.prenotazionePostoSubject.notify(ppc);
            postoObserver1.update(ppc);
            }
            return postoObserverToPostoBean(this.prenotazionePostoSubject.getObservers());
        }
        return null;
    }

public boolean prenota(PrenotazioneAulaBean pab){
        return this.gestisciCreazionePrenotazioneAulaController.prenota(pab);

}


    public static PostoObserver isPostoInList(List<Observer> observers, PrenotazionePostoContext context) {
        for (Observer observer : observers)
            if (observer instanceof PostoObserver po)
                if (po.getPostoId().equals(context.getPostoId()))
                    return po;
        return null;
    }


    public List<PrenotazioneAulaBean> searchBySurnameAndSubject(PrenotazioneAulaBean pab, UserBean userBean) throws SQLException, ItemNotFoundException {
        UserModel usm=usrBeanToUsrModel(userBean);
        return this.gestisciCreazionePrenotazioneAulaController.getBookedClassByteacherNameAndSubject(pab, usm);
    }

    public int getCapienzaAula(String pab) throws SQLException {
        return this.gestisciCreazionePrenotazioneAulaController.getCapienzaAula(pab);

    }

    public List<PrenotazioneAulaBean>getAvailableClass(){
       return this.gestisciCreazionePrenotazioneAulaController.getAvailableClass();

    }

    public UserModel usrBeanToUsrModel(UserBean us) {
        return new UserModel(us.getEmail(), us.getRuolo(), us.getNome());
    }

}

