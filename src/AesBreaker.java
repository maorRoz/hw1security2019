import java.io.FileOutputStream;
import java.io.IOException;

public class AesBreaker implements Breaker {

    private byte[] messageByteArray;
    private byte[] cipherByteArray;
    private byte[][] keys;

    public AesBreaker(){
        keys = null;
        messageByteArray = null;
        cipherByteArray = null;
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

        byte[] cipherAfterFirstKey = utils.encrypt(messageByteArray, byte[0]);
        byte[] cipherAfterSecondKey = utils.encrypt(cipherAfterFirstKey, byte[1]);

        //k3 = c XOR shiftRows(cipherAfterSecondKey)

        keys[2] = utils.addRoundKeys(cipherByteArray, utils.shiftRows(cipherAfterSecondKey));

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
