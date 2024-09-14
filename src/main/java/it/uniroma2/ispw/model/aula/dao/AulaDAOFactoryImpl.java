package it.uniroma2.ispw.model.aula.dao;

import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;


public class AulaDAOFactoryImpl implements  AulaDAOFactory{

    public AulaDAO getDao() {
        TypesOfPersistenceLayer typesOfPersistenceLayer = Conf.getConf().getTypesOfPersistenceLayer();
        switch (typesOfPersistenceLayer) {
            case JDBC -> {
                return new AulaDBMS();
            }
            case FILE_SYSTEM -> {
                return new AulaFS();
            }
            case null, default -> throw new IllegalArgumentException("typeOfPersistence Invalid, got " + typesOfPersistenceLayer);
        }
    }
}
