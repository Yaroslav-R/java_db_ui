package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import TableEntity.TreatmentWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;


public class TreatmentController implements Initializable{
    @FXML
    public TableView mainTable;
    @FXML
    public Button closeBtn;
    @FXML
    public TableColumn<TreatmentWrapper,String> treatmentTypeName;
    @FXML
    public TableColumn<TreatmentWrapper,String> dateBegin;
    @FXML
    public TableColumn<TreatmentWrapper,String> dateEnd;
    @FXML
    public TableColumn<TreatmentWrapper,String> treatmentCount;
    @FXML
    public TableColumn<TreatmentWrapper,String> treatmentCountFact;
    @FXML
    public TableColumn<TreatmentWrapper,String> doctorFIO;
    
    MainModel mainModel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treatmentTypeName.setCellValueFactory(new PropertyValueFactory<TreatmentWrapper, String>("treatmentTypeName"));
        dateBegin.setCellValueFactory(new PropertyValueFactory<TreatmentWrapper, String>("dateBegin"));
        dateEnd.setCellValueFactory(new PropertyValueFactory<TreatmentWrapper, String>("dateEnd"));
        treatmentCount.setCellValueFactory(new PropertyValueFactory<TreatmentWrapper, String>("treatmentCount"));
        treatmentCountFact.setCellValueFactory(new PropertyValueFactory<TreatmentWrapper, String>("treatmentCountFact"));
        doctorFIO.setCellValueFactory(new PropertyValueFactory<TreatmentWrapper, String>("doctorFIO"));
        System.out.println("list of ptid : " + mainModel.ptId);

        mainModel.addDataChangedListener(data -> {
            mainTable.setItems(FXCollections.observableArrayList(data.treatmentWrapperList));
        });
        mainModel.loadRoomList();
        mainModel.loadTreatment();
    }
    public void onClickClose(ActionEvent actionEvent) {
        ((Stage)((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
    
    public void onClickAddTreatment() throws IOException {
        System.out.println("click add treatment");

        FXMLLoader loader = new FXMLLoader();
        NewTreatmentController controller = new NewTreatmentController();
        controller.mainModel = mainModel;
        loader.setController(controller);
        loader.setLocation(getClass().getResource("new-treatment.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();
        
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        stage.showAndWait();

    }
}
