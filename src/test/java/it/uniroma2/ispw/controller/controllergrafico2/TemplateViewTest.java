package it.uniroma2.ispw.controller.controllergrafico2;

import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemplateViewTest extends TemplateView {


    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {

    }

    @Override
    protected List<String> getOptions() {
        return List.of("1 ", "2 ", "3 ");
    }

    @Override
    protected String getHeader() {
        return "ciao";
    }

    public static void main(String[]args){
        TemplateViewTest test = new TemplateViewTest();
        try {
            int choice=test.userChoice();
            System.out.println("user option ="+choice);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}