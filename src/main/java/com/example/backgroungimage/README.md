************** HOW TO RUN THE PROJECT THIS PROJECT **************
 open the folder in terminal
 run command  mvn javafx:run

this command critically requires pomxml. And as per the announcement pomxml is not required this command might fail.
Solution is to run through INTELLIJ PLAY BUTTON
Through lines 74 to 110 we have used 3files HScore.txt,CherryCnt.txt and Score.txt. Sometimes the runtime enviornment does not find these files
due to file path errors. In such a case, please copy the absolute paths of the three and paste them instead of the relative path while reading and writing to these files.

# Stick Hero Game Rules

Welcome to Stick Hero! Before embarking on your heroic journey, make sure to familiarize yourself with the rules to maximize your chances of success.

## Objective

Your goal is to guide the Stick Hero through the obstacles, consisting of pillars and challenges, while collecting cherries to increase your score. Exercise precision and timing to extend the stick and traverse the gaps between pillars.

## Controls

- **Space Bar (A key):** Hold to extend the stick, release to stop.
    - The height of the stick determines the distance between pillars.
- **Up Arrow Key:** Move the hero upwards.
- **Down Arrow Key:** Move the hero downwards.

## Gameplay

1. **Pillars:**
    - Navigate through the pillars by extending the stick at the right moment.
    - The gap between pillars varies, so adjust the stick height accordingly.

2. **Cherries:**
    - Collect cherries to increase your score.
    - Cherries are strategically placed between or near pillars.

3. **Timing:**
    - Precise timing is crucial to successfully cross the gaps.
    - Extend the stick too early or too late, and the Stick Hero may fall.

4. **Obstacles:**
    - Watch out for falling obstacles and other challenges.
    - Colliding with obstacles may result in a decrease in score or the end of the game.

## Scoring

- **Score:**
    - Earn points for successfully crossing pillars.
    - Collect cherries for additional points.
    - The longer the distance between pillars, the higher the score.
-  **Cherries:**
    - Cherries can be used to revive.
    

## Challenges

- **Stick Length:**
    - Managing the stick length is essential. Extend it wisely to conquer varying pillar distances.

- **Timing is Key:**
    - Master the art of timing to ensure successful pillar crossings.

- **Avoid Collisions:**
    - Collisions with pillars or obstacles may lead to a score deduction or game over.

## High Score

- **Beat Your Record:**
    - The game keeps track of your high score.
    - Challenge yourself to surpass your previous best.

## Extra feature
- **Unique placemement of cherries
    - Cherries can be placed at any y coordinate
    - So this makes it challenging
- **Vertical unrestricted movement of stick Hero
    - Hero can move verically to collect cherries;

## Design Patterns Used
- **Singleton
    - to ensure only one instance of the hero
- **Decorators
    - to use files handlers like buffer reader and file reader in sequence to manage data



## Have Fun!

Stick Hero is an exciting adventure that tests your reflexes and decision-making skills. Embrace the challenge, enjoy the journey, and strive for greatness in the Stick Hero world!