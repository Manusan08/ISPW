package it.uniroma2.ispw.model.aula.dao;


import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.util.Collections;
import java.util.List;

public class AulaFS implements AulaDAO {


    @Override
    public List<AulaModel> getAllAule() {
        return List.of();
    }

    @Override
    public List<AulaModel> getAulaByFiltri(AulaModel aulaM) {
        return Collections.emptyList();
    }



    @Override
    public AulaModel getAulaById(String id) throws ItemNotFoundException {
        return null;
    }




}
