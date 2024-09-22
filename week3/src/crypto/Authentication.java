package crypto;

public class Authentication {
    static String password = "0000";

    public static boolean checkAuthenticationToken(String token) {
//    	#if Authentication
//@        return token.equals(password);
//      #else
        return true;
//      #endif
    }

    public static String getAuthenticationToken() {
        return password;
    }
}
