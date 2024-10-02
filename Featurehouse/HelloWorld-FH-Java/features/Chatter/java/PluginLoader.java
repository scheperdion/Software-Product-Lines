import interfaces.IMessageProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PluginLoader extends ClassLoader{

    public Object loadPlugin(String fileName) {

        try {
            byte[] classData = Files.readAllBytes(Paths.get(fileName));
            Class<?> cls = defineClass(null, classData, 0, classData.length);
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public IMessageProcessor loadMessageProcessor(String fileName) {
        Object o = loadPlugin(fileName);
        if(o instanceof IMessageProcessor) {
            return (IMessageProcessor) o;
        }
        throw new RuntimeException("Could not load message processor");
    }
}
