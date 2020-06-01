# OthelloAI
OthelloAI is a Human VS AI board game that allows the user to play against the AI on different difficulty levels.

![readme_photo1](https://i.ibb.co/61rCxNL/initial.png)
![readme_photo2](https://i.ibb.co/sPg1DHj/4.png)
## Table of Contents
* [General info](#general-info)
* [Key features](#key-features)
* [Technologies & Tools](#technologies--tools)
* [Setup & Run](#setup--run)
* [License](#license)

## General info
MiniMax algorithm is used as a decision rule along with Alpha-Beta pruning to trim the tree for performance purposes.
The AI evaluates the best move to make depending on the stage of the game by utilizing
the heuristics implemented.

## Key features
### Alpha-Beta pruning
Alpha–beta pruning is a search algorithm that seeks to decrease the number of nodes that are evaluated by the minimax algorithm in its search tree.
It stops evaluating a move when at least one possibility has been found that proves the move to be worse than a previously examined move.

### Game stages
The game is divided into 3 stages, early-game, mid-game, late-game. Depending on which stage of the game we are, different weight is applied to each heuristic method.

### Heuristic methods
* **corners()**

Checks how many corners each player has captured. Every corner makes for a stable cell (and to be precise it's the most simple *stable piece* possibility), which means that the enemy cannot capture it. It's needless to say that the 4 corners are vital in order to establish dominance on the board, because they also help the holder to obtain more *stable pieces* on the board.

*The method has a lot of weight throughout the whole game.*

* **stability()**

stability() extends corner()'s functionality. After it detects that a corner has been capture, it checks the border to find if they are neighbouring pieces. If so, these are also stable pieces.

For instance, if we have captured cell [0][0], then the cells [0][1]...[0][7] are potential stable pieces.
If I hold [0][0] and [0][1], then the last one is not in danger to be flanked.

*The method has a lot of weight throughout the whole game.*

* **mobility()**

After every move, it counts the number of eligible possible moves the player can make with an ulterior motive to increase our mobility at the cost of the enemy's. Essentially, in this way we check how many eligible moves we have left.

*The method has significant weight during early-game and almost no impact during the late-game.*

* **discDifference()**

Finds the difference between black and white pieces.

*The method has no weight during the early-game, mediocre during mid-game, yet significant during late-game.*

* **squareWeights()**

It gives some weight to each cell, in order to avoid the possibility of giving an important cell to the enemy.

*The method has mediocre weight during the early-game, a bit less during mid-game, yet no significance during late-game.*

## Technologies & Tools
* Java 13
* Eclipse IDE for Java Developers

## Setup & Run
To run this project, you just need to simply download it.
Via command line:
```
$ cd  ../where_you_want_it_to_be_saved
$ git clone https://github.com/elenidriva/othelloAI.git
```
Or download manually.

 Import it in a programming environment or (i.e. Eclipse IDE for Enterprise Java Developers) or execute via command line.

## License
The application is under the MIT license.
