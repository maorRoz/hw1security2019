public class Main {

    private static EncryptorDecryptor encryptorDescryptor;
    private static Breaker breaker;

    private static void doEncryptDecrypt(ArgumentExtractor argumentExtractor){
        if(argumentExtractor.getPathToKeys().isEmpty() || argumentExtractor.getPathToInputFile().isEmpty()
                || argumentExtractor.getPathToOutputFile().isEmpty()){
            return;
        }

        encryptorDescryptor = new AesEncryptorDecryptor(argumentExtractor.toEncrypt());
        encryptorDescryptor.loadKeys(argumentExtractor.getPathToKeys());
        encryptorDescryptor.loadInputFileToEncryptDecrypt(argumentExtractor.getPathToInputFile());
        encryptorDescryptor.encryptDecrypt();
        encryptorDescryptor.writeOutputFile(argumentExtractor.getPathToOutputFile());
    }

    private static void doBreaking(ArgumentExtractor argumentExtractor){
        if(argumentExtractor.getPathToMessageFile().isEmpty() || argumentExtractor.getPathToCipherFile().isEmpty()
                || argumentExtractor.getPathToOutputFile().isEmpty()){
            return;
        }

        breaker = new AesBreaker();
        breaker.loadMessage(argumentExtractor.getPathToMessageFile());
        breaker.loadCipher(argumentExtractor.getPathToCipherFile());
        breaker.findKeys();
        breaker.writeOutputFile(argumentExtractor.getPathToOutputFile());
    }

    public static void main(String[] args) {
        if(args.length == 0){
            return;
        }

        ArgumentExtractor argumentExtractor = new ArgumentExtractor(args);
        if(argumentExtractor.toEncryptDecrypt()){
            doEncryptDecrypt(argumentExtractor);
        } else if(argumentExtractor.toBreak()){
            doBreaking(argumentExtractor);
        }

    }
}
