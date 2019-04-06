import java.io.FileOutputStream;
import java.io.IOException;

public class AesBreaker implements Breaker {
	private EncryptorDecryptor encryptorDescryptor;
    private byte[] messageByteArray;
    private byte[] cipherByteArray;
    private byte[][] keys;

    public AesBreaker(){
        keys = null;
        messageByteArray = null;
        cipherByteArray = null;
        this.encryptorDescryptor = new AesEncryptorDecryptor(true);
    }
    
    @Override
    public void loadMessage(String pathToMessage) {
        messageByteArray = utils.loadFile(pathToMessage);
    }

    @Override
    public void loadCipher(String pathToCipher) {
        cipherByteArray = utils.loadFile(pathToCipher);
    }

    @Override
    public byte[][] findKeys() {
        if(messageByteArray == null || cipherByteArray == null){
            return null;
        }

        keys = new byte[3][16];
        //choose the two first keys
        for(int i = 0; i < keys[0].length; i++){
            keys[0][i] = 0;
            keys[1][i] = 1;
        }

        //calculate the third key
        this.encryptorDescryptor.setInputArray(messageByteArray);
        keys[2] = cipherByteArray;
        this.encryptorDescryptor.setKeys(keys);
        keys[2] = this.encryptorDescryptor.encryptDecrypt();

        return keys;
    }

    @Override
    public void writeOutputFile(String pathToOutputFile) {
        if(keys == null){
            return;
        }

        try (FileOutputStream stream = new FileOutputStream(pathToOutputFile)) {
            for(byte[] key: keys){
                stream.write(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
