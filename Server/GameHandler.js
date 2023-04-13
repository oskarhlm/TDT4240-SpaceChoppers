import shortUUID from "short-uuid";
import ServerMessage from './messages/ServerMessage.js'
import { MessageActions } from "./messages/MessageActions.js";

export default class GameHandler {

  constructor(dbHandler) {
    this.dbHandler = dbHandler;
  }

  // create lobby:
  // 1. lager en ID server-side, lager dokument med lobbyID i realtime, lagrer nickname og score i realtime

  // join lobby
  // 1. nickname og ID, lagrer nickname og score under lobbyID i realtime

  // update score
  // 1. player sender score hvert sekund, med lobbyID, lagrer under nickname i realtime

  // player leaves lobby
  // 1. hent ut sscore og nick og last opp i firestore
  // 2. hvis player[] == 0, slett

  createLobby(username) {
    return new Promise((resolve, reject) => {

      // Create UUID
      const lobbyID = shortUUID.generate();

      // Create lobby in DB
      this.dbHandler.writeToDB(lobbyID, username, 0)
      .then(() => {
        // Data saved successfully!
        const response = new ServerMessage(MessageActions.LOBBY_CREATED, "");
        resolve(response);
      })
      .catch((error) => {
        // The write failed...
        const response = new ServerMessage(MessageActions.LOBBY_CREATE_FAILED, "");
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
        const response = new ServerMessage(MessageActions.LOBBY_JOINED, "");
        resolve(response);
      })
      .catch((error) => {
        // The write failed...
        const response = new ServerMessage(MessageActions.LOBBY_JOIN_FAILED, "");
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
          
          //console.log(snapshot)
          const values = snapshot.val();
          //console.log(values);
          const score = values[username];
          // Data saved successfully!
          this.dbHandler.writeToFirestore(username, score);
  
          // Leave lobby in DB
          this.dbHandler.removePlayerFromLobby(lobbyID, username)
            .then(() => {
              // Data saved successfully!
              const response = new ServerMessage(MessageActions.LOBBY_LEFT, "");
              resolve(response);
            })
            .catch((error) => {
              // The write failed...
              const response = new ServerMessage(MessageActions.LOBBY_LEFT_FAILED, "");
              reject(response);
            });
        })
        .catch((error) => {
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
      console.log("Failed to update score");
      console.log(error);
    });
  }
}