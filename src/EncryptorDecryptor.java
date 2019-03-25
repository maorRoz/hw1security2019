public interface EncryptorDecryptor {
    void loadKeys(String pathToKeys);

    void loadInputFileToEncryptDecrypt(String pathToInputFile);

    void writeOutputFile(String pathToOutputFile);

}
