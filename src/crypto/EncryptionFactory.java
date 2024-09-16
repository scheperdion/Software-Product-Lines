package crypto;

import config.Config;

public class EncryptionFactory {
    public IEncryptionRoutine createEncryption() {
        Config config = Config.getConfig();
        String encryptionOrder = config.getProperty("ENCRYPTION_ORDER");
        String key = config.getProperty("KEY");
        IEncryptionRoutine encryptionRoutine = new EncryptionRoutine();

        if (encryptionOrder != null) {
            return encryptionRoutine;
        }

        // Split the encryption algorithms by comma
        String[] algorithms = encryptionOrder.split(",");

        // Reverse order to nest correctly
        for (int i = algorithms.length - 1; i >= 0; i--) {
            String algorithm = algorithms[i].trim().toUpperCase();

            switch (algorithm) {
                case "ROT13":
                    encryptionRoutine = new Rot13Encryption(encryptionRoutine);
                    break;
                case "VIGENERE":
                    if (key != null) {
                        encryptionRoutine = new VigenereEncryption(encryptionRoutine, key);
                    } else {
                        System.err.println("Vigenere encryption requires a key");
                    }
                    break;
                default:
                    System.out.println("Unknown encryption algorithm: " + algorithm);
                    break;
            }
        }

        return encryptionRoutine;
    }
}
