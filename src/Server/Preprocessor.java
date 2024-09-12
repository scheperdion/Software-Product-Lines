package Server;
import messages.Message;

public interface Preprocessor {
    void process(Message m, PreprocessorChain chain);
}
