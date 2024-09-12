import crypto.Authentication;
import messages.Message;


public class AuthenticationServer extends AServer implements Runnable {

    public AuthenticationServer(int port) {
        super(port);
    }

    @Override
    public void preprocess(Message m) {
        if(!m.getSocket().isAuthenticated() && Authentication.checkAuthenticationToken(m.getString())) {
            m.getSocket().authenticate();
        }
    }
}
