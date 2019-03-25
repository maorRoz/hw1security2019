import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class utils {
    public static void shiftRows() {

    }

    public static void addRoundKeys() {

    }

    public static byte[] loadFile(String filePath){
        byte[] buffer = null;

        try(InputStream inputStream = new FileInputStream(filePath)){
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);

            for(byte b : buffer){
                System.out.printf("%02x", b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }
}
