# Software-Product-Lines
Software Product Lines Chatter app server and client

# Building & Testing
We use the idea build system for now.
- `Build / Build Artifacts` will build the .jar's so you can run them from command line.
- You can also use the inbuilt buttons to start a server from the `ChatServer` and `ChatClient` classes
- The jars are in the `out/artifacts directory`
- `java -jar Server.jar` should run server, idea downloads the jdks to `~/.jdks/` so you can also run it as `~/.jdks/.../bin/java -jar Server.jar`

# Task 4: Runtime parameter implementation
```
Explain your design decisions. In particular, explain your decision regarding global
parameters vs. parameter passing.
```
5. We use parameter passing to be able to test the parts of the chat application better.

```
Explain how the feature selection works from the user perspective. Is there a risk of invalid
feature selections and if yes, how do you address it?
```

```
If you feel strongly about not implementing variability for one or several features, explain why.
```
We think that encryption should be implemented by default.

# Task 5: Design pattern implementation
1. Color -> Template Method

   Gives the advantage to have an abstract Message class that can have different processors on the content of the message
   before sending. To be able to add color to the message, but also to automatically translate or insert emojis (possible future features).
   The decorator would fit here as well.

2. Encryption -> Decorator

   The decorator requires that you do not change the interface of the different decorating functions. Encryption in this
   example only has `encrypt` and `decrypt` methods. So is very well suited, plus this gives the unique business selling point that
   you can specify your own encryption routine with keys in a config file.

3. Authentication -> ??


Explain your design decisions. In particular, explain which design pattern(s) you selected
and why.

Explain how the feature selection works from the user perspective.  

4. We use a file for the feature selection inspired by the apache example. The user can then specify the parameters once
and doesn't have to think about them afterwards. (read-only?)