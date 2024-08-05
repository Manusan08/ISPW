package it.uniroma2.ispw.Controller.controllerGrafico2.studente;

import it.uniroma2.ispw.Controller.controllerGrafico2.TemplateView;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.LoginBean;

import java.io.IOException;
import java.util.List;

public class CercaAulaView  extends TemplateView {


    @Override
    public LoginBean control() {
        int choice;
        boolean cond= true;

        while (cond){
            choice =this.userChoice();
            switch (choice){
                case 1->inserisciCampiDiricerca();
                case 2 -> cond=false;
                default -> System.out.println("riprova");
            }
        }
        return null;
    }
    private void inserisciCampiDiricerca() {
        AulaBean aulaBean=new AulaBean();
        try{
            aulaBean.setMateria(getDesiredIn("Materia","inserisci il nome della materia"));
            aulaBean.setNomeDocente(getDesiredIn("Nome","inserisci il nome della docente"));
        }catch (IOException e){
            System.out.println("Formato non valido");
        }
    }

    @Override
    protected List<String> getOptions() {
        return List.of();
    }

    @Override
    protected String getHeader() {
        return "";
    }

    @Override
    public void update(String... msg) {

    }
}
