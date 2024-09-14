package it.uniroma2.ispw.model.prenotazioneposto.dao;



import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;

public class PrenotazionePostoDAOFactoryImpl implements  PrenotazionePostoDAOFactory {

    public PrenotazionePostoDAO getDao() {
        TypesOfPersistenceLayer typesOfPersistenceLayer = Conf.getConf().getTypesOfPersistenceLayer();
        switch (typesOfPersistenceLayer) {
            case JDBC -> {
                return new PrenotazionePostoDBMS();
            }
            case FILE_SYSTEM -> {
                return new PrenotazionePostoFS();
            }
            case null, default -> throw new IllegalArgumentException("typeOfPersistence Invalid, got " + typesOfPersistenceLayer);
        }
    }
}
