package src.interfaces;

public interface Confidential {
    public int getSecurityLevel();
    public void unmaskSensitiveData();
}