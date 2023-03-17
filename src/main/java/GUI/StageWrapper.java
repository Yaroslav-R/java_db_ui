package GUI;

import java.util.ArrayList;
import java.util.List;

import TableEntity.Patient;
import TableEntity.TreatmentWrapper;

public class StageWrapper {
    public List<Patient> patientList;
    public List<TreatmentWrapper> treatmentWrapperList;
    public List<String> roomList;

    public StageWrapper() {
        patientList = new ArrayList<Patient>();
        treatmentWrapperList = new ArrayList<TreatmentWrapper>();
        roomList = new ArrayList<String>();
    }
}
