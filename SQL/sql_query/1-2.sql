USE master
GO;

IF NOT EXISTS (
   SELECT name
   FROM sys.databases
   WHERE name = N'Treatment'
)
CREATE DATABASE [Treatment]
GO;

USE Treatment

if not exists (select * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'TheSchema' 
                 AND  TABLE_NAME = 'tblPatient'
)
create table tblPatient (
intPatientId int primary key identity(1,1),
txtPatientSurname varchar(30) not null,
txtPatientName varchar(25) not null,
txtPatientSecondName varchar(30) not null,
datBirthday date not null,
txtAddress varchar(100) not null
)
GO;

if not exists (select * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'TheSchema' 
                 AND  TABLE_NAME = 'tblDoctor'
)
create table tblDoctor (
intDoctorId int primary key identity(1,1),
txtDoctorName varchar(150) not null,
txtSpecialist varchar(35) not null,
datDoctorWork date not null
)
GO;

if not exists (select * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'TheSchema' 
                 AND  TABLE_NAME = 'tblTreatmentType'
)
create table tblTreatmentType (
intTreatmentTypeId int primary key identity(1,1),
txtTreatmentTypeName varchar(100) not null,
txtTreatmentTypeDescription varchar(255) not null,
fltTreatmentPrice decimal not null
)
GO;
 
if not exists (select * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'TheSchema' 
                 AND  TABLE_NAME = 'tblTreatmentSet'
)
create table tblTreatmentSet(
intTreatmentSetId int primary key identity(1,1),
intDoctorId int not null,
foreign key (intDoctorId) references tblDoctor (intDoctorId) on update cascade on delete no action,
intPatientId int not null,
foreign key (intPatientId) references tblPatient (intPatientId) on update cascade on delete no action,
datDateBegin date not null,
datDateEnd date not null,
txtTreatmentSetRoom char(5) not null,
intTreatmentSetCount int not null,
intTreatmentSetCountFact int not null,
intTreatmentTypeId int not null,
foreign key (intTreatmentTypeId) references tblTreatmentType (intTreatmentTypeId) on update cascade on delete no action
)
GO;

if not exists (select * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'TheSchema' 
                 AND  TABLE_NAME = 'tblTreatmentVisit'
)
create table tblTreatmentVisit (
intTreatmentVisitId int primary key identity(1,1),
intTreatmentSetId int not null,
foreign key (intTreatmentSetId) references tblTreatmentSet(intTreatmentSetId) on update cascade on delete no action,
datTreatmentVisitDate date not null
)
GO;
