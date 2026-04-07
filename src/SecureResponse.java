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
            String informasi = kembalian.toString();
            this.data.maskSensitiveData("0934838529");
            this.data = null;
            return informasi;
        } else {
            return "Data Info: Tidak ada data";
        }
    }

    public String getWarningMessage() {
        return this.warningMessage;
    }
}
