# Software-Product-Lines
Software Product Lines Chatter app server and client

# Building & Testing
We use the idea build system for now.
- `Build / Build Artifacts` will build the .jar's so you can run them from command line.
- You can also use the inbuilt buttons to start a server from the `ChatServer` and `ChatClient` classes
- The jars are in the `out/artifacts directory`
- `java -jar Server.jar` should run server, idea downloads the jdks to `~/.jdks/` so you can also run it as `~/.jdks/.../bin/java -jar Server.jar`

# Black box framework
- the current separation of the GUI in the Client module is an example of white-box framework. There is no
  loose coupling since the user interface (plugin) depends on the Client class from the Chatter module. This 
  would need to inverse the Observer pattern relationship between Client and MessageObserver (making the notify a notify String instead of Message).
- the color feature has been successfully made a plugin by using the Singleton pattern. The default functionality
  is to do nothing and set the default color on green. A separate class file can be included to load a different coloring interpretation.
- we wanted to do the 'processMessage'  example from the lecture but for encryption realised that this would not work.
  since the decrypt operation is not the same as the encryption operation. We decided two methods for the message processor,
  one for incoming, and one for outgoing messages. Another thing: vigenere requires a 'key', while rot13 does not
  require a key. Thus for each plugin there is a required extra information before instantiation. To keep with the idea of
  a black box framework we kept the interfaces generic, but the framework code changed quite a bit.