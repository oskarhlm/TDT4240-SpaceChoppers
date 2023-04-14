import http from 'http';
import DBHandler from './database/DBHandler.js';

const hostname = 'localhost';
const port = 3000;

const dbHandler = new DBHandler;

const server = http.createServer((req, res) => {
  if (req.url === '/highscores') {
    if (req.method === 'GET') {
      dbHandler.getHighscores()
      .then((data) => {
        sendResponse(200, 'application/json', JSON.stringify(data), res);
      })
      .catch((error) => {
        console.log(error);
        sendResponse(500, 'application/json', JSON.stringify({ error: error.message }), res);
      });
    } else {
      res.setHeader('Allow', 'GET');
      sendResponse(405, 'text/plain', 'Method not allowed', res);
    }
  } else {
    sendResponse(404, 'text/plain', 'Not Found', res);
  }
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});

function sendResponse(statusCode, contentType, data, res) {
  res.statusCode = statusCode;
  res.setHeader('Content-Type', contentType);
  res.end(data);
}
