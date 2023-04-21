import { MessageActions } from "./MessageActions.js";

export default class ServerMessage {
  constructor(action, payload) {
    if (!Object.values(MessageActions).includes(action)) {
      throw new Error('Invalid message action');
    }

    this.action = action;
    this.payload = payload;
  }

  static createLobbyMessage(lobbyID, success) {
    return {
      action: MessageActions.CREATE_LOBBY,
      lobbyID: lobbyID,
      success: success
    };
  }

  static joinLobbyMessage(success) {
    return {
      action: MessageActions.JOIN_LOBBY,
      success: success
    };
  }

  static receiveScoresMessage(scores) {
    return {
      action: MessageActions.RECEIVE_SCORES,
      scores: scores
    };
  }

  static receiveHighscoresMessage(highscores) {
    return {
      action: MessageActions.RECEIVE_HIGHSCORES,
      highscores: highscores
    };
  }
}
