package src;
import src.interfaces.*;

public class IntegrationGateway<T extends MedicalRecord & Confidential & Versioned> {
    private T mockDatabaseRecord;

    public IntegrationGateway(T mockDatabaseRecord) {
        this.mockDatabaseRecord = mockDatabaseRecord;
    }

    public SecureResponse<T> fetchData(String ID, int levelClearance) {
        if (this.mockDatabaseRecord != null) {
            if (this.mockDatabaseRecord.getSecurityLevel() >= levelClearance) {
                return new SecureResponse<T>(true, this.mockDatabaseRecord, "Data berhasil diambil");
            } else {
                this.mockDatabaseRecord.maskSensitiveData();
                return new SecureResponse<T>(false, this.mockDatabaseRecord, "Level keamanan tidak cukup");
            }
        } else {
            return new SecureResponse<T>(false, null, "Data tidak ditemukan");
        }
    }
}