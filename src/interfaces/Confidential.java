package src.interfaces;

public interface Confidential {
    public int getSecurityLevel();
    public void unmaskSensitiveData(String pass);
    public void maskSensitiveData(String pass);
}