use Treatment; 
Select tblTreatmentType.txtTreatmentTypeName, 
        tblTreatmentSet.datDateBegin, 
        tblTreatmentSet.datDateEnd, 
        tblTreatmentSet.intTreatmentSetCount 
    
from tblTreatmentSet join tblTreatmentType on (tblTreatmentType.intTreatmentTypeId = tblTreatmentSet.intTreatmentTypeId) 
where ( 
    ( 
        ( (tblTreatmentSet.datDateBegin >= '2022-01-01') and (tblTreatmentSet.datDateBegin <= '2022-02-01') ) 
        or 
        ( (tblTreatmentSet.datDateBegin >= '2022-05-01') and (tblTreatmentSet.datDateBegin <= '2022-06-01') ) 
    ) 
    and
    ( 
        (tblTreatmentSet.intTreatmentSetCount >= 3) and (tblTreatmentSet.intTreatmentSetCount <= 6) 
    ) 
)
order by tblTreatmentSet.datDateBegin
GO
