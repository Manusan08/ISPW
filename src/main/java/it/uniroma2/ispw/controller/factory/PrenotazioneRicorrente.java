package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaFS;

import java.sql.Date;
import java.time.LocalDate;

public class PrenotazioneRicorrente implements PrenotazioneAula {

    private final String email;
    private final String iDaula;
    private final Orario oraLezione;
    private final Date datalezione;
    private final String descrizione;
    private final String materia;
    private final String idPrenotazioneAula;
    private final String nomeProfessore;
    private final Date dataFine;


    private final PrenotazioneAulaDAO prenotazioneAulaDAO;


    public PrenotazioneRicorrente(PrenotazioneAulaBean prenotazioneAulaBean) {
        this.email = prenotazioneAulaBean.getEmail();
        this.iDaula = prenotazioneAulaBean.getIdAula();
        this.oraLezione = prenotazioneAulaBean.getOraLezione();
        this.datalezione = (Date) prenotazioneAulaBean.getGiornoLezione();
        this.descrizione = prenotazioneAulaBean.getDescrizione();
        this.materia = prenotazioneAulaBean.getMateria();
        this.nomeProfessore = prenotazioneAulaBean.getNomeDocente();
        this.dataFine = (Date) prenotazioneAulaBean.getDataFine();
        this.idPrenotazioneAula = prenotazioneAulaBean.getIdPrenotazioneAula();


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
        LocalDate dataCorrente = datalezione.toLocalDate();
        LocalDate dataFineLocal = dataFine.toLocalDate();

        while (!dataCorrente.isAfter(dataFineLocal)) {
            if (prenotazioneAulaDAO.esistePrenotazione(iDaula, Date.valueOf(dataCorrente), oraLezione)) {
                return false; // L'aula è occupata in una delle date ricorrenti
            }
            dataCorrente = dataCorrente.plusDays(7); // Aggiungi 7 giorni per passare alla settimana successiva
        }

        return true; // L'aula è disponibile per tutte le date ricorrenti
    }

    @Override
    public void salvaPrenotazione() {
        LocalDate dataCorrente = datalezione.toLocalDate();
        LocalDate dataFineLocal = dataFine.toLocalDate();
        Boolean isRicorrente = true;

        while (!dataCorrente.isAfter(dataFineLocal)) {
            if (!prenotazioneAulaDAO.salvataggioPrenotazione(
                    email,
                    iDaula,
                    oraLezione,
                    Date.valueOf(dataCorrente),
                    descrizione,
                    materia,
                    nomeProfessore,
                    dataFine,
                    idPrenotazioneAula,
                    isRicorrente)) {
                return;
            }
            dataCorrente = dataCorrente.plusDays(7);
        }

    }
}
