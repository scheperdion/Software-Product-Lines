# Software-Product-Lines
Software Product Lines Chatter app server and client

# Building & Testing
We use the idea build system for now.
- `Build / Build Artifacts` will build the .jar's so you can run them from command line.
- You can also use the inbuilt buttons to start a server from the `ChatServer` and `ChatClient` classes
- The jars are in the `out/artifacts directory`
- `java -jar Server.jar` should run server, idea downloads the jdks to `~/.jdks/` so you can also run it as `~/.jdks/.../bin/java -jar Server.jar`

# Black box framework

- we wanted to do the 'processMessage'  example from the lecture but for encryption realised that this would not work.
  since the decrypt operation is not the same as the encryption operation. We decided two methods for the message processor,
  one for incoming, and one for outgoing messages. Another thing: vigenere requires a 'key', while rot13 does not
  require a key. Thus for each plugin there is a required extra information before instantiation. To keep with the idea of
  a black box framework we kept the interfaces generic, but the framework code changed quite a bit.

- It was quite difficult to change the code to a black box plugin system because this greatly impacted the architecture of the whole system.
In addition, the ui plugin heavily depends on the color plugin because color is a feature of the ui.