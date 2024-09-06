package it.uniroma2.ispw.controller.controllerapplicativo.factory;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaFS;

import java.sql.Date;
import java.time.LocalDate;

public class PrenotazioneRicorrente implements PrenotazioneAula{
    private PrenotazioneAulaModel prenotazioneAulaModel;
    public  PrenotazioneRicorrente(PrenotazioneAulaModel model) {

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
    private Date dataFine;

    private AulaDAO aulaDAO;
    private PrenotazioneAulaDAO prenotazioneAulaDAO;
    private ConcretePrenotazioneAulaFactory prenotazioneFactory;



    public PrenotazioneRicorrente(String aulaBean, String emailDocente, Orario orario, Date dataInizio, String descrizione, String materia, String nomeProfessore, Date dataFine, String idPrenotazione) {
       this.email = emailDocente;
        this.iDaula = aulaBean;
        this.oraLezione = orario;
        this.datalezione = dataInizio;
        this.descrizione = descrizione;
        this.materia = materia;
        this.nomeProfessore = nomeProfessore;
        this.dataFine = dataFine;
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
        Boolean isRicorrente=true;

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
