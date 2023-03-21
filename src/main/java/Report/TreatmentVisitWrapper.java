package Report;

import java.text.SimpleDateFormat;
import java.util.Date;

import TableEntity.*;

public class TreatmentVisitWrapper {
    Patient pt;
    TreatmentSet st;
    TreatmentVisit vs;
    TreatmentType tp;
    String name;
    String surname;
    String secondName;
    String date;
    String treatmentName;

    public TreatmentVisitWrapper(Patient pt, TreatmentSet set, TreatmentVisit visit, TreatmentType typ) {
        this.name = pt.getTxtPatientName();
        this.surname = pt.getTxtPatientSurname();
        this.secondName = pt.getTxtPatientSecondName();
        Date date = visit.getDatTreatmentVisitDate();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        this.date = formater.format(date);
        this.treatmentName = typ.getTxtTreatmentTypeName();
        this.pt = pt;
        this.st = set;
        this.vs = visit;
        this.tp = typ;
    }
    @Override
    public String toString() {
        return this.pt.toString() + 
            this.st.toString() + 
            this.vs.toString();
    }
}
