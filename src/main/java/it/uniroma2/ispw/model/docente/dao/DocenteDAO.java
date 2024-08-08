package it.uniroma2.ispw.model.docente.dao;

import it.uniroma2.ispw.model.docente.DocenteModel;
import it.uniroma2.ispw.utils.exception.SystemException;

public interface DocenteDAO {
    public DocenteModel getDocentebyEmail(String email) throws SystemException;
}
