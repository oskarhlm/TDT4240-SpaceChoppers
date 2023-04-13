class GameHandler {
  createLobby(lobbyDetails) {
    return new Promise((resolve, reject) => {
      // Simulate an asynchronous operation, like a network request or database query
        setTimeout(() => {
          // Replace this condition with your own logic for successful lobby creation
          const isLobbyCreated = true;

          if (isLobbyCreated) {
            const response = new ServerMessage(MessageActions.LOBBY_CREATED, "");
            resolve(response);
          } else {
            const response = new ServerMessage(MessageActions.LOBBY_CREATE_FAILED, "");
            reject(response);
          }
        }, 1000); // Simulate a 1 second delay
    });
  }

  joinLobby(username, lobbyID) {
    return new Promise((resolve, reject) => {
      // Simulate an asynchronous operation, like a network request or database query
      setTimeout(() => {
        // Replace this condition with your own logic for successful lobby joining
        const isLobbyJoined = true;

        if (isLobbyJoined) {
          const response = new ServerMessage(MessageActions.LOBBY_JOINED, "");
          resolve(response);
        } else {
          const response = new ServerMessage(MessageActions.LOBBY_JOIN_FAILED, "");
          reject(response);
        }
      }, 1000); // Simulate a 1 second delay
    });
  }

  leaveLobby(username, lobbyID) {
    return new Promise((resolve, reject) => {
      // Simulate an asynchronous operation, like a network request or database query
      setTimeout(() => {
        // Replace this condition with your own logic for successful lobby joining
        const hasLeftLobby = true;

        if (hasLeftLobby) {
          const response = new ServerMessage(MessageActions.LOBBY_JOINED, "");
          resolve(response);
        } else {
          const response = new ServerMessage(MessageActions.LOBBY_JOIN_FAILED, "");
          reject(response);
        }
      }, 1000); // Simulate a 1 second delay
    });
  }

  updateScore(username, payload) {
    // No need for promise, dont really care if it doesnt update
    const score = payload.score;
    const lobbyID = payload.lobbyID;
  }
}