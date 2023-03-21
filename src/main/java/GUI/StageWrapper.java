package GUI;

import java.util.ArrayList;
import java.util.List;

import TableEntity.Patient;
import TableEntity.TreatmentVisit;
import TableEntity.TreatmentWrapper;

public class StageWrapper {
    public List<Patient> patientList;
    public List<TreatmentWrapper> treatmentWrapperList;
    public List<TreatmentVisit> treatmentVisits;

    public StageWrapper() {
        patientList = new ArrayList<Patient>();
        treatmentWrapperList = new ArrayList<TreatmentWrapper>();
        treatmentVisits = new ArrayList<>();
    }
}
