package crypto;

import messages.Message;

public class EncryptionRoutine implements IEncryptionRoutine {

    @Override
    public Message encrypt(Message m) {
        return m;
    }

    @Override
    public Message decrypt(Message m) {
        return m;
    }
}
