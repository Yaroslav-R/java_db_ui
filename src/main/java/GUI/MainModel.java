package GUI;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.DocumentException;

import Connection.DBConnection;
import GenerateValue.GeneratorOfValues;
import TableEntity.*;
import Report.*;


public class MainModel {
    int ptId;
    String patientName;
    DBConnection connection;
    public StageWrapper data = new StageWrapper();

    List<Patient> patientList = data.patientList;
    List<TreatmentWrapper> treatmentList = data.treatmentWrapperList;
    List<TreatmentVisit> treatmentVisits = data.treatmentVisits;
    List<Doctor> doctorList = new ArrayList<>();
    List<TreatmentType> treatmentTypeList = new ArrayList<>();


    public interface DataChangedListener {
        void dataChanged(StageWrapper data);
    }

    private ArrayList<DataChangedListener> dataChangedListeners = new ArrayList<>();
    public void addDataChangedListener(DataChangedListener listener) {
        this.dataChangedListeners.add(listener);
    }
 

    public void loadPatient () {
        List<Patient> ptList = connection.getPatients();
        patientList.clear();
        patientList.addAll(ptList);
        emitDataChanged();
    }
    public void loadDoctor() {
        List<Doctor> dcList = connection.getDoctor();
        doctorList.clear();
        doctorList.addAll(dcList);
        emitDataChanged();
    }
    public void loadTreatmentVisit() {
        List<TreatmentVisit> visList = connection.getTreatmentVisit();
        treatmentVisits.addAll(visList);
        emitDataChanged();
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
        treatmentList.clear();
        treatmentList.addAll(trList);
        emitDataChanged();
    }

    public void addPatient(Patient patient) {
        System.out.println("add patient :" + patient.toString());
        connection.InsertValue("tblPatient", patient);
        List<Patient> pt = connection.getPatients();
        patientList.clear();
        patientList.addAll(pt);
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

    public void createReportRoom() throws FileNotFoundException, DocumentException {
        System.out.println("query for report room:");
        connection.isLog = true;
        ReportRoom.make(connection);
        connection.isLog = false;
        try{
            // Runtime.getRuntime().exec("pwd");
            Runtime.getRuntime().exec("powershell .\\ReportRoom.pdf");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void createReportPatient() throws FileNotFoundException, DocumentException {
        System.out.println("query for report patient:");
        connection.isLog = true;
        ReportPatient.make(connection);
        connection.isLog = false;
        try{
            // Runtime.getRuntime().exec("pwd");
            Runtime.getRuntime().exec("powershell .\\ReportPatient.pdf");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void createReportDoctor() throws FileNotFoundException, DocumentException {
        ReportDoctor.make(connection);
    }
    public void loadAll() {
        loadPatient();
        loadDoctor();
        loadTreatmentType();
        loadTreatmentVisit();
        loadTreatment();

    }

    public void DropAll() {
        connection.DropTableAll();
        
        loadAll();

        emitDataChanged();
    }
    public void GenAll() {
        connection.InsertValue("tblPatient", GeneratorOfValues.getPatient(10));
        connection.InsertValue("tblDoctor", GeneratorOfValues.getDoctor(10));
        connection.InsertValue("tblTreatmentType", GeneratorOfValues.getTreatmentType(10));
        connection.InsertValue("tblTreatmentSet", GeneratorOfValues.getTreatmentSet(30, connection.getDoctor(), connection.getPatients(), connection.getTreatmentType()));
        connection.InsertValue("tblTreatmentVisit", GeneratorOfValues.getTreatmentVisit(30, connection.getTreatmentSet()));
        
        loadAll();
        emitDataChanged();

    }

}
