module com.example.poke_appv1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires json.simple;

    opens com.example.poke_appv1 to javafx.fxml;
    exports com.example.poke_appv1;
}