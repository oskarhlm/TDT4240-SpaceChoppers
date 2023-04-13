class GameHandler {
  createLobby(lobbyDetails) {
    return new Promise((resolve, reject) => {
      // Simulate an asynchronous operation, like a network request or database query
        setTimeout(() => {
          // Replace this condition with your own logic for successful lobby creation
          const isLobbyCreated = true;

          if (isLobbyCreated) {
            resolve({ success: true, lobby: lobbyDetails });
          } else {
            reject(new Error('Failed to create lobby'));
          }
        }, 1000); // Simulate a 1 second delay
      });
    }
}