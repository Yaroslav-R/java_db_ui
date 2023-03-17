module Form {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafaker;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires itextpdf;

    opens GUI;
    opens TableEntity;
    opens Connection;

    exports GUI;
}