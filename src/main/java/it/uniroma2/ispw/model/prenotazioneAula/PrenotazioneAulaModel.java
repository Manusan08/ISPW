package it.uniroma2.ispw.model.prenotazioneAula;

import it.uniroma2.ispw.enums.Orario;

import java.sql.Date;

public  class PrenotazioneAulaModel {
    private  String email;
    private String iDaula;
    private Orario oraLezione;
    private Date datalezione;
    private String descrizione;
    private String materia;
    private String idPrenotazioneAula;
    private String nomeProfessore;
    private boolean isRicorente;
    private java.util.Date dataFine;




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






    private String cognomeProfessore;

    public PrenotazioneAulaModel(String nomeProfessore, String materia) {
        this.nomeProfessore=nomeProfessore;
        this.materia=materia;
    }

    public PrenotazioneAulaModel() {
    }



    public void setDatalezione(Date datalezione) {
        this.datalezione = datalezione;
    }



    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }





    public void setiDaula(String iDaula) {
        this.iDaula = iDaula;
    }



    public void setIdPrenotazioneAula(String idPrenotazioneAula) {
        this.idPrenotazioneAula = idPrenotazioneAula;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }



    public void setNomeProfessore(String nomeProfessore) {
        this.nomeProfessore = nomeProfessore;
    }


    public void setOraLezione(String oraLezione) {

        Orario orario=Orario.valueOf(oraLezione);
        this.oraLezione =orario ;
    }
}
