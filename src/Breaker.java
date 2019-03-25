public interface Breaker {

   void loadMessage(String pathToMessage);

   void loadCipher(String pathToCipher);

    void writeOutputFile(String pathToOutputFile);
}
