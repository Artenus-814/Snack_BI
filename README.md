# Base Informatique : Projet Snake  

## Aperçu
- Jeu Snake en console avec un mode manuel et deux stratégies IA.
- Plateau en `char[][]` avec murs `*`, tête `@`, corps `o`, fruit `x`.
- Mouvement basé sur une liste chaînée: nouvelle tête ajoutée, queue retirée sur case vide; croissance sur fruit.

- Dépôt public: https://github.com/Artenus-814/Snack_BI

## Affichage du Plateau
Légende: `*` mur, `@` tête, `o` corps, `x` fruit, ` ` vide.

```
**********************************************
*                                            *
*     ooooooo@                               *
*                                            *
*          x                                 *
*                                            *
*                                            *
*                                            *
*                                            *
**********************************************
```


## Prérequis
- Java 8+ installé et disponible dans le PATH.
- Windows PowerShell ou un terminal capable d’afficher des caractères.

## Compilation
- `javac -d bin src\mes_class\*.java`

## Exécution
- Jeu manuel: `java -cp bin mes_class.Games`
- IA:
  - Par défaut, `GameAI.main` lance la stratégie 1: `java -cp bin mes_class.GameAI`
  - Pour tester la stratégie 2, remplace dans `GameAI.java`:
    - `jeu.jouerStrategie1();` par `jeu.jouerStrategie2();` dans `main`

## Contrôles (Jeu manuel)
- `z` haut
- `s` bas
- `q` gauche
- `d` droite
- `t` quitter

## Règles de Mouvement
- Case cible lue avant déplacement:
  - Fruit `x`: avance, score++, ajoute un nouveau fruit, ne retire pas la queue (croissance)
  - Vide ` `: avance et retire la queue (longueur constante)
  - Mur `*` ou corps `o` hors queue: fin de partie
- Exception queue: entrer dans la case de la queue est autorisé sur un pas normal, car la queue est retirée après le déplacement

## Stratégies IA
- Stratégie 1: gauche constante, arrêt immédiat sur mur ou corps (pas d’évitement)
- Stratégie 2: se rapprocher du fruit (vertical d’abord, puis horizontal) et éviter localement un pas dangereux en testant haut, bas, droite, gauche; mêmes règles de collision et croissance
- Vitesse d’animation: `pause(300)` dans `GameAI` (modifiable)

## Scores
- À la fin d’une partie manuelle, le score est enregistré dans `scores.txt` à la racine du projet
- Fichier géré par `ScoreManager`:
  - `ScoreManager.sauvegarderScore(nom, score)`
  - `ScoreManager.afficherScores()`
- `scores.txt` est ignoré par Git
- Affichage console: lancer `java -cp bin mes_class.Games` et la fin de partie affiche les scores via `ScoreManager.afficherScores()`


## Référence Par Classe
- Board
  - Rôle: gère la grille, les murs, le rendu, place serpent et fruits
  - Champs: `rows`, `columns`, `grid`
  - Méthodes:
    - `draw(score)`: affiche le plateau avec effacement
    - `get(x,y)`: lit une case
    - `addFruit()`: place un fruit sur une case libre interne
    - `addSnake(coords)`: nettoie l’intérieur (hors fruits) et dessine corps/tête
- Snake
  - Rôle: modèle de serpent via liste chaînée ordonnée
  - Champs: `LinkedList<Coordinates> coordinates`
  - Méthodes:
    - `getCoordinates()`, `getHead()`, `getTail()`
    - `getHeadX()`, `getHeadY()`
    - `move(dx,dy)`: ajoute une nouvelle tête
    - `removeQueue()`: retire la queue
- Coordinates
  - Rôle: encapsule une position (ligne, colonne)
  - Méthodes: `getRow`, `getCol`, `equals`
- Games
  - Rôle: boucle de jeu manuelle avec saisie clavier
  - Étapes par tick: rendu, lecture touche, pré-vérification, mouvement/collision, score
- GameAI
  - Rôle: stratégies automatiques (stratégie 1 et 2)
- ScoreManager
  - Rôle: sauvegarde et affichage des scores (fichier `scores.txt`)
- TestBoard
  - Rôle: test de rendu statique (dessin serpent + fruit puis `draw`)
- module-info
  - Déclare le module `Snake`

<!-- Section supprimée (doublon avec Compilation/Exécution) -->


## Limitations
- Pas d’interface graphique (Swing/JavaFX); rendu texte uniquement
- IA stratégie 2: évitement local simple, sans recherche de chemin (BFS/A*)
- `clearScreen` utilise des séquences ANSI, leur support dépend du terminal

## Références Code
- `src\mes_class\Board.java:34` `draw(score)`
- `src\mes_class\Board.java:46` `get(x,y)`
- `src\mes_class\Board.java:51` `addFruit()`
- `src\mes_class\Board.java:67` `addSnake(coords)`
- `src\mes_class\Snake.java:39` `move(dx,dy)`
- `src\mes_class\Snake.java:44` `removeQueue()`
- `src\mes_class\Games.java:7` `main` (boucle manuelle)
- `src\mes_class\GameAI.java:9` `GameAI()` (initialisation)
- `src\mes_class\GameAI.java:17` `jouerStrategie1()`
- `src\mes_class\GameAI.java:51` `jouerStrategie2()`
- `src\mes_class\ScoreManager.java:7` `sauvegarderScore(nom, score)`
- `src\mes_class\ScoreManager.java:15` `afficherScores()`
- `src\mes_class\Coordinates.java:9` `getRow()`
- `src\mes_class\Coordinates.java:13` `getCol()`
- `src\mes_class\Coordinates.java:17` `equals(Object)`
- `src\mes_class\TestBoard.java:5` `main` (rendu statique)
- `src\module-info.java:7` `module Snake { }`


## Auteur
- Mouhamed El Moustapha FAYE 
