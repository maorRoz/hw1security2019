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
        keys = null;
        inputByteArray = null;
        outputMessageByteArray = null;

        this.toEncrypt = toEncrypt;
    }
    
    public void setInputArray(byte[] input) {
    	inputByteArray = input;
    }
    
    public void setKeys(byte[][] keysInput) {
    	keys = keysInput;
    }
    
    @Override
    public void loadKeys(String pathToKeys) {
        byte[] keysByteArray = utils.loadFile(pathToKeys);

        int maxKeyByteSize = 16;
        keys = new byte[3][16];
        for(int i = 0; i < keys.length; i++){
            keys[i] = Arrays.copyOfRange(keysByteArray, i * maxKeyByteSize, (i + 1) * maxKeyByteSize);
            keys[i] = utils.transposeMatrix(keys[i]);
        }
    }

    @Override
    public void loadInputFileToEncryptDecrypt(String pathToInputFile) {
        inputByteArray = utils.loadFile(pathToInputFile);
    }

    private byte[] startEncryption(){
        outputMessageByteArray = inputByteArray;

        int blockSize = 16;
        for(int i = 0; i < outputMessageByteArray.length; i+=blockSize){
        	int destIndex = ((i + blockSize) > outputMessageByteArray.length) ? outputMessageByteArray.length : (i + blockSize);
            byte[] currentBlockMessageByteArray = Arrays.copyOfRange(outputMessageByteArray, i, destIndex);
            currentBlockMessageByteArray = utils.transposeMatrix(currentBlockMessageByteArray);
            
            for(byte[] key : keys) {
                currentBlockMessageByteArray = utils.encrypt(currentBlockMessageByteArray, key);
            }
            
            currentBlockMessageByteArray = utils.transposeMatrix(currentBlockMessageByteArray);
            for(int j = i, k = 0; k < currentBlockMessageByteArray.length; j++, k++){
                outputMessageByteArray[j] = currentBlockMessageByteArray[k];
            }
        }

        return outputMessageByteArray;
    }

    private byte[] startDecryption(){
        outputMessageByteArray = inputByteArray;
        byte[][] reversedKeys = keys;
        Collections.reverse(Arrays.asList(reversedKeys));

        int blockSize = 16;
        for(int i = 0; i < outputMessageByteArray.length; i+= blockSize){
            byte[] currentBlockMessageByteArray = Arrays.copyOfRange(outputMessageByteArray, i , (i + blockSize));
            currentBlockMessageByteArray = utils.transposeMatrix(currentBlockMessageByteArray);
            for(byte[] key : reversedKeys) {
                currentBlockMessageByteArray = utils.decrypt(currentBlockMessageByteArray, key);
            }
            
            currentBlockMessageByteArray = utils.transposeMatrix(currentBlockMessageByteArray);
            for(int j = i, k = 0; k < currentBlockMessageByteArray.length; j++, k++){
                outputMessageByteArray[j] = currentBlockMessageByteArray[k];
            }
        }

        return outputMessageByteArray;
    }

    @Override
    public byte[] encryptDecrypt() {
        if(inputByteArray == null || keys == null){
            return null;
        }

        if(toEncrypt){
            return startEncryption();
        }

        return startDecryption();
    }

    @Override
    public void writeOutputFile(String pathToOutputFile) {
        if(outputMessageByteArray == null){
            return;
        }

        try (FileOutputStream stream = new FileOutputStream(pathToOutputFile)) {
            stream.write(outputMessageByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
