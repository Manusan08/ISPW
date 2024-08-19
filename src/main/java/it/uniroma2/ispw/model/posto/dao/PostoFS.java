package it.uniroma2.ispw.model.posto.dao;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.model.posto.PostoModel;

import java.util.List;

public class PostoFS implements PostoDAO{
    @Override
    public void postoNuovamenteDisponibile(PostoModel postoModel) {

    }

    @Override
    public List<PostoModel> getAvailablePosti(PrenotazioneAulaBean pab) {
        return List.of();
    }
}
