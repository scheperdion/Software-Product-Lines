package crypto;



public class Authentication {
    static String password = "0000";//"This is a private channel";

    public static boolean checkAuthenticationToken(String token) {
        return token.equals(password);
    }

    public static String getAuthenticationToken() {
        return password;
    }
}
