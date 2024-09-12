package Server;

import java.util.ArrayList;
import java.util.List;
import messages.Message;

public class PreprocessorChain {
    private final List<Preprocessor> preprocessors = new ArrayList<>();
    private int index = 0;

    public PreprocessorChain addPreprocessor(Preprocessor preprocessor) {
        preprocessors.add(preprocessor);
        return this;
    }

    public void process(Message message) {
        if (index < preprocessors.size()) {
            Preprocessor preprocessor = preprocessors.get(index++);
            preprocessor.process(message, this);
        }
    }
}
