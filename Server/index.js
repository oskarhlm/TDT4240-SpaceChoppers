import { WebSocketServer } from 'ws';
import DBHandler from './database/DBHandler.js';
import GameHandler from './GameHandler.js';
import { MessageActions } from './messages/MessageActions.js';

const wss = new WebSocketServer({ port: 6968 });
const dbHandler = new DBHandler
const gameHandler = new GameHandler(dbHandler);

wss.on('connection', function connection(ws) {
  ws.username = null; // Initialize the username property to null
  ws.lobbyID = null; // Initialize the lobbyID property to null
  
  ws.on('message', function incoming(data) {
    const message = JSON.parse(data);
    console.log(message);

    switch (message.action) {
      case MessageActions.CREATE_LOBBY:
        gameHandler
          .createLobby(message.username)
          .then((result) => {
            ws.lobbyID = result.lobbyID;
            ws.username = message.username;
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
            ws.lobbyID = message.lobbyID;
            ws.username = message.username;
            ws.send(JSON.stringify(result));
          })
          .catch((error) => {
            ws.send(JSON.stringify(error));
          });
        break;
      case MessageActions.LEAVE_LOBBY:
        gameHandler
          .leaveLobby(message.lobbyID, message.username)
          .then(() => {
            ws.lobbyID = null;
            ws.username = null;
          })
          .catch((error) => {
            ws.send(JSON.stringify(error));
          });
        break;
      case MessageActions.SEND_SCORE:
        gameHandler.updateScore(message.lobbyID, message.username, message.score);
        gameHandler
          .getScores(message.lobbyID)
          .then((result) => {
            ws.send(JSON.stringify(result));
          })
          .catch((error) => {
            ws.send(JSON.stringify(error));
          });
        break;
      case MessageActions.RECEIVE_SCORES:
        gameHandler
          .getScores(message.lobbyID)
          .then((result) => {
            ws.send(JSON.stringify(result));
          })
          .catch((error) => {
            ws.send(JSON.stringify(error));
          });
        break;
      case MessageActions.GET_HIGHSCORES:
        gameHandler
          .getHighscores()
          .then((result) => {
            ws.send(JSON.stringify(result));
          })
          .catch((error) => {
            ws.send(JSON.stringify(error));
          });
        break;
      default:
        const error = {
          error: `Unknown action: ${message.action}`,
        };
        ws.send(JSON.stringify(error));
        break;
      }
  });

  ws.on('close', function close() {
    if (ws.lobbyID && ws.username) {
        gameHandler.leaveLobby(ws.lobbyID, ws.username)
            .then(() => {
                console.log(`User ${ws.username} left lobby ${ws.lobbyID}`);
            })
            .catch((error) => {
                console.log(`Error leaving lobby: ${error}`);
            });
    }
  });
});

export { wss };
