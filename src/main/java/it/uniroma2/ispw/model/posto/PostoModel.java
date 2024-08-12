package it.uniroma2.ispw.model.posto;

public class PostoModel {

    String postoId;
    boolean stato;
    String  idAula;

    public PostoModel(String idPosto, String idAula) {
    }


    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getPostoId() {
        return postoId;
    }

    public void setPostoId(String postoId) {
        this.postoId = postoId;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
}

