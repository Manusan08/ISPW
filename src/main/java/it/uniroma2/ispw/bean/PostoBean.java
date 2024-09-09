package it.uniroma2.ispw.bean;



public class PostoBean {
    private String idPosto;
    private String idAula;
    private boolean isPrenotato;



    public PostoBean(String idPosto,String idAula, boolean isPrenotato) {
        this.idPosto=idPosto;
        this.isPrenotato=isPrenotato;
        this.idAula=idAula;
    }

    public PostoBean() {

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

    public boolean isPrenotato() {
        return isPrenotato;
    }

    public void setIsPrenotato(boolean statoPosto) {
        this.isPrenotato = statoPosto;
    }

    public String getPostoId() {
        return  this.idPosto;
    }
}
