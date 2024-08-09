package it.uniroma2.ispw.model.studente;

import it.uniroma2.ispw.model.UserModel;

public class StudenteModel extends UserModel {
    private String corso;
    private int matricola;

    public StudenteModel() {

    }

    public String getCorso() {
        return corso;
    }

    @Override
    public int getMatricola() {
        return matricola;
    }

    public StudenteModel(String email, int matricola) {
        super(email, matricola);
    }
}
