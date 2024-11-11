# Analysis of feature models and configurations
##### a)
- we can check if its valid if and only if: the mandatory features are selected, check for each feature that the dependencies are present & check if constraints are met.

- C1 is not valid as neither Quicksort or LinearSearch has been chosen.

- C2 is valid, as all mandatory features are selected, all dependencies and the constraint is met.

##### b)
- Consistent means that there is at least one valid configuration
- We could convert all the features to booleans where true means it's selected and false means it is not. We can then convert this using logical expressions

##### c)
- this model does not contain dead features. 
- Datastructures, Visualisation & Simulation.
- You can set a constraint that they have to find a good config where the feature is TRUE

# Analysis of feature models and configurations
##### a)
- we can check if its valid if and only if: the mandatory features are selected, check for each feature that the dependencies are present & check if constraints are met.

- C1 is not valid as neither Quicksort or LinearSearch has been chosen.

- C2 is valid, as all mandatory features are selected, all dependencies and the constraint is met.

##### b)
- Consistent means that there is at least one valid configuration
- We could convert all the features to booleans where true means it's selected and false means it is not. We can then convert this using logical expressions

##### c)
- this model does not contain dead features. 
- Datastructures, Visualisation & Simulation.
- You can set a constraint that they have to find a good config where the feature is TRUE

# Comparison of feature models
##### a)
- Generalization, Refactoring, Arbitrary edit & Specialization
- Generalization: The set becomes larger
- Refactoring: The set remains the same
- Arbitrary edit: The size of the set can vary a lot.
- Specialization: The set becomes smaller

##### b)
- This is refactoring, because of the constraints, the selection becomes the same, its only more split up.

##### c)
- We can do this by adding an extra optional feature, such as: List

# Analysis of Code
##### a)
- We can use a SAT solver for trying to find unreachable/dead code.
- For unnecessary annotations we can also use a SAT solver by creating a propositional formula where the presence of each annotation requires AND dependencies. If it becomes unsolvable it is an unnecessary annotation

##### b)
- We can use the feature model for finding the constraints required for using the SAT solver.

##### c)
- System.out.println("Structures"); is not reachable, as Array does imply that structures is selected. 