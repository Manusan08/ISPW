module ispw {
    requires javafx.controls;
    requires java.sql;
    requires com.opencsv;
    requires org.apache.commons.lang3;
    requires javafx.fxml;
    requires javafx.base;

    opens it.uniroma2.ispw to org.junit.platform.commons;

    // Esportare il pacchetto principale
    exports it.uniroma2.ispw;
    exports it.uniroma2.ispw.utils; // Esporta a tutti i moduli
    exports it.uniroma2.ispw.utils.facade;
    exports it.uniroma2.ispw.utils.exception;
    exports it.uniroma2.ispw.bean;
    exports it.uniroma2.ispw.model.login.dao;
    exports it.uniroma2.ispw.view.cli.studente;
    exports it.uniroma2.ispw.enums;
    exports it.uniroma2.ispw.model.login;
    exports  it.uniroma2.ispw.model;
    exports  it.uniroma2.ispw.controller.observer.observers;
    exports   it.uniroma2.ispw.model.prenotazioneaula;
    exports  it.uniroma2.ispw.controller.observer;
    exports it.uniroma2.ispw.view.cli;

    // Aprire i pacchetti dei controller grafici per FXML
    opens it.uniroma2.ispw.view.graphicalcontroller to javafx.fxml, javafx.graphics,javafx.base;

    // Esportare altri pacchetti se necessario
    exports it.uniroma2.ispw.controller to javafx.fxml, javafx.graphics,javafx.base;

    // Aprire il pacchetto principale per FXML


    // Esportare il pacchetto specifico per i controller FXML
    opens it.uniroma2.ispw.view.graphicalcontroller.studente to javafx.fxml, javafx.graphics,javafx.base;
    opens it.uniroma2.ispw.view.graphicalcontroller.docente to javafx.fxml, javafx.graphics,javafx.base;
    opens it.uniroma2.ispw.bean to javafx.fxml, javafx.graphics,javafx.base;
    exports it.uniroma2.ispw.controller.factory.strategy to javafx.base, javafx.fxml, javafx.graphics;


}
