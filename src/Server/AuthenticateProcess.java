package Server;

import crypto.Authentication;
import messages.Message;

public class AuthenticateProcess implements Preprocessor{
    @Override
    public PreprocessResult process(PreprocessResult pr) {
        Message m = pr.message;
        if(m.getSocket().isAuthenticated())
        {
            return pr;
        }

        if(Authentication.checkAuthenticationToken(m.getString())) {
            m.getSocket().authenticate();
            return pr;
        }

        //Authentication failed, stop further processing and flag message so it will not be sent
        pr.stopPreprocessing = true;
        pr.sendMessage = false;

        return pr;
    }
}
