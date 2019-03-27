public interface Breaker {

   void loadMessage(String pathToMessage);

   void loadCipher(String pathToCipher);

    byte[][] findKeys();

    void writeOutputFile(String pathToOutputFile);
}
