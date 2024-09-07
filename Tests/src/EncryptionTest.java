package src;

import crypto.Encryption;
import messages.Message;

import static org.junit.Assert.*;

public class EncryptionTest {

    @org.junit.Test
    public void encryptDecryptStringTest()
    {
        Encryption encryption = new Encryption();
        String s = "test";
        String result = encryption.decrypt(encryption.encrypt(s));

        assertEquals(s, result);
    }

    @org.junit.Test
    public void encryptDecryptMessageTest()
    {
        Encryption encryption = new Encryption();
        Message m = new Message("Hello World", null);
        Message result = encryption.decrypt(encryption.encrypt(m));

        assertEquals(m.getString(), result.getString());
    }
}
