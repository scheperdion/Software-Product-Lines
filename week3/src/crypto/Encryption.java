package crypto;

import messages.Message;

public class Encryption {

    public Message encrypt(Message m) {
        return new Message(encrypt(m.getString()), m.getSocket());
    }

    public Message decrypt(Message m) {
        return new Message(decrypt(m.getString()), m.getSocket());
    }

    public String encrypt(String s) {
//    	#if Rot13
    	s = rot13(s);
//    	#endif
    	
//    	#if Vigenere
    	s = vigenereCipherEncrypt(s, "key");
//    	#endif
    	
        return s;
    }

    public String decrypt(String s) {
//    	#if Vigenere
    	s = vigenereCipherDecrypt(s,"key");
//    	#endif
    	
//    	#if Rot13
    	s = rot13(s);
//    	#endif
    	
        return s;
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
