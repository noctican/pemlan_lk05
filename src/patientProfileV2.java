package src;
import src.interfaces.*;

public class patientProfileV2 implements Confidential, MedicalRecord, RiwayatAlergi, Versioned{
    // Atribut
    private String patientID;
    private String name;
    private String ssn;
    private String diagnosis;
    private String alergiHistory;
    private int securityLevel;
    private int version = 2;
    private boolean masked;

    // Constructor
    public patientProfileV2(String ID, String name, String ssn, String diagnosis, String alergiHistory, int securityLevel){
        this.patientID = ID;
        this.name = name;
        this.ssn = ssn;
        this.diagnosis = diagnosis;
        this.alergiHistory = alergiHistory;
        this.securityLevel = securityLevel;
    }

    public patientProfileV2(String ID, String name, String diagnosis, String alergiHistory, int securityLevel){
        this.patientID = ID;
        this.name = name;
        this.diagnosis = diagnosis;
        this.alergiHistory = alergiHistory;
        this.securityLevel = securityLevel;
    }

    public patientProfileV2(String ID, String name, String alergiHistory, int securityLevel){
        this.patientID = ID;
        this.name = name;
        this.alergiHistory = alergiHistory;
        this.securityLevel = securityLevel;
    }

    // Method
    public void tambahAlergi(String alergi){
        this.alergiHistory = this.alergiHistory + ", " + alergi;
    }

    public void tambahDiagnosis(String diagnosis){
        this.diagnosis = this.diagnosis + ", " + diagnosis;
    }

    @Override
    public void maskSensitiveData(){
        this.masked = true;
    }

    @Override
    public String getPatientID(){
        return this.patientID;
    }

    @Override
    public int getVersion(){
        return this.version;
    }

    @Override
    public String getRiwayatAlergi(){
        return this.masked ? "********" : this.alergiHistory;
    }

    @Override
    public String getDiagnosis(){
        return this.masked ? "********" : this.diagnosis;
    }

    @Override
    public int getSecurityLevel(){
        return this.securityLevel;
    }

    public boolean getMasked(){
        return this.masked;
    }

    @Override
    public String toString() {
        return "PatientProfileV2 {" +
               "\n  patientId      = " + patientID +
               "\n  name           = " + name +
               "\n  ssn            = " + (masked ? "******" : ssn) +
               "\n  diagnosis      = " + getDiagnosis() +
               "\n  allergyHistory = " + getRiwayatAlergi() +
               "\n  version        = " + version +
               "\n  securityLevel  = " + securityLevel +
               "\n}";
    }
}
