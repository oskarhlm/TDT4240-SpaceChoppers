import GameHandler from './GameHandler.js'
import DBHandler from './database/DBHandler.js'

const dbHandler = new DBHandler();
const gameHandler = new GameHandler(dbHandler);

//gameHandler.createLobby("mathias");
gameHandler.joinLobby("nm85EoZmhw1MLbFHRyZwQD", "larsipus")

//getScoreFromDB("hjkj54353", "larsipus");