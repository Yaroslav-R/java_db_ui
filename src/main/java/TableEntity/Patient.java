package TableEntity;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Patient {
    private int intPatientId;
    private String txtPatientSurname;
    private String txtPatientName;
    private String txtPatientSecondName;
    private Date dateBirthday;
    private String txtAddress;

    private String txtFIO;
    
    public void setTxtFIO() {
        this.txtFIO = this.txtPatientSurname + " " + 
            this.txtPatientName + " " + 
            this.txtPatientSecondName;
    } 
    public String getTxtFIO() {
        return txtFIO;
    }

    public void setIntPatientId(int intPatientId) {
        this.intPatientId = intPatientId;
    }

    public int getIntPatientId() {
        return intPatientId;
    }

    public void setDateBirthday(Date dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public String getDateBirthday() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formater.format(dateBirthday);

        return strDate;

    }

    public void setTxtAddress(String txtAddress) {
        if (txtAddress != null && txtAddress.length() < 255)
            this.txtAddress = txtAddress;
        else
            this.txtAddress = "";
    }

    public String getTxtAddress() {
        return txtAddress;
    }

    public void setTxtPatientName(String txtPatientName) {
        if (txtPatientName != null && txtPatientName.length() < 25)
            this.txtPatientName = txtPatientName;
        else
            this.txtPatientName = "";
        this.setTxtFIO();
    }

    public String getTxtPatientName() {
        return txtPatientName;
    }

    public void setTxtPatientSecondName(String txtPatientSecondName) {
        if (txtPatientSecondName != null && txtPatientSecondName.length() < 30)
            this.txtPatientSecondName = txtPatientSecondName;
        else
            this.txtPatientName = "";
        this.setTxtFIO();
    }

    public String getTxtPatientSecondName() {
        return txtPatientSecondName;
    }

    public void setTxtPatientSurname(String txtPatientSurname) {
        if (txtPatientSurname != null && txtPatientSurname.length() < 30)
            this.txtPatientSurname = txtPatientSurname;
        else
            this.txtPatientName = "";
        this.setTxtFIO();
    }

    public String getTxtPatientSurname() {
        return txtPatientSurname;
    }

    public Patient() {
    }

    public Patient(Map<String, Object> map) {
        this.setIntPatientId((Integer) map.get("intPatientId"));
        this.setTxtPatientSurname((String) map.get("txtPatientSurname"));
        this.setTxtPatientName((String) map.get("txtPatientName"));
        this.setTxtPatientSecondName((String) map.get("txtPatientSecondName"));
        this.setDateBirthday((Date) map.get("datBirthday"));
        this.setTxtAddress((String) map.get("txtAddress"));
    }

    public Patient(String txtPatientSurname,
                   String txtPatientName,
                   String txtPatientSecondName,
                   Date dateBirthday,
                   String txtAddress) {
        this.setTxtPatientName(txtPatientName);
        this.setTxtPatientSurname(txtPatientSurname);
        this.setTxtPatientSecondName(txtPatientSecondName);
        this.setDateBirthday(dateBirthday);
        this.setTxtAddress(txtAddress);
    }

    public Patient(int intPatientId,
                   String txtPatientSurname,
                   String txtPatientName,
                   String txtPatientSecondName,
                   Date dateBirthday,
                   String txtAddress) {
        this.intPatientId = intPatientId;
        this.setTxtPatientName(txtPatientName);
        this.setTxtPatientSurname(txtPatientSurname);
        this.setTxtPatientSecondName(txtPatientSecondName);
        this.setDateBirthday(dateBirthday);
        this.setTxtAddress(txtAddress);
    }

    public String toString() {


        return String.format("N'%s', N'%s', N'%s', '%s', N'%s'",
                    getTxtPatientSurname(),
                    getTxtPatientName(),
                    getTxtPatientSecondName(),
                    getDateBirthday(),
                    getTxtAddress());
        
    }

    public String toString(int withId) {
        if (withId != 1) return this.toString();
        
        return String.format("'%s', '%s', '%s', '%s', '%s', '%s'",
                getIntPatientId(),
                getTxtPatientSurname(),
                getTxtPatientName(),
                getTxtPatientSecondName(),
                getDateBirthday(),
                getTxtAddress());
    }
}
