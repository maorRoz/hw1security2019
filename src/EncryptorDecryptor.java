public interface EncryptorDecryptor {
    void loadKeys(String pathToKeys);

    void loadInputFileToEncryptDecrypt(String pathToInputFile);

    void encryptDecrypt();

    void writeOutputFile(String pathToOutputFile);

}
