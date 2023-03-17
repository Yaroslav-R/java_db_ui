package TableEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TreatmentSet {
    private int intTreatmentSetId;
    private int intDoctorId;
    private int intPatientId;
    private Date datDateBegin;
    private Date datDateEnd;
    private String txtTreatmentSetRoom;
    private int intTreatmentSetCount;
    private int intTreatmentSetCountFact;
    private int intTreatmentTypeId;

    public void setDatDateBegin(Date datDateBegin) {
        this.datDateBegin = datDateBegin;
    }

    public Date getDatDateBegin() {
        return datDateBegin;
    }
    public String getStrDateBegin() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        return formater.format(getDatDateBegin());
    }

    public void setDatDateEnd(Date datDateEnd) {
        this.datDateEnd = datDateEnd;
    }

    public Date getDatDateEnd() {
        return datDateEnd;
    }
    public String getStrDateEnd() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        return formater.format(getDatDateEnd());
    }

    public void setIntDoctorId(int intDoctorId) {
        this.intDoctorId = intDoctorId;
    }

    public int getIntDoctorId() {
        return intDoctorId;
    }

    public void setIntPatientId(int intPatientId) {
        this.intPatientId = intPatientId;
    }

    public int getIntPatientId() {
        return intPatientId;
    }

    public void setIntTreatmentSetCount(int intTreatmentSetCount) {
        this.intTreatmentSetCount = intTreatmentSetCount;
    }

    public int getIntTreatmentSetCount() {
        return intTreatmentSetCount;
    }

    public void setIntTreatmentSetCountFact(int intTreatmentSetCountFact) {
        this.intTreatmentSetCountFact = intTreatmentSetCountFact;
    }

    public int getIntTreatmentSetCountFact() {
        return intTreatmentSetCountFact;
    }

    public void setIntTreatmentSetId(int intTreatmentSetId) {
        this.intTreatmentSetId = intTreatmentSetId;
    }

    public int getIntTreatmentSetId() {
        return intTreatmentSetId;
    }

    public void setIntTreatmentTypeId(int intTreatmentTypeId) {
        this.intTreatmentTypeId = intTreatmentTypeId;
    }

    public int getIntTreatmentTypeId() {
        return intTreatmentTypeId;
    }

    public void setTxtTreatmentSetRoom(String txtTreatmentSetRoom) {
        if (txtTreatmentSetRoom.length() <= 5)
            this.txtTreatmentSetRoom = txtTreatmentSetRoom;
        else
            this.txtTreatmentSetRoom = "";
    }

    public String getTxtTreatmentSetRoom() {
        return txtTreatmentSetRoom;
    }

    public TreatmentSet() {

    }
    public TreatmentSet(Map<String, Object> map) {
        this.setIntTreatmentSetId((Integer) map.get("intTreatmentSetId"));
        this.setIntDoctorId((Integer) map.get("intDoctorId"));
        this.setIntPatientId((Integer) map.get("intPatientId"));
        this.setDatDateBegin((Date) map.get("datDateBegin"));
        this.setDatDateEnd((Date) map.get("datDateEnd"));
        this.setTxtTreatmentSetRoom((String) map.get("txtTreatmentSetRoom"));
        this.setIntTreatmentSetCount((Integer) map.get("intTreatmentSetCount"));
        this.setIntTreatmentSetCountFact((Integer) map.get("intTreatmentSetCountFact"));
        this.setIntTreatmentTypeId((Integer) map.get("intTreatmentTypeId"));
    }
    public TreatmentSet(int intDoctorId,
                        int intPatientId,
                        Date datDateBegin,
                        Date datDateEnd,
                        String txtTreatmentSetRoom,
                        int intTreatmentSetCount,
                        int intTreatmentSetCountFact,
                        int intTreatmentTypeId) {
        this.setDatDateBegin(datDateBegin);
        this.setDatDateEnd(datDateEnd);
        this.setIntTreatmentSetCount(intTreatmentSetCount);
        this.setIntTreatmentSetCountFact(intTreatmentSetCountFact);
        this.setIntDoctorId(intDoctorId);
        this.setIntPatientId(intPatientId);
        this.setTxtTreatmentSetRoom(txtTreatmentSetRoom);
        this.setIntTreatmentTypeId(intTreatmentTypeId);
    }

    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String datBegin = formater.format(getDatDateBegin());
        String datEnd = formater.format(getDatDateEnd());
        return String.format("'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s'",
                 this.getIntDoctorId(),
                 this.getIntPatientId(),
                 datBegin,
                 datEnd,
                 this.getTxtTreatmentSetRoom(),
                 this.getIntTreatmentSetCount(),
                 this.getIntTreatmentSetCountFact(),
                 this.getIntTreatmentTypeId()
                 );
     }
}
