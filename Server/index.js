import WebSocket from 'ws';
import GameHandler from './GameHandler'

const ws = new WebSocket('ws://www.host.com/path');

ws.on('error', console.error);

ws.on('open', function open() {
    ws.send('something');
});

ws.on('message', function message(data) {
    // Try to decode message data
    const message = Message.fromJson(data);

    // Create lobby
    if (message.action === MessageActions.CREATE_LOBBY) {
        GameHandler.createLobby()
    // Join lobby
    } else if (message.action === MessageActions.JOIN_LOBBY) {

    // Leave lobby
    } else if (message.action === MessageActions.LEAVE_LOBBY) {
        DBHandler.disbandLobby()
    }

    console.log(message);
});