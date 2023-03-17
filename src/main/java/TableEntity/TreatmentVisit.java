package TableEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TreatmentVisit {
    private int intTreatmentVisitId;
    private int intTreatmentSetId;
    private Date datTreatmentVisitDate;

    public int getIntTreatmentSetId() {
        return intTreatmentSetId;
    }

    public void setIntTreatmentSetId(int intTreatmentSetId) {
        this.intTreatmentSetId = intTreatmentSetId;
    }

    public Date getDatTreatmentVisitDate() {
        return datTreatmentVisitDate;
    }

    public void setDatTreatmentVisitDate(Date datTreatmentVisitDate) {
        this.datTreatmentVisitDate = datTreatmentVisitDate;
    }

    public int getIntTreatmentVisitId() {
        return intTreatmentVisitId;
    }

    public void setIntTreatmentVisitId(int intTreatmentVisitId) {
        this.intTreatmentVisitId = intTreatmentVisitId;
    }

    public TreatmentVisit() {

    }
    public TreatmentVisit(Map<String, Object> map) {
        this.setIntTreatmentVisitId((Integer) map.get("intTreatmentVisitId"));
        this.setIntTreatmentSetId((Integer) map.get("intTreatmentSetId"));
        this.setDatTreatmentVisitDate((Date) map.get("datTreatmentVisitDate"));
    }
    public TreatmentVisit(int intTreatmentSetId, Date datTreatmentVisitDate) {
        this.setDatTreatmentVisitDate(datTreatmentVisitDate);
        this.setIntTreatmentSetId(intTreatmentSetId);
    }

    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formater.format(this.getDatTreatmentVisitDate());
        return String.format("'%s', '%s'",
                this.getIntTreatmentSetId(),
                strDate);
    }
    public String toString(int withId) {
        if (withId != 1) return this.toString();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formater.format(this.getDatTreatmentVisitDate());
        return String.format("'%s', '%s', '%s'",
                this.getIntTreatmentVisitId(),
                this.getIntTreatmentSetId(),
                strDate);
    }

}
