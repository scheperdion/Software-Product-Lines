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



# Featurehouse additions
When you want the feature 'Color' it is dependent on which option you choose for the 'UI'. This makes it a chicken/egg problem in our case.
A possible solution would be to add a new abstract field Color to a Message. And let the UI default show coloring. But then you have a UI
which assumes a Color will be specified or sets a default Color... hmm....

Our class ColorMessage has code that is tightly coupled to the UI (the ansi codes for example tie directly into the CLI).

Because we chose for MessageProcessors we have had a problem with implementing the right order of encryption/decryption. Because it is not a
commutative operation this conflicts with the need for example to have the coloring processor last. There is an implicit priority value here.

