# Feature Interactions
In our experience with FOP and AOP we found the following feature interactions in our source code.

Our solutions primarily involved static feature interactions because feature dependencies were determined and resolved at compile time based on the selections in the FeatureIDE feature tree. For instance, interactions between selected features required code adjustments to ensure proper dependencies, which we addressed during development. Since these dependencies were configured according to the feature tree without relying on runtime conditions, they represented static feature interactions. We did not encounter dynamic feature interactions, as no features required runtime adjustments or introduced unforeseen behaviors based on user actions or runtime data.

# FeatureHouse project

The relationship between Color and UserInterface was hard to separate. This is a domain interaction that got into our implementation that we couldn't get rid of. The core of the problem is that the text has a color when you introduce the concept of a user interface, so the Color 'feature' is only an extension of this and maybe should have been dependant on a userinterface in the domain.

Because we chose to take the example of having a plugin that processes a message we had unintended side effects when we tried to do the two encryption operations, that have to be done in reverse for incoming messages. This only became apparent since both encryption features worked independently, but combined introduced unintended side effects
that made it impossible to use the product. This is actually an example of the optional feature problem.

# Aspect Oriented project
In this project we actually ran into the same domain problem of the Color and UserInterface. The aspect did an override of the 'printMessage' method, and thus depended on the user interface to begin with.
