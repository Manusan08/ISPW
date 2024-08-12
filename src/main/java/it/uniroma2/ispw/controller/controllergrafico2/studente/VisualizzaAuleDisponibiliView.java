package it.uniroma2.ispw.controller.controllergrafico2.studente;


import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class VisualizzaAuleDisponibiliView extends TemplateView  {
    //TODO da implementare!

    @Override
   public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {
    }

    private void visualizzaAuleDisponibili() {
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
