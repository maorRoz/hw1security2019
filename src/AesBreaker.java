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
        //@Todo - add handling for each 128 bit/16 byte
        //@Todo - add logic


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
