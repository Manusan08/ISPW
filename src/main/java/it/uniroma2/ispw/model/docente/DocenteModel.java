package it.uniroma2.ispw.model.docente;

import it.uniroma2.ispw.model.UserModel;

public class DocenteModel extends UserModel {
    private String materia;
    private int matricola;

    public String getMateria() {
        return materia;
    }

    public DocenteModel() {
        super();
    }

    @Override
    public int getMatricola() {
        return matricola;
    }

    public DocenteModel(String email, int matricola) {
        super(email, matricola);
    }
}

