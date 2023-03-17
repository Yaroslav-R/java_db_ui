package GUI;
import java.util.ArrayList;
import java.util.List;

import Connection.DBConnection;
import TableEntity.*;


public class MainModel {
    int ptId;
    DBConnection connection;
    public StageWrapper data = new StageWrapper();

    List<String> roomList = data.roomList;
    List<Patient> patientList = data.patientList;
    List<TreatmentWrapper> treatmentList = data.treatmentWrapperList;

    List<Doctor> doctorList = new ArrayList<>();
    List<TreatmentType> treatmentTypeList = new ArrayList<>();


    public interface DataChangedListener {
        void dataChanged(StageWrapper data);
    }

    private ArrayList<DataChangedListener> dataChangedListeners = new ArrayList<>();
    public void addDataChangedListener(DataChangedListener listener) {
        this.dataChangedListeners.add(listener);
    }
 
    public void loadRoomList() {
        String query = "use Treatment; Select Distinct txtTreatmentSetRoom from tblTreatmentSet;";
        var tmp = connection.ex(query);
        List<String> rmList = new ArrayList<String>();
        tmp.forEach(item -> {
            rmList.add((String) item.get("txtTreatmentSetRoom"));
        });
        
        data.roomList.addAll(rmList);
        emitDataChanged();
    }
    public void loadPatient () {
        List<Patient> ptList = connection.getPatients();
        patientList.addAll(ptList);
        emitDataChanged();
    }
    public void loadDoctor() {
        if (doctorList.size() == 0) {
            doctorList = connection.getDoctor();
        }
    }
    
    public void loadTreatmentType() {
        if (treatmentTypeList.size() == 0) {
            treatmentTypeList = connection.getTreatmentType();
        }
    }

    public void loadTreatment() {
        List<TreatmentWrapper> trList = new ArrayList<>();
        var treatment = connection.ex(
    "select * from tblTreatmentSet where intPatientId = " + ptId);
        treatment.forEach(item -> {
            TreatmentSet treatmentSet = new TreatmentSet(item);
            String queryDoctor = "select * from tblDoctor where intDoctorId = " + 
                                    treatmentSet.getIntDoctorId() ;
            String queryTreatmentType = "select * from tblTreatmentType where intTreatmentTypeId = " + 
                                    treatmentSet.getIntTreatmentTypeId();
            trList.add(new TreatmentWrapper(
                treatmentSet,
                new TreatmentType(connection.ex(queryTreatmentType).get(0)),
                new Doctor(connection.ex(queryDoctor).get(0)))
            );
        });
        treatmentList.addAll(trList);
        emitDataChanged();
    }

    public void addPatient(Patient patient) {
        System.out.println("add patient :" + patient.toString());
        connection.InsertValue("tblPatient", patient);
        patientList = connection.getPatients();
        emitDataChanged();
    }
    // public void addTreatmentSet(TreatmentSet treatmentSet) {
    //     connection.InsertValue("tblTreatmentSet", treatmentSet);
    //     data.treatmentWrapperList.add()
    // }
    public void addTreatmentWrapper(TreatmentWrapper treatmentWrapper) {
        connection.InsertValue("tblTreatmentSet", treatmentWrapper.getTreatmentSet());
        data.treatmentWrapperList.add(treatmentWrapper);
        emitDataChanged();
    }


    private void emitDataChanged() {
        for (DataChangedListener listener: dataChangedListeners) {
            listener.dataChanged(data);
        }
    }
}
