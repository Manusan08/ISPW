package it.uniroma2.ispw.controller.controllergrafico2.studente;


import it.uniroma2.ispw.Façade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class VisualizzaAuleDisponibiliView extends TemplateView {


    ManIntheMiddleFaçade intheMiddle = new ManIntheMiddleFaçade();

    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {
        visualizzaAuleDisponibili();

    }

    private void visualizzaAuleDisponibili() {
        printTable(intheMiddle.getAvailableClass());
    }

    @Override
    protected List<String> getOptions() {
        return List.of();
    }

    @Override
    protected String getHeader() {
        return "";
    }
}

