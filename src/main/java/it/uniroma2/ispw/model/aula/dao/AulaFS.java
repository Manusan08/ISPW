package it.uniroma2.ispw.model.aula.dao;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public class AulaFS implements AulaDAO {


    @Override
    public List<AulaModel> getAllAule() {
        return List.of();
    }

    @Override
    public List<AulaModel> getAulaByFiltri(AulaModel aulaM) {
        return null;
    }



    @Override
    public AulaModel getAulaById(String Id) throws ItemNotFoundException {
        return null;
    }




}
