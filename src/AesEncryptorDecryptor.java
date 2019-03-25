public class AesEncryptorDecryptor implements EncryptorDecryptor {
    private String[] keys;
    private byte[] messageToEncryptDecrypt;
    private String pathToOutputFile;
    private boolean toEncrypt;

    public AesEncryptorDecryptor(boolean toEncrypt){
        keys = new String[3];

        this.toEncrypt = toEncrypt;
    }
    @Override
    public void loadKeys(String pathToKeys) {

    }

    @Override
    public void loadInputFileToEncryptDecrypt(String pathToInputFile) {

    }

    @Override
    public void writeOutputFile(String pathToOutputFile) {

    }
}
