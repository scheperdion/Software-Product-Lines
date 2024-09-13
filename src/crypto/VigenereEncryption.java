package crypto;

import messages.Message;

public class VigenereEncryption extends EncryptionDecorator{
    private String key;

    public VigenereEncryption(IEncryptionRoutine r, String key) {
        super(r);
        this.key = key;
    }

    @Override
    public Message encrypt(Message m) {
        String res = vigenereCipherEncrypt(m.getString(), this.key);
        return super.encrypt(new Message(res, m.getSocket()));
    }

    @Override
    public Message decrypt(Message m) {
        Message m2 = super.decrypt(m);
        return new Message(vigenereCipherDecrypt(m2.getString(), this.key), m.getSocket());
    }

    //    Source: https://raw.githubusercontent.com/mm898/Vigenere-cipher/master/vigCipher.java
    private String vigenereCipherEncrypt(String s, String key) {
        StringBuilder EMessage = new StringBuilder();
        for (int i = 0, j = 0; i < s.length(); i++) {
            char letter = s.charAt(i);

            // Only encrypt alphabetic characters
            if (Character.isLetter(letter)) {
                boolean isUpperCase = Character.isUpperCase(letter);
                letter = Character.toUpperCase(letter);
                char enc = (char) (((letter - 'A') + (Character.toUpperCase(key.charAt(j)) - 'A')) % 26 + 'A');
                EMessage.append(isUpperCase ? enc : Character.toLowerCase(enc));
                j = ++j % key.length();
            } else {
                EMessage.append(letter); // Leave non-alphabetic characters unchanged
            }
        }
        return EMessage.toString();
    }

    //    Source: https://raw.githubusercontent.com/mm898/Vigenere-cipher/master/vigCipher.java
    private String vigenereCipherDecrypt(String s, String key) {
        StringBuilder DMessage = new StringBuilder();
        for (int i = 0, j = 0; i < s.length(); i++) {
            char letter = s.charAt(i);

            // Only decrypt alphabetic characters
            if (Character.isLetter(letter)) {
                boolean isUpperCase = Character.isUpperCase(letter);
                letter = Character.toUpperCase(letter);
                char dec = (char) ((letter - Character.toUpperCase(key.charAt(j)) + 26) % 26 + 'A');
                DMessage.append(isUpperCase ? dec : Character.toLowerCase(dec));
                j = ++j % key.length();
            } else {
                DMessage.append(letter); // Leave non-alphabetic characters unchanged
            }
        }
        return DMessage.toString();
    }
}
