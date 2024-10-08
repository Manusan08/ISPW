package it.uniroma2.ispw.bean;



import java.util.Date;

public class PrenotazionePostoBean {

    private String idPrenotazioneAula;
    private String idPrenotazionePosto;
    private String idAula;
    private String idPosto;
    private String oraLezione;
    private Date giornoLezione;

    private String materia;

    private  String nomeDocente;

    public PrenotazionePostoBean(String idPosto, String idAula, String materia, Date giornoLezione, String oraLezione, String idPrenotazionePosto,String idPrenotazioneAula) {
        this.idPosto=idPosto;
        this.idAula=idAula;
        this.materia=materia;
        this.giornoLezione=giornoLezione;
        this.oraLezione=oraLezione;
        this.idPrenotazionePosto=idPrenotazionePosto;

        this.idPrenotazioneAula=idPrenotazioneAula;

    }

    public PrenotazionePostoBean() {

    }



    public Date getGiornoLezione() {
        return giornoLezione;
    }

    public void setGiornoLezione(Date giornoLezione) {
        this.giornoLezione = giornoLezione;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getIdPosto() {
        return idPosto;
    }

    public void setIdPosto(String idPosto) {
        this.idPosto = idPosto;
    }

    public String getIdPrenotazionePosto() {
        return idPrenotazionePosto;
    }

    public void setIdPrenotazionePosto(String idPrenotazionePosto) {
        this.idPrenotazionePosto = idPrenotazionePosto;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }


    public String getOraLezione() {
        return oraLezione;
    }

    public void setOraLezione(String oraLezione) {
        this.oraLezione = oraLezione;
    }




    public String getIdPrenotazioneAula() {
        return idPrenotazioneAula;
    }
}
