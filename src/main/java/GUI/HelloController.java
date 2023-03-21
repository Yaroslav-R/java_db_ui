package GUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;

import TableEntity.Patient;
import javafx.collections.FXCollections;
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
import javafx.collections.FXCollections;

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

    public javafx.collections.ObservableList<Patient> list = FXCollections.observableArrayList();
    
    public MainModel mainModel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnFIO.setCellValueFactory(new PropertyValueFactory<Patient, String>("txtFIO"));
        columnDateBirthday.setCellValueFactory(new PropertyValueFactory<Patient,String>("dateBirthday"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<Patient,String>("txtAddress"));

        mainTable.setItems(list);

        mainModel.addDataChangedListener(data -> {
            // System.out.println("UPDATE DATA");
            // System.out.println(data.patientList);
            list.clear();
            
            // list.forEach(item -> System.out.println(item));
            // System.out.println("add;");
            list.addAll(data.patientList);
            
            // list.forEach(item -> System.out.println(item));
            mainTable.setItems(list);
        });

        mainModel.loadAll();


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
        Patient patient = (Patient) this.mainTable.getSelectionModel().getSelectedItem();
        int pt = patient.getIntPatientId();
        System.out.println("load treatment of " + pt);
        FXMLLoader loader = new FXMLLoader();
        // mainModel.loadTreatment(pt);
        TreatmentController controller = new TreatmentController();
        mainModel.ptId = pt;
        mainModel.patientName = patient.getTxtFIO();
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

    public void onClickDropAll() {
        System.out.println("click on Drop All");
        mainModel.DropAll();
    }
    public void onClickGenAll() {
        System.out.println("click on gen All");
        mainModel.GenAll();
    }

    public void onClickGenReportRoom() throws FileNotFoundException, DocumentException {
        System.out.println("Start generating report room");
        mainModel.createReportRoom();
    }
    public void onClickGenReportPt() throws FileNotFoundException, DocumentException {
        System.out.println("Start generation report of Patients");
        mainModel.createReportPatient();
    }
    public void onClickGenReportDc() {
        System.out.println("Start generation report of Doctors");
        // mainModel.createReportDoctor();
    }

}

