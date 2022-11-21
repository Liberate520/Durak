module ru.durak.kostya {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.durak.Kostya to javafx.fxml;
    exports ru.durak.Kostya;
}