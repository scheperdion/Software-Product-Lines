package Server;
import messages.Message;

public interface Preprocessor {
    PreprocessResult process(PreprocessResult m);
}
