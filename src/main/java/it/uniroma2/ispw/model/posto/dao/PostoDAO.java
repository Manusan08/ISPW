package it.uniroma2.ispw.model.posto.dao;

import it.uniroma2.ispw.model.posto.PostoModel;

import java.sql.SQLException;

public interface PostoDAO {
    void postoNuovamenteDisponibile(PostoModel postoModel) throws SQLException;
}