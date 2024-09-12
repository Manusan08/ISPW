package it.uniroma2.ispw.utils.facade;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.controller.GestisciAuleController;
import it.uniroma2.ispw.controller.GestisciCreazionePrenotazioneAulaController;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public class DocenteFacade {
    private final GestisciCreazionePrenotazioneAulaController gestisciCreazionePrenotazioneAulaController;
    private final GestisciAuleController gestisciAuleController;
    public DocenteFacade() {

        this.gestisciCreazionePrenotazioneAulaController = new GestisciCreazionePrenotazioneAulaController();
        this.gestisciAuleController= new GestisciAuleController();
    }
    public AulaBean getAulaById(String idAula) throws ItemNotFoundException, SystemException {
        return this.gestisciAuleController.getAulaById(idAula);
    }

    public List<AulaBean> getAllAule() throws SystemException {
        return this.gestisciAuleController.getAllAule();
    }

    public List<AulaBean> chekAula(AulaBean aulaBean) throws ItemNotFoundException, SystemException {
        return this.gestisciAuleController.chekAula(aulaBean);
    }

    public boolean prenota(PrenotazioneAulaBean pab){
        return this.gestisciCreazionePrenotazioneAulaController.prenota(pab);

    }
}
