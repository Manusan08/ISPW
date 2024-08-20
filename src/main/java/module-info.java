module ispw {
    requires javafx.controls;
    requires java.sql;
    requires com.opencsv;
    requires org.apache.commons.lang3;
    requires javafx.fxml;

    // Esportare il pacchetto principale
    exports it.uniroma2.ispw;

    // Aprire i pacchetti dei controller grafici per FXML
    opens it.uniroma2.ispw.controller.controllergrafico1 to javafx.fxml;

    // Esportare altri pacchetti se necessario
    exports it.uniroma2.ispw.controller.controllerApplicativo to javafx.fxml;

    // Aprire il pacchetto principale per FXML
    opens it.uniroma2.ispw to javafx.fxml;

    // Esportare il pacchetto specifico per i controller FXML
    opens it.uniroma2.ispw.controller.controllergrafico1.studente to javafx.fxml;
    opens it.uniroma2.ispw.controller.controllergrafico1.docente to javafx.fxml;
}
