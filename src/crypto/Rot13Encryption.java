package crypto;

import messages.Message;

public class Rot13Encryption extends EncryptionDecorator{
    public Rot13Encryption(IEncryptionRoutine routine) {
        super(routine);
    }

    @Override
    public Message encrypt(Message m) {
        String res = rot13(m.getString());
        return super.encrypt(new Message(res, m.getSocket()));
    }

    @Override
    public Message decrypt(Message m) {
        Message m2 = super.decrypt(m);
        return new Message(rot13(m2.getString()), m.getSocket());
    }

    //    Source: https://introcs.cs.princeton.edu/java/31datatype/Rot13.java.html
    public String rot13(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            result.append(c);
        }
        return result.toString();
    }
}
