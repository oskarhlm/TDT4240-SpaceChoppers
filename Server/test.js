import GameHandler from './GameHandler.js'
import DBHandler from './database/DBHandler.js'

const dbHandler = new DBHandler();
const gameHandler = new GameHandler(dbHandler);

dbHandler.getHighscores();

// dbHandler.writeToFirestore("Arild", 1)
// dbHandler.writeToFirestore("Oskar", 2)
// dbHandler.writeToFirestore("Espen", 3)
// dbHandler.writeToFirestore("Vetle", 4)
// dbHandler.writeToFirestore("Muttern", 5)
// dbHandler.writeToFirestore("Fattern", 6)
// dbHandler.writeToFirestore("Karoline", 7)
// dbHandler.writeToFirestore("Mats Herman", 8)
// dbHandler.writeToFirestore("André", 9)
// dbHandler.writeToFirestore("Shurabi", 10)
// dbHandler.writeToFirestore("Jeppe", 11)
