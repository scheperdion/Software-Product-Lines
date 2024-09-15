package crypto;

import messages.Message;

public interface IEncryptionRoutine {
    Message encrypt(Message m);

    Message decrypt(Message m);
}
