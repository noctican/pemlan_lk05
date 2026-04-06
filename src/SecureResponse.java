package src;
import src.interfaces.*;

public class SecureResponse<T extends MedicalRecord & Confidential> {
    private boolean success;
    private T data;
    private String warningMessage;

    public SecureResponse(boolean success, T data, String warningMessage) {
        this.success = success;
        this.data = data;
        this.warningMessage = warningMessage;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void getInfoData() {
        if (this.data != null) {
            System.out.println("=== Data Info ===");
            System.out.println("Patient ID    : " + this.data.getPatientID());
            System.out.println("Security Level: " + this.data.getSecurityLevel());
        } else {
            System.out.println("Data Info: Tidak ada data");
        }
    }

    public String getWarningMessage() {
        return this.warningMessage;
    }
}