package src;
import src.interfaces.*;

public class patientProfileV1 implements Versioned, MedicalRecord, Confidential{
    // Atribut
    private String patientID;
    private String name;
    private String ssn;
    private int version = 1;
    private int securityLevel = 1;
    private boolean masked = true;

    // Constructor
    public patientProfileV1(String ID, String name, String ssn, int securityLevel){
        this.patientID = ID;
        this.name = name;
        this.ssn = ssn;
        this.securityLevel = securityLevel;
    }

    public patientProfileV1(String ID, String name, int securityLevel){
        this.patientID = ID;
        this.name = name;
        this.securityLevel = securityLevel;
    }

    // Method
    @Override
    public void unmaskSensitiveData(String pass){
        if (pass.equals("0934838529")){
            this.masked = false;
        }
    }

    @Override
    public void maskSensitiveData(String pass){
        if (pass.equals("0934838529")){
            this.masked = true;
        }
    };

    @Override
    public int getVersion() {
        return this.masked? -1 : this.version;
    }

    @Override
    public String getPatientID(){
        return this.masked? "*********" : this.patientID;
    }

    @Override
    public int getSecurityLevel(){
        return this.securityLevel;
    }

    public boolean getMasked(){
        return this.masked;
    } 

    @Override
    public String toString(){
        String kembalian = "PatientProfileV1 {" +
                "\n  patientId    = " + patientID +
                "\n  name         = " + name +
                "\n  ssn          = " + (masked ? "******" : ssn) +
                "\n  version      = " + version +
                "\n  securityLevel= " + securityLevel +
                "\n}";

        return kembalian;
    }
}
