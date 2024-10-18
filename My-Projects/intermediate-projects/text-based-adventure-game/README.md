# text-based-adventure-game

## Description
An interactive story game showcasing inheritance, interfaces, and file I/O for game state management.
### Overview of the Text-Based Adventure Game

This text-based adventure game is designed to provide an interactive experience where players can engage with various characters (NPCs), fight battles, and manage their health and stats. The game incorporates elements of inheritance, interfaces, file I/O for game state management, and an interactive user interface using JavaFX.

#### Core Components

1. **Character Class Hierarchy**
   - **Abstract Class: `Character`**
     - Serves as the base class for all characters in the game (both player and NPCs).
     - Contains common attributes such as `name`, `health`, `strength`, and `defense`.
     - Implements methods for attacking other characters, checking if a character is alive, and displaying stats.
     - Includes an abstract method `useSpecialAbility()`, which must be defined in subclasses.

   - **Player Class: `Player`**
     - Inherits from `Character`.
     - Represents the main character controlled by the player.
     - Contains an attribute for experience and methods for gaining experience and leveling up.

   - **NPC Classes:**
     - **`NPC` (Abstract Class)**
       - Extends the `Character` class and adds an abstract method for interaction.
     - **`RegularNPC`**
       - A basic NPC that does not have special abilities.
       - Implements the interaction method but does not perform any special actions.
     - **`HealerNPC`**
       - A type of NPC that can heal the player when interacted with.
       - Implements the interaction method to restore health to the player.
     - **`SpecialNPC`**
       - An NPC with unique abilities that can attack the player or use special powers.
       - Implements the interaction method to damage the player.

2. **Game Logic**
   - **`Game` Class**
     - Manages the overall game state, including the player and a randomly selected NPC.
     - Contains methods for starting the game, handling interactions, and checking the game state.
     - Provides methods for saving and loading the game state using file I/O.

3. **User Interface**
   - **`GameUI` Class**
     - The main graphical user interface (GUI) using JavaFX.
     - Displays player information, game logs, and buttons for various actions (interacting, attacking, saving, loading).
     - Updates the UI based on player actions and game state changes.
     - Listens for button clicks to trigger game events, such as attacking NPCs or saving the game state.

#### Game Flow

1. **Start the Game**
   - The player is prompted to enter their character name and starts the game.

2. **Interact with NPCs**
   - The player can choose to interact with different types of NPCs:
     - **Regular NPCs** provide basic interactions.
     - **Healer NPCs** can restore player health.
     - **Special NPCs** can attack the player and use special abilities.

3. **Combat System**
   - Players can attack NPCs, and both the player and NPCs have health that decreases when they take damage.
   - If an NPC is defeated, the player can earn experience.
   - If the player's health drops to zero, the game ends.

4. **Saving and Loading Game State**
   - Players can save their current game state to a file.
   - The game can also load a previously saved state, allowing players to continue from where they left off.

5. **User Interface Interactions**
   - The interface displays player stats (health, strength, etc.) and a log of actions.
   - Buttons allow for quick interactions, such as attacking an NPC or saving the game state.

#### Example Scenario

1. The player starts the game and names their character.
2. They encounter a `HealerNPC` and choose to interact, restoring health.
3. Next, they face a `SpecialNPC`, engage in combat, and experience both attacking and taking damage.
4. After defeating the NPC, the player gains experience points, which could contribute to leveling up in future encounters.
5. At any point, the player can save the game to continue later.

### Additional Features

- **Complex Combat Mechanics**: Players can have varied combat strategies based on NPC abilities.
- **Leveling Up**: Players can become stronger over time, gaining better stats and abilities.
- **Multiple NPC Types**: Each NPC type has unique interactions, making each encounter different.
- **Interactive UI**: The use of JavaFX provides a modern and responsive interface.

### Conclusion

This game is designed to be engaging and strategic, encouraging players to think about their interactions and combat strategies. The use of inheritance and interfaces allows for a flexible design that can be easily expanded with more NPC types, abilities, and gameplay features in the future.

## How to Run
1. Visit the GitHub repository at https://github.com/ndlovumandla/My-Projects
2. Navigate to the 'text-based-adventure-game' folder
3. Click on the 'Run' button in the README

## Technologies Used
- Java 11
- Maven
- GitHub Actions for CI/CD

### Learning Outcomes

1. **Object-Oriented Programming (OOP) Principles**:
   - Understand and apply core OOP concepts such as inheritance, encapsulation, polymorphism, and abstraction.
   - Recognize how to create class hierarchies and utilize interfaces effectively in Java.

2. **JavaFX for GUI Development**:
   - Gain experience in creating interactive user interfaces using JavaFX, including layouts, buttons, labels, and text areas.
   - Learn how to handle user input and events to create a responsive application.

3. **Game Design Fundamentals**:
   - Explore basic game mechanics, including combat systems, player stats management, and NPC interactions.
   - Understand how to design engaging game scenarios and narratives to enhance player experience.

4. **File I/O Operations**:
   - Learn how to implement file input and output operations in Java for saving and loading game states.
   - Understand the importance of data persistence in applications and how to manage state across sessions.

5. **Problem Solving and Debugging Skills**:
   - Develop problem-solving skills by troubleshooting issues in game logic, UI design, and class interactions.
   - Gain experience in debugging Java applications and identifying areas for optimization and enhancement.

### Future Improvements

1. **Enhanced Combat Mechanics**:
   - Introduce more complex combat systems with various attack types, critical hits, and status effects (e.g., poison, stun).
   - Implement a turn-based combat system with a strategy element, allowing players to choose actions based on their character’s skills and the enemy’s weaknesses.

2. **More Diverse NPC Abilities**:
   - Expand the range of NPCs with unique abilities, such as buffs (increasing player stats), debuffs (lowering player stats), or environmental effects (changing the battlefield).
   - Add NPC factions with different behaviors, making interactions more dynamic based on player choices.

3. **World Building**:
   - Create a richer game world with multiple locations, quests, and lore to enhance the narrative experience.
   - Implement a map feature that allows players to explore different areas, encounter various NPCs, and discover hidden treasures.

4. **Player Progression System**:
   - Develop a leveling system that allows players to unlock new skills, abilities, or equipment as they gain experience.
   - Introduce a skill tree to customize player builds, encouraging diverse playstyles.

5. **Improved User Interface and Experience**:
   - Enhance the UI design with animations, transitions, and more visual elements to make interactions feel more immersive.
   - Implement sound effects and music to create a more engaging atmosphere.

6. **Multiplayer Features**:
   - Explore the possibility of adding multiplayer functionality, allowing players to compete or cooperate with friends.
   - Implement leaderboards or achievements to motivate players and create a competitive environment.

7. **Game Balancing**:
   - Continuously test and balance gameplay mechanics to ensure a fair challenge for players.
   - Gather feedback from playtesters to identify areas for improvement and make adjustments accordingly.

8. **Artificial Intelligence (AI)**:
   - Develop smarter NPC AI that adapts to player behavior and strategy, making encounters more challenging and engaging.
   - Implement decision-making algorithms for NPCs that create dynamic interactions based on player choices.

9. **Save and Load Improvements**:
   - Enhance the save/load functionality to support multiple save slots and provide players with more control over their game sessions.
   - Store additional game state information (e.g., player inventory, quest status) to enrich the load experience.

### Conclusion

By focusing on these learning outcomes and potential improvements, the text-based adventure game can evolve into a more engaging and educational project, providing players with a richer experience while enhancing the developer's skills in software design and game development.
[![Run on Repl.it](https://repl.it/badge/github/ndlovumandla/My-Projects)](https://repl.it/github/ndlovumandla/My-Projects)
