package Server;
import crypto.IEncryptionRoutine;
import messages.Message;

public class DecryptionProcess implements Preprocessor{

    IEncryptionRoutine _encryption;

    DecryptionProcess(IEncryptionRoutine encryption)
    {
        _encryption = encryption;
    }

    @Override
    public PreprocessResult process(PreprocessResult pr) {
        pr.message = _encryption.decrypt(pr.message);
        return pr;
    }
}
