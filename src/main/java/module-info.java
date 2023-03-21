module Form {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires java.sql;
    requires javafaker;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires itextpdf;

    exports TableEntity;
    exports Connection;

    exports GUI;
}