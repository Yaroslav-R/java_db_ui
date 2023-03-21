package Report;
import java.util.ArrayList;
import java.util.List;

import TableEntity.*;

public class PatientInfoWrapper {
    public Patient pt;
    public List<TreatmentSet> sets = new ArrayList<>();
    public List<TreatmentType> types = new ArrayList<>();
    public List<Integer> countFact = new ArrayList<>();

    public void addSets(TreatmentSet set, TreatmentType type, Integer fact) {
        sets.add(set);
        types.add(type);
        countFact.add(fact);
    }

}
