module ispw{
    requires javafx.controls;
    requires java.sql;
    requires com.opencsv;
    requires org.apache.commons.lang3;
    requires javafx.fxml;

    exports it.uniroma2.ispw;


    opens it.uniroma2.ispw.controller.controllergrafico1 to javafx.fxml;
    exports it.uniroma2.ispw.controller.controllerApplicativo to javafx.fxml;


    opens it.uniroma2.ispw to javafx.fxml;


}