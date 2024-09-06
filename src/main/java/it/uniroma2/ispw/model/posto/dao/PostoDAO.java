package it.uniroma2.ispw.model.posto.dao;

import it.uniroma2.ispw.model.posto.PostoModel;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

import java.sql.SQLException;
import java.util.List;

public interface PostoDAO {
    void postoNuovamenteDisponibile(PostoModel postoModel) throws SQLException;
    List<PostoModel> getAvailablePosti(PrenotazioneAulaModel pab) throws SQLException;
}