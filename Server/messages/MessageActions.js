export const MessageActions = {
  // From client
  CREATE_LOBBY: 'createLobby',
  JOIN_LOBBY: 'joinLobby',
  LEAVE_LOBBY: 'leaveLobby',
  SEND_HIGHSCORE: 'sendHighscore',

  // From server
  LOBBY_CREATED: "lobbyCreated",
  LOBBY_CREATE_FAILED: "lobbyCreateFailed",
  LOBBY_JOINED: "lobbyJoined",
  LOBBY_JOIN_FAILED: "lobbyJoinFailed",
  LOBBY_LEFT: "lobbyLeft",
  LOBBY_LEFT_FAILED: "lobbyLeftFailed",
};