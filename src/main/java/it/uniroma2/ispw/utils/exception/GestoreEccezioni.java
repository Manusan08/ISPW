package it.uniroma2.ispw.utils.exception;

import it.uniroma2.ispw.utils.factory.DialogBox;
import it.uniroma2.ispw.utils.factory.Factory;

public class GestoreEccezioni {
    private GestoreEccezioni() {
        // ignored
    }

    private static GestoreEccezioni instance = null;
    private final Factory factory = new Factory();

    public static GestoreEccezioni getInstance() {
        if (instance == null) {
            instance = new GestoreEccezioni();
        }
        return instance;
    }

    public void handleException(Exception e) {
        DialogBox myDialogBox = factory.getBox(e);
        myDialogBox.showBox(e.getMessage());
    }
}
