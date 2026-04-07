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

    @Override
    public String toString() {
        T kembalian = this.data;
        if (kembalian != null) {
            this.data = null;
            return kembalian.toString();
        } else {
            return "Data Info: Tidak ada data";
        }
    }

    public String getWarningMessage() {
        return this.warningMessage;
    }
}
