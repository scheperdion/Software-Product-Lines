package Server;

import java.util.ArrayList;
import java.util.List;
import messages.Message;


//    Add variability by inserting various Preprocessors in the PreprocessorChain where the order of execution is the order of insertion
public class PreprocessorChain {
    private final List<Preprocessor> _preprocessors = new ArrayList<>();
    private int _index = 0;


    public PreprocessorChain addPreprocessor(Preprocessor preprocessor) {
        _preprocessors.add(preprocessor);
        return this;
    }

    public void process(Message message) {
        while (_index < _preprocessors.size()) {
            Preprocessor preprocessor = _preprocessors.get(_index++);
            preprocessor.process(message, this);
        }
        _index = 0;
    }
}
