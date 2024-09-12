package it.uniroma2.ispw.model.aula.dao;


import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public interface AulaDAO {

    public List<AulaModel> getAllAule() throws SystemException;
    public List<AulaModel> getAulaByFiltri(AulaModel aulaM) throws ItemNotFoundException, SystemException;
    public AulaModel getAulaById(String id) throws ItemNotFoundException, SystemException;



}

