import ServerMessage from './messages/ServerMessage.js'

export default class GameHandler {

  constructor(dbHandler) {
    this.dbHandler = dbHandler;
  }

  async createLobby(username) {
    try {
      // Generate 4-digit pin code
      const lobbyID = Math.floor(1000 + Math.random() * 9000).toString();

      // Create lobby in DB
      await this.dbHandler.writeToDB(lobbyID, username, 0);

      // Send success message to client
      const message = ServerMessage.createLobbyMessage(lobbyID, true);
      return message;
    } catch (error) {
      // Send failure message to client
      const message = ServerMessage.createLobbyMessage(null, false);
      return message;
    }
  }

  async joinLobby(lobbyID, username) {
    try {
      // Join lobby in DB
      await this.dbHandler.writeToDB(lobbyID, username, 0);

      // Send success message to client
      const message = ServerMessage.joinLobbyMessage(true);
      return message;
    } catch (error) {
      // Send failure message to client
      const message = ServerMessage.joinLobbyMessage(false);
      return message;
    }
  }

  async getScores(lobbyID) {
    try {
      const scores = await this.dbHandler.getScores(lobbyID);

      // Send scores to client
      const message = ServerMessage.receiveScoresMessage(scores);
      return message;
    } catch (error) {
      // Send failure message to client
      const message = ServerMessage.receiveScoresMessage({});
      return message;
    }
  }

  async getHighscores() {
    try {
      const highscores = await this.dbHandler.getHighscores();

      // Send highscores to client
      const message = ServerMessage.receiveHighscoresMessage(highscores);
      return message;
    } catch (error) {
      // Send failure message to client
      const message = ServerMessage.receiveHighscoresMessage({});
      return message;
    }
  }

  async leaveLobby(lobbyID, username) {
    try {
      const snapshot = await this.dbHandler.getScoreFromDB(lobbyID, username);
      if (snapshot.exists()) {
        const values = snapshot.val();
        const score = values[username];
        await this.dbHandler.writeToFirestore(username, score);
      } else {
        console.log("No data available");
      }
      await this.dbHandler.removePlayerFromLobby(lobbyID, username);
      console.log('Player left lobby');
    } catch (error) {
      console.log(`Failed to leave lobby: ${error}`);
    }
  }

  updateScore(lobbyID, username, score) {
    // No need for promise, dont really care if it doesnt update
    
    // Update score in DB
    this.dbHandler.writeToDB(lobbyID, username, score)
    .then(() => {
      // Data saved successfully!
      console.log("Updated score")
    })
    .catch((error) => {
      // The write failed...
      console.log(`Failed to update score: ${error}`);
    });
  }
}
