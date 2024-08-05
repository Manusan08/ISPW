package it.uniroma2.ispw.model;

public class DocenteModel extends UserModel{
    private String materia;
    private int matricola;

    public String getMateria() {
        return materia;
    }

    @Override
    public int getMatricola() {
        return matricola;
    }

    public DocenteModel(String email, int matricola) {
        super(email, matricola);
    }
}

