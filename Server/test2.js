import GameHandler from './GameHandler.js'
import DBHandler from './database/DBHandler.js'

const dbHandler = new DBHandler();
const gameHandler = new GameHandler(dbHandler);

//gameHandler.createLobby("mathias");
//gameHandler.updateScore("gzcho8xabmaMoAT5sN27aK", "mathias", 420);

gameHandler
  .leaveLobby("smAGkkjKUwFPA4vxEHRXro", "mathias")
  .then((response) => {
    console.log(response);
  })
  .catch((error) => {
    console.error('Error:', error);
  });


//getScoreFromDB("hjkj54353", "larsipus");