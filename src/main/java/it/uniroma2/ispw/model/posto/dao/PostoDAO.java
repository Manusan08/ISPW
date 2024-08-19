package it.uniroma2.ispw.model.posto.dao;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.model.posto.PostoModel;

import java.sql.SQLException;
import java.util.List;

public interface PostoDAO {
    void postoNuovamenteDisponibile(PostoModel postoModel) throws SQLException;
    List<PostoModel> getAvailablePosti(PrenotazioneAulaBean pab) throws SQLException;
}