package Report;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Connection.DBConnection;
import TableEntity.*;

public class ReportPatient {
    private static String FILE = "ReportPatient.pdf";
    
    private static String FONTREGULAR = "src/main/resources/fonts/HelveticaRegular.ttf";
    private static String FONTBOLD = "src/main/resources/fonts/HelveticaBold.ttf";
    private static String FONTITALIC = "src/main/resources/fonts/HelveticaItalic.ttf";
    private static Font fontRegular;
    private static Font fontBold;
    private static Font fontItalic;

    private static void addTableHeader(PdfPTable table) {
        Stream.of("Процедура", "Начало", "Окончание", "Назначено", "Проведено", "Осталось", "Кабинет")
          .forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPadding(5);
            Phrase ph = new Phrase(columnTitle, fontRegular);
            
            header.setPhrase(ph);
            table.addCell(header);
        });
    }
    private static PdfPCell newCell(String str) {
        Phrase phrase = new Phrase(str, fontRegular);
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPhrase(phrase);
        return cell;
    }

    private static int addRows(PdfPTable table, PatientInfoWrapper ptInfo) {
        // table.addCell("row 1, col 1");
        // table.addCell("row 1, col 2");
        // table.addCell("row 1, col 3");
        Phrase phrase = new Phrase();
        int CNT = 0;
        int size = ptInfo.sets.size();
        for (int i = 0; i < size; i++) {
            var set = ptInfo.sets.get(i);
            var typ = ptInfo.types.get(i);
            int cnt = ptInfo.countFact.get(i);
            CNT += cnt;
            table.addCell(newCell(typ.getTxtTreatmentTypeName()));
            table.addCell(newCell(set.getStrDateBegin()));
            table.addCell(newCell(set.getStrDateEnd()));
            table.addCell(newCell("" + set.getIntTreatmentSetCount()));
            table.addCell(newCell("" + (set.getIntTreatmentSetCount() - set.getIntTreatmentSetCountFact())));
            table.addCell(newCell("" + set.getIntTreatmentSetCountFact()));
            table.addCell(set.getTxtTreatmentSetRoom());

        }
        return CNT;
    }
    
    
    public static void make(DBConnection connection) throws FileNotFoundException, DocumentException {
        List<PatientInfoWrapper> wrapperList = new ArrayList<>();

        List<Patient> patients = connection.getPatients();
        System.out.println("patients: " + patients.toString());
        patients.forEach(item -> {
            PatientInfoWrapper ptWrapper = new PatientInfoWrapper();
            ptWrapper.pt = item;
            // System.out.println("new pt: " + item.getIntPatientId());
            var query = connection.ex("select * from tblTreatmentSet where intPatientId = " + item.getIntPatientId());

            query.forEach(curSet -> {
                TreatmentSet set = new TreatmentSet(curSet);
                // System.out.println(" new set: " + set.getIntTreatmentSetId());
                TreatmentType type = new TreatmentType(connection.ex("select * from tblTreatmentType where intTreatmentTypeId = " + set.getIntTreatmentTypeId()).get(0));
                var visits = connection.ex("select * from tblTreatmentVisit where intTreatmentSetId = " + set.getIntTreatmentSetId());
                Integer cntFact = visits.size();
                ptWrapper.addSets(set, type, cntFact);
            });
            
            wrapperList.add(ptWrapper);
        }); 

        fontRegular = FontFactory.getFont(FONTREGULAR, BaseFont.IDENTITY_H, true);
        fontBold = FontFactory.getFont(FONTBOLD, BaseFont.IDENTITY_H, true);
        fontItalic = FontFactory.getFont(FONTITALIC, BaseFont.IDENTITY_H, true);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();

        Paragraph paragraph = new Paragraph("Отчёт по пациентам", fontBold);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        wrapperList.forEach( item -> {
            try {
                document.add(Chunk.NEWLINE);
                Paragraph par = new Paragraph("Пациент " + item.pt.getTxtFIO(), fontBold);
                document.add(par);
                par = new Paragraph("Дата рождения: " + item.pt.getDateBirthday(), fontBold);

                document.add(par);
                par = new Paragraph("Адресс: " + item.pt.getTxtAddress(), fontBold);
                
                document.add(Chunk.NEWLINE);
                PdfPTable table = new PdfPTable(7);
                addTableHeader(table);
                int CNT = 0;
                CNT += addRows(table, item);
                document.add(table);
                par = new Paragraph("Всего: " + CNT + " процедур", fontRegular);
                document.add(par);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            

        });


        document.close();
        System.out.println("Done");        
    }
    
}
