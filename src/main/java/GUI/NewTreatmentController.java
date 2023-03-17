package GUI;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import TableEntity.Doctor;
import TableEntity.Patient;
import TableEntity.TreatmentSet;
import TableEntity.TreatmentType;
import TableEntity.TreatmentWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.stage.Stage;

public class NewTreatmentController implements Initializable {
    MainModel mainModel;
    @FXML 
    public Label labelName;
    @FXML
    public Label labelDate;
    @FXML
    public Button btnSave;
    @FXML
    public Button closeBtn;
    @FXML
    public DatePicker dateStart;
    @FXML
    public DatePicker dateEnd;
    @FXML
    public ChoiceBox choiceName;
    @FXML 
    public ChoiceBox choiceDoctor;
    @FXML
    public ChoiceBox room;
    @FXML
    public TextField cntTreatment;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Patient patient : mainModel.patientList) {
            if (patient.getIntPatientId() == mainModel.ptId) {
                labelName.setText(patient.getTxtFIO());
                labelDate.setText(patient.getDateBirthday());
                break;
            }
        }

        List<String> strTreatmentType = new ArrayList<>();
        mainModel.treatmentTypeList.forEach(item -> {
            strTreatmentType.add(item.getTxtTreatmentTypeName());
        });
        
        List<String> strDoctorName = new ArrayList<>();
        mainModel.doctorList.forEach(item -> {
            strDoctorName.add(item.getTxtDoctorName());
        });

        List<String> tmp = new ArrayList<String>();
        mainModel.roomList.forEach(item -> {
            tmp.add(item);
        });
        List<String> strRoomList = new ArrayList<String>(tmp.subList(0, Math.min(tmp.size(), 10))); 

        choiceName.setItems(FXCollections.observableArrayList(strTreatmentType));
        choiceDoctor.setItems(FXCollections.observableArrayList(strDoctorName));
        room.setItems(FXCollections.observableArrayList(strRoomList));
    }

    public void openError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(error);
        alert.showAndWait();
    }
    public void onClickClose(ActionEvent actionEvent) {
        ((Stage)((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onClickSave() {
        int indexTreatmentName = choiceName.getSelectionModel().getSelectedIndex();
        TreatmentType treatmentType = mainModel.treatmentTypeList.get(indexTreatmentName);
        int indexDoctorName = choiceDoctor.getSelectionModel().getSelectedIndex();
        Doctor doctor = mainModel.doctorList.get(indexDoctorName);
        String strRoom = (String) room.getSelectionModel().getSelectedItem();
        int intCntTreatment = Integer.parseInt(cntTreatment.getText());
        LocalDate datDateBegin = dateStart.getValue();
        LocalDate datDateEnd = dateEnd.getValue();
        String strDateBegin = datDateBegin.toString();
        String strDateEnd = datDateEnd.toString();
        Date datBegin = null;
        Date datEnd = null;
        try {
            datBegin = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(strDateBegin); 
            datEnd = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(strDateEnd);
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
        
        System.out.println(doctor.getTxtDoctorName() + " " + strDateBegin + " -> " + strDateEnd);
        // if (datDateBegin.isAfter(datDateEnd)) {
        //     openError("Дата начала должна быть раньше конца!");
        // }

        TreatmentSet treatmentSet = new TreatmentSet(doctor.getIntDoctorId(),
            mainModel.ptId,
            datBegin,
            datEnd,
            strRoom,
            intCntTreatment,
            0,
            treatmentType.getIntTreatmentTypeId()
            );
        TreatmentWrapper treatmentWrapper = new TreatmentWrapper(treatmentSet, treatmentType, doctor);
        mainModel.addTreatmentWrapper(treatmentWrapper);
    }

}
