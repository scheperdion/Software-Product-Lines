package Server;

import java.util.ArrayList;
import java.util.List;
import messages.Message;


//    Add variability by inserting various Preprocessors in the PreprocessorChain where the order of execution is the order of insertion
public class PreprocessorChain {
    private final List<Preprocessor> _preprocessors = new ArrayList<>();
    private int _index = 0;


    public void addPreprocessor(Preprocessor preprocessor) {
        _preprocessors.add(preprocessor);
    }

    public PreprocessResult process(Message message) {
        PreprocessResult result = new PreprocessResult(message);
        _index = 0;

        while (_index < _preprocessors.size() && (!result.stopPreprocessing || !result.sendMessage)) {
            Preprocessor preprocessor = _preprocessors.get(_index++);
            result = preprocessor.process(result);
        }

        return result;
    }
}
