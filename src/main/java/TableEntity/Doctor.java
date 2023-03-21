package TableEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Doctor {
    private int intDoctorId;
    private String txtDoctorName;
    private String txtSpecialist;
    private Date datDoctorWork;

    public void setIntDoctorId(int intDoctorId) {
        this.intDoctorId = intDoctorId;
    }

    public int getIntDoctorId() {
        return intDoctorId;
    }

    public String getTxtDoctorName() {
        return this.txtDoctorName;
    }

    public void setTxtDoctorName(String txtDoctorName) {
        if (txtDoctorName != null && txtDoctorName.length() < 150)
            this.txtDoctorName = txtDoctorName;
        else this.txtDoctorName = "";
    }

    public String getTxtSpecialist() {
        return txtSpecialist;
    }

    public void setTxtSpecialist(String txtSpecialist) {
        if (txtSpecialist != null && txtSpecialist.length() < 35)
            this.txtSpecialist = txtSpecialist;
        else
            this.txtSpecialist = "";
    }

    public Date getDatDoctorWork() {
        return datDoctorWork;
    }

    public void setDatDoctorWork(Date datDoctorWork) {
        this.datDoctorWork = datDoctorWork;
    }

    public Doctor() {

    }
    public Doctor(String txtDoctorName,
                  String txtSpecialist,
                  Date datDoctorWork) {
        this.setDatDoctorWork(datDoctorWork);
        this.setTxtDoctorName(txtDoctorName);
        this.setTxtSpecialist(txtSpecialist);
    }
    public Doctor(Map<String, Object> map)  {
        this.setIntDoctorId((int) map.get("intDoctorId"));
        this.setTxtSpecialist((String) map.get("txtSpecialist"));
        this.setTxtDoctorName((String) map.get("txtDoctorName"));
        this.setDatDoctorWork((Date)map.get("datDoctorWork"));
    }
    public Doctor(int intDoctorId,
                  String txtDoctorName,
                  String txtSpecialist,
                  Date datDoctorWork) {
        this.intDoctorId = intDoctorId;
        this.setDatDoctorWork(datDoctorWork);
        this.setTxtDoctorName(txtDoctorName);
        this.setTxtSpecialist(txtSpecialist);
    }

    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formater.format(getDatDoctorWork());

        return String.format("N'%s', N'%s', '%s'",
                getTxtDoctorName(),
                getTxtSpecialist(),
                strDate);
    }
    public String toString(int withId) {
        if (withId != 1) return this.toString();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formater.format(getDatDoctorWork());

        return String.format("'%s', '%s', '%s', '%s'",
                getIntDoctorId(),
                getTxtDoctorName(),
                getTxtSpecialist(),
                strDate);
    }



}
