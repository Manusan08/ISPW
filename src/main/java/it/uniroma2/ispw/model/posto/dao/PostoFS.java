package it.uniroma2.ispw.model.posto.dao;

import it.uniroma2.ispw.model.posto.PostoModel;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

import java.util.List;

public class PostoFS implements PostoDAO{
    @Override
    public void postoNuovamenteDisponibile(PostoModel postoModel) {
        // TODO document why this method is empty
    }

    @Override
    public List<PostoModel> getAvailablePosti(PrenotazioneAulaModel pab) {
        return List.of();
    }
}
