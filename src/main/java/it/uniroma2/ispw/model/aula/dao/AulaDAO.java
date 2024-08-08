package it.uniroma2.ispw.model.aula.dao;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public interface AulaDAO {
    public List<AulaModel> getAllAuleNumeroPosti(AulaBean aulaBean) throws SystemException;
}
