import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class utils {
    public static byte[] shiftRows(byte[] inputByteArray) {
        byte[] result = inputByteArray.clone();

        int numberOfShifts = 0;
        for(int i = 0; i< result.length;i += 4){
            for(int j = 0; j < numberOfShifts; j++) {
                for (int k = i; k < i + 3; k++) {
                    byte temp = result[k];
                    result[k] = result[k + 1];
                    result[k + 1] = temp;
                }
            }
            numberOfShifts++;
        }
        return result;
    }

    public static byte[] revertShiftRows(byte [] inputByteArray){
        byte[] result = inputByteArray.clone();

        int numberOfShifts = 3;
        for(int i = result.length - 1; i > 0 ;i -= 4){
            for(int j = 0; j < numberOfShifts; j++) {
                for (int k = i; k > i - 3; k--) {
                    byte temp = result[k];
                    result[k] = result[k - 1];
                    result[k - 1] = temp;
                }
            }
            numberOfShifts--;
        }
        return result;
    }

    public static byte[] addRoundKeys(byte[] inputByteArray, byte[] key) {
        byte[] result = inputByteArray.clone();


        for(int i = 0; i< result.length; i++){
            result[i] = (byte) (result[i] ^ key[i]);
        }

        return result;
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

    public static byte[] encrypt(byte[] currentInputByteArray, byte[] key){
        byte[] shiftedInputByteArray = utils.shiftRows(currentInputByteArray);
        return utils.addRoundKeys(shiftedInputByteArray, key);

    }


}
