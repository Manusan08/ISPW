package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.bean.LoginBean;

import java.util.List;

public class VisualizzaAuleDisponibiliView extends TemplateView {
    @Override
    public LoginBean control() {

        return null;
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
