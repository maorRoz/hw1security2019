public class ArgumentExtractor {
    private boolean toEncryptFlag = false;
    private boolean toDecryptFlag = false;
    private boolean expectingKeysFile = false;
    private boolean expectingInputFile = false;

    private boolean toBreakFlag = false;
    private boolean expectingMessageFile = false;
    private boolean expectingCipherFile = false;

    private boolean expectingOutputFile = false;

    private String pathToInputFile = "";
    private String pathToKeys = "";

    private String pathToMessageFile = "";
    private String pathToCipherFile = "";

    private String pathToOutputFile = "";


    private void turnOffFlags (){
        expectingKeysFile = false;
        expectingInputFile = false;

        expectingMessageFile = false;
        expectingCipherFile = false;

        expectingOutputFile = false;
    }

    private void processArgumentFlag(char flag){
        turnOffFlags();

        switch(flag){
            case 'e': toEncryptFlag = true; break;
            case 'd': toDecryptFlag = true; break;
            case 'b': toBreakFlag = true; break;
            case 'k': expectingKeysFile = true; break;
            case 'i': expectingInputFile = true; break;
            case 'm': expectingMessageFile = true; break;
            case 'c': expectingCipherFile = true; break;
            case 'o': expectingOutputFile = true;
        }
    }

    private void processArgumentPath(String argument){
        if(expectingKeysFile){
            pathToKeys = argument;
            return;
        }

        if(expectingInputFile){
            pathToInputFile = argument;
            return;
        }

        if(expectingMessageFile){
            pathToMessageFile = argument;
            return;
        }

        if(expectingCipherFile){
            pathToCipherFile = argument;
            return;
        }

        if(expectingOutputFile){
            pathToOutputFile = argument;
        }
    }

    private void processArgument(String argument){
        if(argument.length() == 2 && argument.charAt(0) == '-'){
            processArgumentFlag(argument.charAt(1));
        } else {
            processArgumentPath(argument);
        }
    }

    public ArgumentExtractor(String[] args){
        for(String argument : args ){
            processArgument(argument);
        }
    }

    public boolean toBreak(){
        return toBreakFlag;
    }

    public boolean toDecrypt(){
        return toDecryptFlag;
    }

    public boolean toEncrypt(){
        return toEncryptFlag;
    }

    public boolean toEncryptDecrypt(){
        return toEncrypt() || toDecrypt();
    }

    public String getPathToKeys(){
        return pathToKeys;
    }

    public String getPathToInputFile(){
        return pathToInputFile;
    }

    public String getPathToMessageFile(){
        return pathToMessageFile;
    }

    public String getPathToCipherFile(){
        return pathToCipherFile;
    }

    public String getPathToOutputFile(){
        return pathToOutputFile;
    }
}
