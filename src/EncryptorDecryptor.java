public interface EncryptorDecryptor {
    void loadKeys(String pathToKeys);

    void loadInputFileToEncryptDecrypt(String pathToInputFile);

    byte[] encryptDecrypt();

    void writeOutputFile(String pathToOutputFile);
    
    void setInputArray(byte[] input);
    
    void setKeys(byte[][] keysInput);

}
