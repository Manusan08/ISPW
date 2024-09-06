package it.uniroma2.ispw.model.aula.dao;


import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;


import java.util.List;

public interface AulaDAO {

    public List<AulaModel> getAllAule();
    public List<AulaModel> getAulaByFiltri(AulaModel aulaM) throws ItemNotFoundException;
    public AulaModel getAulaById(String id) throws ItemNotFoundException;



}

