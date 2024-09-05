package crypto;

public class Authentication {
    String password = "This is a private channel";

    public boolean checkAuthenticationToken(String token) {
        return token.equals(password);
    }

    public String getAuthenticationToken() {
        return password;
    }
}
