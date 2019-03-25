public class Main {

    private static EncryptorDecryptor encryptorDescryptor;
    private static Breaker breaker;

    private static void doEncryptDecrypt(ArgumentExtractor argumentExtractor){
        if(argumentExtractor.getPathToKeys().isEmpty() || argumentExtractor.getPathToInputFile().isEmpty()
                || argumentExtractor.getPathToOutputFile().isEmpty()){
            return;
        }

        encryptorDescryptor = new AesEncryptorDecryptor();
        encryptorDescryptor.loadKeys();
        encryptorDescryptor.loadInputFileToEncryptDecrypt();
        encryptorDescryptor.writeOutputFile();

    }

    private static void doBreaking(ArgumentExtractor argumentExtractor){
        breaker = new AesBreaker(argumentExtractor);
    }

    public static void main(String[] args) {
        if(args.length == 0){
            return;
        }

        ArgumentExtractor argumentExtractor = new ArgumentExtractor(args);
        if(argumentExtractor.toEncryptDecrypt()){
            doEncryptDecrypt(argumentExtractor);
        } else {
            doBreaking(argumentExtractor);
        }

    }
}
