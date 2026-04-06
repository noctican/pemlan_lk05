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

    public T getInfoData() {
        if (this.data != null) {
            return this.data;
        } else {
            System.out.println("Data Info: Tidak ada data");
            return null;
        }
    }

    public String getWarningMessage() {
        return this.warningMessage;
    }
}
