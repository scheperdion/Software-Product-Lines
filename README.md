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