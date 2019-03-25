public interface EncryptorDecryptor {
    void loadKeys();

    void loadInputFileToEncryptDecrypt();

    void writeOutputFile();

}
