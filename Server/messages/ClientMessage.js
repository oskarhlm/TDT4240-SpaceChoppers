class ClientMessage {
  constructor(action, username, payload) {
    if (!Object.values(MessageActions).includes(action)) {
      throw new Error('Invalid message action');
    }

    this.action = action;
    this.username = username;
    this.payload = payload;
  }

  static fromJson(jsonData) {
    const parsedData = JSON.parse(jsonData);
    return new ClientMessage(parsedData.action, parsedData.username, parsedData.payload);
  }
}