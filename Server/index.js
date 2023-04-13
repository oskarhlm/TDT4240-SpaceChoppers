import WebSocket from 'ws';
import GameHandler from './GameHandler'

const ws = new WebSocket('ws://localhost:6969');
const gameHandler = new GameHandler();

ws.on('error', console.error);

ws.on('open', function open() {
    ws.send('something');
});

ws.on('message', function message(data) {
    // Try to decode message data
    const message = ClientMessage.fromJson(data);

    // Create lobby
    if (message.action === MessageActions.CREATE_LOBBY) {

      gameHandler.createLobby({ username: message.username })
      .then((result) => {
        ws.send(result);
      })
      .catch((error) => {
        ws.send(error);
      });

    // Join lobby
    } else if (message.action === MessageActions.JOIN_LOBBY) {

      gameHandler.joinLobby(message.payload.lobbyID, message.username)
      .then((result) => {
        ws.send(result);
      })
      .catch((error) => {
        ws.send(error);
      });

    // Leave lobby
    } else if (message.action === MessageActions.LEAVE_LOBBY) {
      
      gameHandler.leaveLobby(message.username, message.payload.lobbyID)
      .then((result) => {
        ws.send(result);
      })
      .catch((error) => {
        ws.send(error);
      });

    // Receieve highscore
    } else if (message.action === MessageActions.SEND_HIGHSCORE) {
      gameHandler.updateScore(message.username, message.payload)
    }

    console.log(message);
});