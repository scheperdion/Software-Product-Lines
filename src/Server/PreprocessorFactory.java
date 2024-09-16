package Server;

import config.Config;
import crypto.EncryptionFactory;
import crypto.IEncryptionRoutine;

public class PreprocessorFactory {
    public PreprocessorChain createPreprocessorChain(){
        Config config = Config.getConfig();
        PreprocessorChain result = new PreprocessorChain();

        result.addPreprocessor(new DecryptionProcess((new EncryptionFactory()).createEncryption()));

        String authentication = config.getProperty("AUTHENTICATION_ENABLED");
        if ("TRUE".equalsIgnoreCase(authentication)) {
            result.addPreprocessor(new AuthenticateProcess());
        }


        return result;
    }
}
