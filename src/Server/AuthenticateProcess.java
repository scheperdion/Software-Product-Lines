package Server;

import crypto.Authentication;
import messages.Message;

public class AuthenticateProcess implements Preprocessor{
    @Override
    public void process(Message m, PreprocessorChain chain) {
        if(!m.getSocket().isAuthenticated() && Authentication.checkAuthenticationToken(m.getString())) {
            m.getSocket().authenticate();
        }
    }
}
