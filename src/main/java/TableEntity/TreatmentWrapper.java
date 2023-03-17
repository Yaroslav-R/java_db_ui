package TableEntity;

import Connection.DBConnection;

public class TreatmentWrapper {
    private TreatmentSet treatmentSet;
    private TreatmentType treatmentType;
    private Doctor doctor;

    private String treatmentTypeName;
    private String dateBegin;
    private String dateEnd;
    private String treatmentCount;
    private String treatmentCountFact;
    private String doctorFIO;

    public TreatmentSet getTreatmentSet() {
        return this.treatmentSet;
    }

    public TreatmentWrapper() {

    }
    public TreatmentWrapper(TreatmentSet treatmentSet,
                            TreatmentType treatmentType,
                            Doctor doctor) 
    {
        this.treatmentType = treatmentType;
        this.treatmentSet = treatmentSet;
        this.doctor = doctor;
        this.setTreatmentTypeName();
        this.setDateBegin();
        this.setDateEnd();
        this.setTreatmentCount();
        this.setTreatmentCountFact();
        this.setDoctorFIO();
    }

    private void setTreatmentTypeName() {
        this.treatmentTypeName = this.treatmentType.getTxtTreatmentTypeName();
    }
    public String getTreatmentTypeName() {
        return this.treatmentTypeName;
    }
    private void setDateBegin() {
        this.dateBegin = this.treatmentSet.getStrDateBegin();
    }
    public String getDateBegin() {
        return this.dateBegin;
    }
    private void setDateEnd() {
        this.dateEnd = this.treatmentSet.getStrDateEnd();
    }
    public String getDateEnd() {
        return this.dateEnd;
    }
    private void setTreatmentCount() {
        this.treatmentCount = String.valueOf(this.treatmentSet.getIntTreatmentSetCount());
    }
    public String getTreatmentCount() {
        return this.treatmentCount;
    }
    private void setTreatmentCountFact() {
        this.treatmentCountFact = String.valueOf(this.treatmentSet.getIntTreatmentSetCountFact());
    }
    public String getTreatmentCountFact() {
        return this.treatmentCountFact;
    }
    private void setDoctorFIO() {
        this.doctorFIO = this.doctor.getTxtDoctorName();
    }
    public String getDoctorFIO() {
        return this.doctorFIO;
    }
    public String toString() {
        return this.treatmentType.toString() + " "
        + this.doctor.toString() + " "
        + this.treatmentSet.toString();
    }
}
