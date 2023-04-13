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
      
      // Create lobby in DB
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
      // Simulate an asynchronous operation, like a network request or database query
    
      // Replace this condition with your own logic for successful lobby joining
      const hasLeftLobby = true;

      if (hasLeftLobby) {
        const response = new ServerMessage(MessageActions.LOBBY_JOINED, "");
        resolve(response);
      } else {
        const response = new ServerMessage(MessageActions.LOBBY_JOIN_FAILED, "");
        reject(response);
      }

    });
  }

  updateScore(username, payload) {
    // No need for promise, dont really care if it doesnt update
    const score = payload.score;
    const lobbyID = payload.lobbyID;
  }
}