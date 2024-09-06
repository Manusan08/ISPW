package it.uniroma2.ispw.facade;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.controller.controllerapplicativo.GestisciAuleController;
import it.uniroma2.ispw.controller.controllerapplicativo.GestisciCreazionePrenotazioneAulaController;

import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.util.List;

public class DocenteFacade {
    private final GestisciCreazionePrenotazioneAulaController gestisciCreazionePrenotazioneAulaController;
    private final GestisciAuleController gestisciAuleController;
    public DocenteFacade() {

        this.gestisciCreazionePrenotazioneAulaController = new GestisciCreazionePrenotazioneAulaController();
        this.gestisciAuleController= new GestisciAuleController();
    }
    public AulaBean getAulaById(String idAula) throws ItemNotFoundException {
        return this.gestisciAuleController.getAulaById(idAula);
    }

    public List<AulaBean> getAllAule() {
        return this.gestisciAuleController.getAllAule();
    }

    public List<AulaBean> chekAula(AulaBean aulaBean) throws ItemNotFoundException {
        return this.gestisciAuleController.chekAula(aulaBean);
    }

    public boolean prenota(PrenotazioneAulaBean pab){
        return this.gestisciCreazionePrenotazioneAulaController.prenota(pab);

    }
}
