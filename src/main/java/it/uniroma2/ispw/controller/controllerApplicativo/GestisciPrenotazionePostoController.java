package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;

public class GestisciPrenotazionePostoController {
    private AulaDAO aulaDAO;
    public GestisciPrenotazionePostoController() {
        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            aulaDAO = new AulaDBMS();
        } else {
            try {
                aulaDAO = new AulaFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }
}
