package it.uniroma2.ispw.bean;

import it.uniroma2.ispw.model.StudenteModel;

public class StudenteBean extends UserBean {
    private final String corso;
    private final int matricola;


    public StudenteBean(StudenteModel studente) {
        super(studente.getEmail(), studente.getNome(), studente.getCognome());
        this.corso=studente.getCorso();
        this.matricola=studente.getMatricola();

    }
}
