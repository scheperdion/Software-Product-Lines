package Server;

import messages.Message;


public class PreprocessResult
{
    public Message message;

    public boolean stopPreprocessing = false;

    public boolean sendMessage = true;

    PreprocessResult(Message m)
    {
        message = m;
    }
}
