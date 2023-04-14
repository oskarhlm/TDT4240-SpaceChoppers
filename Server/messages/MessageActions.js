export const MessageActions = {
  // From client
  CREATE_LOBBY: 'createLobby',
  JOIN_LOBBY: 'joinLobby',
  LEAVE_LOBBY: 'leaveLobby',
  SEND_SCORE: 'sendScore',
  GET_SCORES: 'getScores',

  // From server
  LOBBY_CREATED: "lobbyCreated",
  LOBBY_CREATE_FAILED: "lobbyCreateFailed",
  LOBBY_JOINED: "lobbyJoined",
  LOBBY_JOIN_FAILED: "lobbyJoinFailed",
  LOBBY_LEFT: "lobbyLeft",
  LOBBY_LEFT_FAILED: "lobbyLeftFailed",
  GAME_STARTED: "gameStarted",
  GAME_START_FAILED: "gameStartedFailed",
  SCORES_SENT: "scoresSent",
  SCORES_SENT_FAILED: "scoresSentFailed"
};