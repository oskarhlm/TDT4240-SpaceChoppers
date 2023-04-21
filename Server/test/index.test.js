import WebSocket from 'ws';
import { expect } from 'chai';
import { wss } from '../index.js'
import { MessageActions } from '../messages/MessageActions.js';

describe('WebSocket server', () => {
  let client;

  beforeEach((done) => {
    client = new WebSocket('ws://localhost:6968');
    client.on('open', () => done());
  });

  afterEach(() => {
    client.close();
  });

  it('should create a new lobby when a CREATE_LOBBY message is received', (done) => {
    const message = {
      action: MessageActions.CREATE_LOBBY,
      username: 'testUser',
    };

    client.on('message', (data) => {
      const result = JSON.parse(data);
      expect(result.success).to.be.true;
      expect(result.lobbyID).to.be.a('string');
      done();
    });

    client.send(JSON.stringify(message));
  });

  it('should allow a user to create a lobby and another user to join the same lobby', (done) => {
    const createMessage = {
      action: MessageActions.CREATE_LOBBY,
      username: 'user1',
    };

    client.send(JSON.stringify(createMessage));

    client.once('message', (createData) => {
      const createResult = JSON.parse(createData);
      expect(createResult.success).to.be.true;
      expect(createResult.lobbyID).to.be.a('string');

      const joinMessage = {
        action: MessageActions.JOIN_LOBBY,
        lobbyID: createResult.lobbyID,
        username: 'user2',
      };

      client.once('message', (joinData) => {
        const joinResult = JSON.parse(joinData);
        expect(joinResult.success).to.be.true;
        done();
      });

      client.send(JSON.stringify(joinMessage));
    });
  });

  it('should return an error message for invalid messages', function(done) {
    this.timeout(5000);
    const message = {
      action: 'INVALID_ACTION',
    };
  
    client.on('message', (data) => {
      const result = JSON.parse(data);
      console.log('Here')
      console.log(result); // add this line to check the response from the server
      expect(result.error).to.exist;
      done();
    });
  
    client.send(JSON.stringify(message));
  });

  it('should allow a user to send and receive scores', (done) => {
    const createMessage = {
      action: MessageActions.CREATE_LOBBY,
      username: 'user1',
    };
  
    client.send(JSON.stringify(createMessage));
  
    client.once('message', (createData) => {
      const createResult = JSON.parse(createData);
      expect(createResult.success).to.be.true;
      expect(createResult.lobbyID).to.be.a('string');
  
      const joinMessage = {
        action: MessageActions.JOIN_LOBBY,
        lobbyID: createResult.lobbyID,
        username: 'user2',
      };
  
      client.send(JSON.stringify(joinMessage));
  
      client.once('message', (joinData) => {
        const joinResult = JSON.parse(joinData);
        expect(joinResult.success).to.be.true;
  
        const scoreMessage = {
          action: MessageActions.SEND_SCORE,
          lobbyID: createResult.lobbyID,
          username: 'user1',
          score: 100,
        };
  
        client.send(JSON.stringify(scoreMessage));
  
        client.once('message', (scoreData) => {
          const scoreResult = JSON.parse(scoreData);
  
          const receiveMessage = {
            action: MessageActions.RECEIVE_SCORES,
            lobbyID: createResult.lobbyID,
          };
  
          client.send(JSON.stringify(receiveMessage));
  
          client.once('message', (receiveData) => {
            const receiveResult = JSON.parse(receiveData);
            expect(receiveResult.scores).to.be.an('array').with.lengthOf(2);
            expect(receiveResult.scores[0]).to.deep.equal({ username: 'user1', score: 100 });
            expect(receiveResult.scores[1]).to.deep.equal({ username: 'user2', score: 0 });
            done();
          });
        });
      });
    });
  });  
});
