public interface Breaker {

   void loadMessage(String pathToMessage);

   void loadCipher(String pathToCipher);

   void findKeys();

    void writeOutputFile(String pathToOutputFile);
}
