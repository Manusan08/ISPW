package it.uniroma2.ispw.model.studente.dao;

import it.uniroma2.ispw.model.studente.StudenteModel;
import it.uniroma2.ispw.utils.exception.SystemException;

public interface StudenteDAO {
    public StudenteModel getStudentebyEmail(String email) throws SystemException;
}
