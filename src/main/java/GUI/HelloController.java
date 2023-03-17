package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import TableEntity.Patient;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
// import TableEntity.Patient;
// import javafx.fxml.FXML;
// import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
// import Connection.DBConnection;
// import javafx.scene.layout.*;
// import javafx.stage.Stage;
import javafx.stage.Stage;

// import java.util.List;

public class HelloController implements Initializable {
    
    @FXML
    public TableView <Patient> mainTable;
    @FXML
    public TableColumn <Patient, String> columnFIO;
    @FXML
    public TableColumn <Patient, String> columnDateBirthday;
    @FXML
    public TableColumn <Patient, String> columnAddress;
    
    public MainModel mainModel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnFIO.setCellValueFactory(new PropertyValueFactory<Patient, String>("txtFIO"));
        columnDateBirthday.setCellValueFactory(new PropertyValueFactory<Patient,String>("dateBirthday"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<Patient,String>("txtAddress"));

        

        mainModel.addDataChangedListener(data -> {
            mainTable.setItems(FXCollections.observableArrayList(data.patientList));
        });

        mainModel.loadPatient();
        mainModel.loadDoctor();
        mainModel.loadTreatmentType();


        mainTable.setRowFactory( tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Patient rowData = row.getItem();
                    System.out.println(rowData);
                    try {
                        onClickTreatment();    
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    
                }
            });
            return row ;
        });

    }

    public void onClickAddPatient() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        NewPatientController controller = new NewPatientController();
        controller.mainModel = mainModel;
        loader.setController(controller);
        loader.setLocation(getClass().getResource("new-patient.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();
        
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        stage.showAndWait();
    }

    public void onClickTreatment() throws IOException {
        int pt = ((Patient) this.mainTable.getSelectionModel().getSelectedItem()).getIntPatientId();
        System.out.println("load treatment of " + pt);
        FXMLLoader loader = new FXMLLoader();
        // mainModel.loadTreatment(pt);
        TreatmentController controller = new TreatmentController();
        mainModel.ptId = pt;
        controller.mainModel = mainModel;
        loader.setController(controller);
        loader.setLocation(getClass().getResource("treatment.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();
        
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        stage.showAndWait();
    }

}

