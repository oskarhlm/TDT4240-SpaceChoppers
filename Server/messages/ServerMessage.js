class ServerMessage {
  constructor(action, payload) {
    if (!Object.values(MessageActions).includes(action)) {
      throw new Error('Invalid message action');
    }

    this.action = action;
    this.payload = payload;
  }
}