public class KeyGenerator {
    private byte[] key;
    private boolean done;

    public KeyGenerator(){
        done = false;
        key = new byte[16];
        for(int i=0; i<key.length; i++){
            key[i] = 0;
        }
    }

    public byte[] getKey(){
        if(done){
            return null;
        }

        done = true;
        byte[] currentKey = key.clone();

        for(int i = key.length - 1; i >= 0; i--){
            if((key[i] & 0xFF) == 255){
                key[i] = 0;
                continue;
            }

            key[i]++;
            done = false;
            break;
        }

        return currentKey;
    }

    public boolean isDone(){
        return done;
    }
}
