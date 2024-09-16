package Server;
import crypto.IEncryptionRoutine;
import messages.Message;

public class EncryptionProcess implements Preprocessor{

    IEncryptionRoutine _encryption;

    EncryptionProcess(IEncryptionRoutine encryption)
    {
        _encryption = encryption;
    }

    @Override
    public PreprocessResult process(PreprocessResult pr) {
        pr.message = _encryption.encrypt(pr.message);
        return pr;
    }
}
