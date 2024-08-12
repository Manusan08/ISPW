package it.uniroma2.ispw.model.prenotazioneAula;

import it.uniroma2.ispw.enums.Orario;

import java.sql.Date;

public class PrenotazioneAulaModel {
    private  String email;
    private String iDaula;
    private Orario oraLezione;
    private Date datalezione;
    private String descrizione;
    private String materia;
    private String idPrenotazioneAula;
    private String nomeProfessore;
}
