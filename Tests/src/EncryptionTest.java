package src;

import crypto.*;
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

    @org.junit.Test
    public void encryptDecryptDecoratorTest()
    {
        IEncryptionRoutine encryption = new VigenereEncryption(new Rot13Encryption(new EncryptionRoutine()), "key");
        Message m = new Message("Hello World", null);
        Message encrypted1 = encryption.encrypt(m);
        Message result = encryption.decrypt(encrypted1);
        Message result2 = (new Encryption()).encrypt(m);

        assertEquals(encrypted1.getString(), result2.getString());
        assertEquals(encrypted1.getString(), result2.getString());
    }

    @org.junit.Test
    public void encryptDecryptDecoratorVigenereTest()
    {
        IEncryptionRoutine encryption = new VigenereEncryption(
                new VigenereEncryption(new EncryptionRoutine(), "firstkey"),
                "secondkey");
        Message m = new Message("Hello World", null);
        Message encrypted1 = encryption.encrypt(m);
        Message result = encryption.decrypt(encrypted1);

        assertEquals(m.getString(), result.getString());
        assertNotEquals(m.getString(), encrypted1.getString());
    }
}
