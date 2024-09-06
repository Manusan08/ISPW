package it.uniroma2.ispw.controller.controllerapplicativo.factory;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaFS;

import java.sql.Date;

public class PrenotazioneSingola implements PrenotazioneAula{
    private PrenotazioneAulaBean prenotazioneAulaModel;

    public PrenotazioneSingola(PrenotazioneAulaBean model) {
        this.prenotazioneAulaModel = model;
    }

    private String email;
    private String iDaula;
    private Orario oraLezione;
    private Date datalezione;
    private String descrizione;
    private String materia;
    private String idPrenotazioneAula;
    private String nomeProfessore;
    private AulaDAO aulaDAO;
    private PrenotazioneAulaDAO prenotazioneAulaDAO;
    private ConcretePrenotazioneAulaFactory prenotazioneFactory;


    public PrenotazioneSingola(String aulaBean, String emailDocente, Orario orario, Date dataInizio, String descrizione, String materia, String nomeProfessore, String idPrenotazione) {
        this.email = emailDocente;
        this.iDaula = aulaBean;
        this.oraLezione = orario;
        this.datalezione = dataInizio;
        this.descrizione = descrizione;
        this.materia = materia;
        this.nomeProfessore = nomeProfessore;
        this.idPrenotazioneAula =idPrenotazione;

        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            aulaDAO = new AulaDBMS();
            prenotazioneAulaDAO = new PrenotazioneAulaDBMS();
        } else {
            try {
                aulaDAO = new AulaFS();
                prenotazioneAulaDAO = new PrenotazioneAulaFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
        this.prenotazioneFactory = new ConcretePrenotazioneAulaFactory();

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

