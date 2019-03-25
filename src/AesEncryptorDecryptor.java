 import java.util.Arrays;

public class AesEncryptorDecryptor implements EncryptorDecryptor {
    private byte[] inputByteArray;
    private byte[][] keys;
    private boolean toEncrypt;
    private byte[] outputMessageByteArray;

    public AesEncryptorDecryptor(boolean toEncrypt){
        keys = new byte[3][16];

        this.toEncrypt = toEncrypt;
    }
    @Override
    public void loadKeys(String pathToKeys) {
        byte[] keysByteArray = utils.loadFile(pathToKeys);
        int i = 0;
        int maxKeyByteSize = 16;
        for(byte[] key : keys){
            key = Arrays.copyOfRange(keysByteArray, i * maxKeyByteSize, (i + 1) * maxKeyByteSize - 1);
            i++;
        }
    }

    @Override
    public void loadInputFileToEncryptDecrypt(String pathToInputFile) {
        inputByteArray = utils.loadFile(pathToInputFile);
    }

    @Override
    public void encryptDecrypt() {

    }

    @Override
    public void writeOutputFile(String pathToOutputFile) {

    }
}
