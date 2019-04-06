import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class utils {
	private static byte[] shiftRows(byte[] inputByteArray) {
		byte[] result = (byte[]) inputByteArray.clone();

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

	private static byte[] revertShiftRows(byte [] inputByteArray){
		byte[] result = (byte[]) inputByteArray.clone();

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

	private static byte[] addRoundKeys(byte[] inputByteArray, byte[] key) {
		byte[] result = (byte[]) inputByteArray.clone();


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
		return addRoundKeys(shiftedInputByteArray, key);

	}

	public static byte[] decrypt(byte[] currentInputByteArray, byte[] key){
		byte[] roundedInputByteArray = utils.addRoundKeys(currentInputByteArray, key);
		return revertShiftRows(roundedInputByteArray);
	}

	private static byte[][] replaceRowsWithColumns(byte[][] inputMatrix) {
		byte[][] newMatrix = new byte[inputMatrix.length][inputMatrix[0].length];
		for(int i = 0; i < inputMatrix.length; i++) {
			for(int j = 0; j < inputMatrix[0].length; j++) {
				newMatrix[i][j] = inputMatrix[j][i];
			}
		}
		return newMatrix;
	}

	private static byte[] convertTwoDimantionIntoOne(byte[][] twoDimenMatrix) {
		int i = 0;
		byte[] newMatrix = new byte[twoDimenMatrix.length * twoDimenMatrix[0].length];
		while(i < newMatrix.length) {
			for(int j = 0; j < twoDimenMatrix.length; j++) {
				for(int k = 0; k < twoDimenMatrix[0].length; k++) {
					newMatrix[i] = twoDimenMatrix[j][k];
					i++;
				}
			}
		}
		return newMatrix;
	}

	private static byte[][] convertOneDimentionIntoTwo(byte[] oneDimenMatrix){
		byte[][] twoDimention = new byte[4][4];
		int i = 0;
		while(i < oneDimenMatrix.length) {
			for(int j = 0; j < twoDimention.length; j++) {
				for(int k = 0; k < twoDimention[0].length; k++) {
					twoDimention[j][k] = oneDimenMatrix[i];
					i++;
				}
			}
		}
		return twoDimention;
	}

	public static byte[] transposeMatrix(byte[] inputMatrix) {
		return convertTwoDimantionIntoOne(replaceRowsWithColumns(convertOneDimentionIntoTwo(inputMatrix)));
	}

}
