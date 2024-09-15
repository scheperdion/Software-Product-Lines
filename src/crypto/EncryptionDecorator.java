package crypto;

import messages.Message;

public abstract class EncryptionDecorator implements IEncryptionRoutine {
    private IEncryptionRoutine _delegate;

    public EncryptionDecorator(IEncryptionRoutine r) {
        this._delegate = r;
    }

    @Override
    public Message encrypt(Message m) {
        return _delegate.encrypt(m);
    }

    @Override
    public Message decrypt(Message m) {
        return _delegate.decrypt(m);
    }
}
