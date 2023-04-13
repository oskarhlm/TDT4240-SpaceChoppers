import http from 'http'
import DBHandler from './database/DBHandler.js'

const hostname = 'localhost';
const port = 3000;

const dbHandler = new DBHandler();

const server = http.createServer((req, res) => {
  if (req.url === '/highscores') {
    if (req.method === 'GET') {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');

      dbHandler.getHighscores()
      .then((data) => {
        console.log(data);
        const parsedData = JSON.stringify(data);
        res.end(parsedData);
      })
      .catch((error) => {
        console.log(error);
      })

    } else {
      res.statusCode = 405;
      res.setHeader('Content-Type', 'text/plain');
      res.setHeader('Allow', 'GET');
      res.end('Method not allowed');
    }
  } else {
    res.statusCode = 200;
    res.setHeader('Content-Type', 'text/plain');
    res.end('Hello World\n');
  }
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});
