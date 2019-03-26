import java.io.FileOutputStream;
import java.io.IOException;

public class AesBreaker implements Breaker {

    private byte[] messageByteArray;
    private byte[] cipherByteArray;
    private byte[][] keys;

    public AesBreaker(){
        keys = new byte[3][16];
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
    public void findKeys() {

    }

    @Override
    public void writeOutputFile(String pathToOutputFile) {
        try (FileOutputStream stream = new FileOutputStream(pathToOutputFile)) {
            for(byte[] key: keys){
                stream.write(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
