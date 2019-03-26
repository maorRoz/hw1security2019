import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class utils {
    public static byte[] shiftRows(byte[] inputByteArray) {
        //@Todo - add logic
        return inputByteArray;
    }

    public static byte[] addRoundKeys(byte[] inputByteArray, byte[] key) {
        //@Todo - add logic
        return inputByteArray;
    }

    public static byte[] loadFile(String filePath){
        byte[] buffer = null;

        try(InputStream inputStream = new FileInputStream(filePath)){
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }
}
