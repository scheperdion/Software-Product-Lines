import interfaces.IMessageProcessor;

public class Vigenere implements IMessageProcessor {
    private final String key;

    public Vigenere() {
        this.key = "key"; // TODO: NEVER DO THIS, could be that someone uses this key without knowing...
    }

    @Override
    public String processIncomingMessage(String s) {
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

    @Override
    public String processOutgoingMessage(String s) {
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
}
