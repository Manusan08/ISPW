package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaFS;

import java.sql.Date;

public class PrenotazioneSingola implements PrenotazioneAula{


    private final String email;
    private final String iDaula;
    private final Orario oraLezione;
    private final Date datalezione;
    private final String descrizione;
    private final String materia;
    private final String idPrenotazioneAula;
    private final String nomeProfessore;

    private final PrenotazioneAulaDAO prenotazioneAulaDAO;



    public PrenotazioneSingola(PrenotazioneAulaBean prenotazioneAulaBean) {
        this.email = prenotazioneAulaBean.getEmail();
        this.iDaula = prenotazioneAulaBean.getIdAula();
        this.oraLezione = prenotazioneAulaBean.getOraLezione();
        this.datalezione = (Date) prenotazioneAulaBean.getGiornoLezione();
        this.descrizione = prenotazioneAulaBean.getDescrizione();
        this.materia = prenotazioneAulaBean.getMateria();
        this.nomeProfessore = prenotazioneAulaBean.getNomeDocente();
        this.idPrenotazioneAula =prenotazioneAulaBean.getIdPrenotazioneAula();

        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {

            prenotazioneAulaDAO = new PrenotazioneAulaDBMS();
        } else {
            try {

                prenotazioneAulaDAO = new PrenotazioneAulaFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }


    }


    @Override
    public boolean verificaPrenotazione() {
        return !prenotazioneAulaDAO.esistePrenotazione(iDaula, datalezione, oraLezione); // L'aula è occupata in una delle date ricorrenti
    }

    @Override
    public void salvaPrenotazione() {

        boolean isRicorrente = false;
        if (prenotazioneAulaDAO.salvataggioPrenotazione(
                email,
                iDaula,
                oraLezione,
                datalezione,
                descrizione,
                materia,
                nomeProfessore,
                null,
                idPrenotazioneAula,
                isRicorrente)) {
        }
    }
}

