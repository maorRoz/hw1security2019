import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

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

    private byte[] encrypt(byte[] currentInputByteArray, byte[] key){
        byte[] shiftedInputByteArray = utils.shiftRows(currentInputByteArray);
        return utils.addRoundKeys(shiftedInputByteArray, key);

    }

    private void startEncryption(){
        outputMessageByteArray = inputByteArray;
        for(byte[] key : keys) {
            outputMessageByteArray = encrypt(outputMessageByteArray, key);
        }
    }

    private byte[] decrypt(byte[] currentInputByteArray, byte[] key){
        byte[] roundedInputByteArray = utils.addRoundKeys(currentInputByteArray, key);
        return utils.shiftRows(roundedInputByteArray);
    }

    private void startDecryption(){
        outputMessageByteArray = inputByteArray;
        byte[][] reversedKeys = keys;
        Collections.reverse(Arrays.asList(reversedKeys));
        for(byte[] key : keys) {
            outputMessageByteArray = decrypt(outputMessageByteArray, key);
        }
    }

    @Override
    public void encryptDecrypt() {
        if(toEncrypt){
            startEncryption();
        } else {
            startDecryption();
        }
    }

    @Override
    public void writeOutputFile(String pathToOutputFile) {
        try (FileOutputStream stream = new FileOutputStream(pathToOutputFile)) {
            stream.write(outputMessageByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
