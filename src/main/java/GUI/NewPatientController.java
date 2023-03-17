package GUI;

import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import TableEntity.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;


public class NewPatientController implements Initializable{
    @FXML
    public Button saveBtn;
    @FXML
    public TextField surnameTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField secondNameTextField;
    @FXML 
    public DatePicker birthdayDatePicker;
    @FXML
    public TextField addressTextField;
    @FXML
    public Button closeBtn;
    
    public MainModel mainModel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void onClickClose(ActionEvent actionEvent) {
        ((Stage)((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
    public void onClickSave(ActionEvent actionEvent) {
        System.out.println("clickSave");
        this.mainModel.addPatient(getPatient());
        ((Stage)((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
    public Patient getPatient() {
        
        String surname = surnameTextField.getText();
        String name = nameTextField.getText();
        String secondname = secondNameTextField.getText();
        LocalDate birthday = birthdayDatePicker.getValue();
        Date date = null;
        try {
            date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(birthday.toString()); 
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
        
        String address = addressTextField.getText();
        
        return new Patient(surname, name, secondname, date, address);
    }
}
