module ispw{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens it.uniroma2.ispw.controller.controllergrafico1 to javafx.fxml;
    exports it.uniroma2.ispw.controller.controllerApplicativo to javafx.fxml;
    exports it.uniroma2.ispw to javafx.graphics;
    opens it.uniroma2.ispw to javafx.fxml;

}