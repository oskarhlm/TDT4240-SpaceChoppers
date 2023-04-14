import { WebSocketServer } from 'ws';
import DBHandler from './database/DBHandler.js';
import GameHandler from './GameHandler.js';
import { MessageActions } from './messages/MessageActions.js';

const wss = new WebSocketServer({ port: 6969 });
const dbHandler = new DBHandler
const gameHandler = new GameHandler(dbHandler);

wss.on('connection', function connection(ws) {
  ws.on('message', function incoming(data) {
    const message = JSON.parse(data);
    console.log(message);

    switch (message.action) {
      case MessageActions.CREATE_LOBBY:
        gameHandler
          .createLobby(message.username)
          .then((result) => {
            ws.send(JSON.stringify(result));
          })
          .catch((error) => {
            ws.send(JSON.stringify(error));
          });
        break;
      case MessageActions.JOIN_LOBBY:
        gameHandler
          .joinLobby(message.lobbyID, message.username)
          .then((result) => {
            ws.send(JSON.stringify(result));
          })
          .catch((error) => {
            ws.send(JSON.stringify(error));
          });
        break;
      case MessageActions.LEAVE_LOBBY:
        gameHandler
          .leaveLobby(message.lobbyID, message.username)
          .then((result) => {
            ws.send(JSON.stringify(result));
          })
          .catch((error) => {
            ws.send(JSON.stringify(error));
          });
        break;
      case MessageActions.SEND_SCORE:
        gameHandler.updateScore(message.lobbyID, message.username, message.score);
        break;
      case MessageActions.GET_SCORES:
        gameHandler
          .getScores(message.lobbyID)
          .then((result) => {
            ws.send(JSON.stringify(result));
          })
          .catch((error) => {
            ws.send(JSON.stringify(error));
          });
        break;
      default:
        console.log(`Unknown action: ${message.action}`);
        break;
    }
  });

  ws.send('something');
});
