import ServerMessage from './messages/ServerMessage.js'
import { MessageActions } from "./messages/MessageActions.js";

export default class GameHandler {

  constructor(dbHandler) {
    this.dbHandler = dbHandler;
  }

  // create lobby:
  // 1. generer en 4-digit pin code server-side, lager dokument med lobbyID i realtime, lagrer nickname og score i realtime

  // join lobby
  // 1. username og pin code, lagrer nickname og score under lobbyID i realtime

  // update score
  // 1. player sender score hvert sekund, med lobbyID, lagrer under nickname i realtime

  // player leaves lobby
  // 1. hent ut sscore og nick og last opp i firestore
  // 2. hvis player[] == 0, slett

  createLobby(username) {
    return new Promise((resolve, reject) => {

      // Generate 4-digit pin code
      const lobbyID = Math.floor(1000 + Math.random() * 9000).toString();

      // Create lobby in DB
      this.dbHandler.writeToDB(lobbyID, username, 0)
      .then(() => {
        // Data saved successfully!
        const response = new ServerMessage(MessageActions.LOBBY_CREATED, {'lobbyID': lobbyID});
        resolve(response);
      })
      .catch((error) => {
        // The write failed...
        const response = new ServerMessage(MessageActions.LOBBY_CREATE_FAILED, `Failed to create lobby: ${error}`);
        reject(response);
      });
    });
  }

  joinLobby(lobbyID, username) {
    return new Promise((resolve, reject) => {
      
      // Join lobby in DB
      this.dbHandler.writeToDB(lobbyID, username, 0)
      .then(() => {
        // Data saved successfully!
        const response = new ServerMessage(MessageActions.LOBBY_JOINED, "Lobby joined successfully");
        resolve(response);
      })
      .catch((error) => {
        // The write failed...
        const response = new ServerMessage(MessageActions.LOBBY_JOIN_FAILED, `Failed to join lobby: ${error}`);
        reject(response);
      });
    
    });
  }

  leaveLobby(lobbyID, username) {
    return new Promise((resolve, reject) => {
      // Fetch score from DB
      this.dbHandler.getScoreFromDB(lobbyID, username)
        .then((snapshot) => {
          
          if (snapshot.exists()) {
            console.log(snapshot.val());
          } else {
            console.log("No data available");
          }
          
          const values = snapshot.val();
          const score = values[username];
          // Data saved successfully!
          this.dbHandler.writeToFirestore(username, score);
  
          // Leave lobby in DB
          this.dbHandler.removePlayerFromLobby(lobbyID, username)
            .then(() => {
              // Data saved successfully!
              const response = new ServerMessage(MessageActions.LOBBY_LEFT, "Lobby left successfully");
              resolve(response);
            })
            .catch((error) => {
              // The write failed...
              const response = new ServerMessage(MessageActions.LOBBY_LEFT_FAILED, `Failed to leave lobby: ${error}`);
              reject(response);
            });
        })
        .catch((error) => {
          console.log('Leave game error')
          reject(error);
        });
    });
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
    
  startGame(lobbyID, username) {
    return new Promise((resolve, reject) => {
      this.dbHandler.startGame(lobbyID)
      .then(() => {
        // Game started successfully!
        const response = new ServerMessage(MessageActions.GAME_STARTED, "Game started successfully");
        resolve(response);
      })
      .catch((error) => {
        // The start game failed...
        const response = new ServerMessage(MessageActions.GAME_START_FAILED, `Failed to start game: ${error}`);
        reject(response);
      });
    })
  }

  getScores(lobbyID) {
    return new Promise((resolve, reject) => {
      this.dbHandler.getScores(lobbyID)
      .then((scores) => {
        // Scores sent!
        const scoresJson = JSON.stringify(scores);
        const response = new ServerMessage(MessageActions.SCORES_SENT, scoresJson);
        resolve(response);
      })
      .catch((error) => {
        // The start game failed...
        const response = new ServerMessage(MessageActions.SCORES_SENT_FAILED, `Failed to send scores ${error}`);
        reject(response);
      });
    })
  }
}
