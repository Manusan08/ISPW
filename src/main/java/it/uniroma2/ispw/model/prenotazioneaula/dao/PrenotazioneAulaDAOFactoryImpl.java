package it.uniroma2.ispw.model.prenotazioneaula.dao;

import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;

public class PrenotazioneAulaDAOFactoryImpl implements PrenotazioneAulaDAOFactory {

    public PrenotazioneAulaDAO getDao() {
        TypesOfPersistenceLayer typesOfPersistenceLayer = Conf.getConf().getTypesOfPersistenceLayer();
        switch (typesOfPersistenceLayer) {
            case JDBC -> {
                return new PrenotazioneAulaDBMS();
            }
            case FILE_SYSTEM -> {
                return new PrenotazioneAulaFS();
            }
            case null, default -> throw new IllegalArgumentException("typeOfPersistence Invalid, got " + typesOfPersistenceLayer);
        }
    }
}
