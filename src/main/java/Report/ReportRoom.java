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

public class ReportRoom {
    private static String FILE = "ReportRoom.pdf";
    
    private static String FONTREGULAR = "src/main/resources/fonts/HelveticaRegular.ttf";
    private static String FONTBOLD = "src/main/resources/fonts/HelveticaBold.ttf";
    private static String FONTITALIC = "src/main/resources/fonts/HelveticaItalic.ttf";
    private static Font fontRegular;
    private static Font fontBold;
    private static Font fontItalic;

    private static void addTableHeader(PdfPTable table) {
        Stream.of("Дата проведения", "Фамилия", "Имя", "Отчество", "Вид процедуры")
          .forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPadding(5);
            header.setPhrase(new Phrase(columnTitle, fontRegular));
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

    private static void addRows(PdfPTable table, List<TreatmentVisitWrapper> list) {
        // table.addCell("row 1, col 1");
        // table.addCell("row 1, col 2");
        // table.addCell("row 1, col 3");
        Phrase phrase = new Phrase();
        list.forEach(item -> {
            // System.out.println("add :" + item.date.toString() + ";" + item.name.toString() 
            // + ";" + item.secondName.toString() + ";" + item.surname.toString()
            // + ";" + item.treatmentName.toString());
            
            table.addCell(newCell(item.date.toString()));
            table.addCell(newCell(item.name.toString()));
            table.addCell(newCell(item.secondName.toString()));
            table.addCell(newCell(item.surname.toString()));
            table.addCell(newCell(item.treatmentName.toString()));
            // table.addCell(null);
        });
    }
    
    
    public static void make(DBConnection connection) throws FileNotFoundException, DocumentException {
        Map<String, List<TreatmentVisitWrapper>> rooms = new HashMap<>();

        List<TreatmentVisit> treatmentVisits = connection.getTreatmentVisit();
        treatmentVisits.forEach(item -> {
            TreatmentSet treatmentSet = new TreatmentSet(connection.ex(
                "select * from tblTreatmentSet where intTreatmentSetId = " + item.getIntTreatmentSetId()).get(0));
            Patient patient = new Patient(connection.ex("select * from tblPatient where intPatientId = " + treatmentSet.getIntPatientId()).get(0)); 
            TreatmentType treatmentType = new TreatmentType(connection.ex("select * from tblTreatmentType where intTreatmentTypeId = " + treatmentSet.getIntTreatmentTypeId()).get(0)); 
            String room = treatmentSet.getTxtTreatmentSetRoom();
            // System.out.println("update room " + room + ": " + treatmentSet.toString() + "; " + patient.toString());
            if (rooms.get(room) == null) rooms.put(room, new ArrayList<>());
            rooms.get(room).add(new TreatmentVisitWrapper(patient, treatmentSet, item, treatmentType));
        });        
        fontRegular = FontFactory.getFont(FONTREGULAR, BaseFont.IDENTITY_H, true);
        fontBold = FontFactory.getFont(FONTBOLD, BaseFont.IDENTITY_H, true);
        fontItalic = FontFactory.getFont(FONTITALIC, BaseFont.IDENTITY_H, true);
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        // Left
        Paragraph paragraph = new Paragraph("This is center aligned textРусский", fontRegular);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        paragraph = new Paragraph("Отчёт по работе кабинетов", fontBold);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        
        document.add( Chunk.NEWLINE );
        
        rooms.forEach( (key, item) -> {
            Paragraph par = new Paragraph("Кабинет " + key, fontBold);  
            try {
                document.add(par);
                document.add(Chunk.NEWLINE);

                PdfPTable table = new PdfPTable(5);
                addTableHeader(table);
                addRows(table, item);
                document.add(table);
                par = new Paragraph("Итого: " + item.size() + " процедур", fontRegular);
                document.add(par);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            

        });


        document.close();
        System.out.println("Done");        
    }
    
}
