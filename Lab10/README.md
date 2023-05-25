## Laborator 10

### Am rezolvat:

* [x] Compulsory
* [x] Tema integral!! + video demo!! 
- Jocurile se stocheaza acum intr-o baza de data
- ID-urile jocurilor sunt unice. De exemplu daca s-a jucat un joc cu ID = 5, serverul 
stie ca urmatorul joc care va fi creat va avea ID = 6 `int gameId_vechi = gameDAO.getHighestGameId(); int gameId_nou = gameId_vechi + 1; 
Game game = new Game(gameId_nou,10); games.add(game);` in functia `createGame()` din GameServer
* [ ] Bonus