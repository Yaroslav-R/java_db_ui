package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import Connection.DBConnection;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        HelloController controller = new HelloController();
        MainModel mainModel = new MainModel();
        mainModel.connection = new DBConnection();
        mainModel.loadDoctor();
        mainModel.loadPatient();
        mainModel.loadTreatmentType();
        mainModel.loadTreatment();
        mainModel.loadTreatmentVisit();

        controller.mainModel = mainModel;

        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        
        stage.setTitle("DB_lab1");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) throws DocumentException, IOException {
       launch(); 
        // DBConnection connection = new DBConnection();
        // Patient pt = connection.getPatients().get(0);
        // String name = pt.getTxtPatientName();
        
        // System.out.println(GeneratorOfValues.getName());
    }
}