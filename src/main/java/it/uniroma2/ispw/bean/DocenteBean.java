package it.uniroma2.ispw.bean;

import it.uniroma2.ispw.model.docente.DocenteModel;

public class DocenteBean extends UserBean{
    private String dipartimento;
    private final String materia;
    private final int matricola;

    public DocenteBean(DocenteModel docente) {
        super(docente.getEmail(), docente.getNome(), docente.getCognome());
        this.materia=docente.getMateria();
        this.matricola=docente.getMatricola();

    }
}
