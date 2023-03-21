package Connection;

import GenerateValue.GeneratorOfValues;
import TableEntity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBConnection {
    private Connection connection;
    public boolean isLog = false;
    private Statement statement;
    private String pre;
    public void setPre(String pre) {
        this.pre = pre;
    }

    public DBConnection() {
        this.setPre("use Treatment;");
        Properties p = new Properties();
        try {
            p.setProperty("user", "sa");
            p.setProperty("password", "admin");
            p.setProperty("useUnicode","true");
            p.setProperty("characterEncoding","UTF-8");
            p.setProperty("CharacterSet", "UTF-8");
            String connectionUrl = "jdbc:sqlserver://localhost:1433";
            connection = DriverManager.getConnection(connectionUrl, p);
            statement = connection.createStatement();
        } catch (SQLException e) {
            connection = null;
            System.err.println(e.getMessage());
        }
        
    }
    public List<Map<String, Object>> ex(String query)  {
        if (isLog == true) {
            System.out.println(query);
        }
        try {
            if (!connection.isValid(1)) {
                throw new RuntimeException();
            }
            ResultSet resultSet = statement.executeQuery(pre + query);

            List<Map<String, Object>> result = new ArrayList<>();
            if (resultSet == null) {
                return result;
            }
            while(resultSet.next()) {
                Map<String, Object> resultMap = new HashMap<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); ++i) {
                    resultMap.put(resultSet.getMetaData().getColumnName(i),
                                  resultSet.getObject(i));
                }
                result.add(resultMap);
            }

            return result;
        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return null;
        }
    }
    public void DropTableAll() {
        ex("delete from tblTreatmentVisit");
        ex("delete from tblTreatmentSet");
        ex("delete from tblPatient");
        ex("delete from tblDoctor");
        ex("delete from tblTreatmentType");
    }

    public <T> void InsertValue(String tableName, T value) {
        this.ex(String.format("insert into %s values(%s);",
                tableName,
                value.toString()));
    }
    public <T> void InsertValue(String tableName, List<T> value) {
        for(var current : value) {
            this.InsertValue(tableName, current);
        }
    }

    public List<Doctor> getDoctor() {
        List<Doctor> docs = new ArrayList<>();
        this.ex("select * from tblDoctor").forEach(
                (current) -> docs.add(new Doctor(current))
        );
        return docs;
    }
    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        this.ex("select * from tblPatient").forEach(
                (current) -> patients.add(new Patient(current))
        );
        return patients;
    }
    public List<TreatmentType> getTreatmentType() {
        List<TreatmentType> treatmentTypes = new ArrayList<>();
        this.ex("select * from tblTreatmentType").forEach(
                (current) -> treatmentTypes.add(new TreatmentType(current))
        );

        return treatmentTypes;
    }
    public List<TreatmentSet> getTreatmentSet() {
        List<TreatmentSet> treatmentSets = new ArrayList<>();
        this.ex("select * from tblTreatmentSet").forEach(
                (current) -> treatmentSets.add(new TreatmentSet(current))
        );
        return treatmentSets;
    }
    public List<TreatmentVisit> getTreatmentVisit() {
        List<TreatmentVisit> treatmentVisits = new ArrayList<>();
        this.ex("select * from tblTreatmentVisit").forEach(
                (current) -> treatmentVisits.add(new TreatmentVisit(current))
        );
        return treatmentVisits;
    }

    public void RefreshTable() {

        this.setPre("use Treatment;");

        this.DropTableAll();

        this.InsertValue("tblPatient",
                GeneratorOfValues.getPatient(10));
        this.InsertValue("tblDoctor",
                GeneratorOfValues.getDoctor(10));
        this.InsertValue("tblTreatmentType",
                GeneratorOfValues.getTreatmentType(10));
        this.InsertValue("tblTreatmentSet",
                GeneratorOfValues.getTreatmentSet(30,
                this.getDoctor(),
                this.getPatients(),
                this.getTreatmentType()));
        this.InsertValue("tblTreatmentVisit",
                GeneratorOfValues.getTreatmentVisit(30,
                this.getTreatmentSet()));
    }

}
