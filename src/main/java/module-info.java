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
    exports it.uniroma2.ispw.facade;
    exports it.uniroma2.ispw.utils.exception;
    exports it.uniroma2.ispw.bean;

    // Aprire i pacchetti dei controller grafici per FXML
    opens it.uniroma2.ispw.controller.controllergrafico1 to javafx.fxml, javafx.graphics,javafx.base;

    // Esportare altri pacchetti se necessario
    exports it.uniroma2.ispw.controller.controllerapplicativo to javafx.fxml, javafx.graphics,javafx.base;

    // Aprire il pacchetto principale per FXML


    // Esportare il pacchetto specifico per i controller FXML
    opens it.uniroma2.ispw.controller.controllergrafico1.studente to javafx.fxml, javafx.graphics,javafx.base;
    opens it.uniroma2.ispw.controller.controllergrafico1.docente to javafx.fxml, javafx.graphics,javafx.base;
    opens it.uniroma2.ispw.bean to javafx.fxml, javafx.graphics,javafx.base;


}
