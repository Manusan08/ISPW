package it.uniroma2.ispw.bean;

public class PostoBean {
    private String idPosto;

    private String idAula;

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

    public boolean isStatoPosto() {
        return statoPosto;
    }

    public void setStatoPosto(boolean statoPosto) {
        this.statoPosto = statoPosto;
    }

    private boolean statoPosto;
}
