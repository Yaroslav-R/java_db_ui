package TableEntity;

import java.math.BigDecimal;
import java.util.Map;

public class TreatmentType {
    private int intTreatmentTypeId;
    private String txtTreatmentTypeName;
    private String txtTreatmentTypeDescription;
    private BigDecimal fltTreatmentPrice;

    public void setIntTreatmentTypeId(int intTreatmentTypeId) {
        this.intTreatmentTypeId = intTreatmentTypeId;
    }

    public int getIntTreatmentTypeId() {
        return intTreatmentTypeId;
    }


    public void setFltTreatmentPrice(BigDecimal fltTreatmentPrice) {
        this.fltTreatmentPrice = fltTreatmentPrice;
    }

    public BigDecimal getFltTreatmentPrice() {
        return fltTreatmentPrice;
    }

    public void setTxtTreatmentTypeDescription(String txtTreatmentTypeDescription) {
        if (txtTreatmentTypeDescription != null && txtTreatmentTypeDescription.length() < 255)
            this.txtTreatmentTypeDescription = txtTreatmentTypeDescription;
        else
            this.txtTreatmentTypeDescription = "";
    }

    public String getTxtTreatmentTypeDescription() {
        return txtTreatmentTypeDescription;
    }

    public void setTxtTreatmentTypeName(String txtTreatmentTypeName) {
        if (txtTreatmentTypeName != null && txtTreatmentTypeName.length() < 100)
            this.txtTreatmentTypeName = txtTreatmentTypeName;
        else
            this.txtTreatmentTypeName = "";
    }

    public String getTxtTreatmentTypeName() {
        return txtTreatmentTypeName;
    }

    public TreatmentType() {

    }
    public TreatmentType(Map<String, Object> map) {
        this.setIntTreatmentTypeId((Integer) map.get("intTreatmentTypeId"));
        this.setTxtTreatmentTypeName((String) map.get("txtTreatmentTypeName"));
        this.setTxtTreatmentTypeDescription((String) map.get(("txtTreatmentTypeDescription")));
        this.setFltTreatmentPrice((BigDecimal) map.get("fltTreatmentPrice"));
    }
    public TreatmentType(String txtTreatmentTypeName,
                         String txtTreatmentTypeDescription,
                         BigDecimal fltTreatmentPrice) {
        this.setFltTreatmentPrice(fltTreatmentPrice);
        this.setTxtTreatmentTypeDescription(txtTreatmentTypeDescription);
        this.setTxtTreatmentTypeName(txtTreatmentTypeName);
    }

    public String toString() {
        return String.format("'%s', '%s', '%s'",
                getTxtTreatmentTypeName(),
                getTxtTreatmentTypeDescription(),
                getFltTreatmentPrice().toString());
    }
    public String toString(int withId) {
        if (withId != 1) return this.toString();

        return String.format("'%s', '%s', '%s', '%s'",
                getIntTreatmentTypeId(),
                getTxtTreatmentTypeName(),
                getTxtTreatmentTypeDescription(),
                getFltTreatmentPrice().toString());
    }
}
