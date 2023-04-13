import { initializeApp }  from 'firebase/app';
import { getFirestore, doc, updateDoc } from 'firebase/firestore';
import { getDatabase, ref, get, child, update, remove } from 'firebase/database';

export default class DBHandler {
  static firebaseConfig = {
    apiKey: "AIzaSyAZxXr9JVYIH_o77yawZ8vM-QBnPy1HhuY",
    authDomain: "tdt4240-spacechoppers.firebaseapp.com",
    databaseURL: "https://tdt4240-spacechoppers-default-rtdb.europe-west1.firebasedatabase.app",
    projectId: "tdt4240-spacechoppers",
    storageBucket: "tdt4240-spacechoppers.appspot.com",
    messagingSenderId: "467082794316",
    appId: "1:467082794316:web:98120a6fadc7fa484d6be9"
  }

  constructor() {
    initializeApp(DBHandler.firebaseConfig);
  }

  async writeToFirestore(nickname, score) {
    const db = getFirestore();
    const highscoreDocRef = doc(db, 'highscores', 'Highscores');

    await updateDoc(highscoreDocRef, {
      [nickname]: score
    });

    console.log('Highscore saved to firestore.');
  }

  writeToDB(lobbyID, nickname, score) {
    const db = getDatabase();
    return update(ref(db, lobbyID), {
      [nickname]: score
    });
    //console.log('Highscore saved to database.');
  }

  async getScoreFromDB(lobbyID, nickname) {
    const dbRef = ref(getDatabase());
    get(child(dbRef, lobbyID)).then((snapshot) => {
      if (snapshot.exists()) {
        console.log(snapshot.val());
        const values = snapshot.val();
        const score = values[nickname];
        console.log("Score: " + score);
      } else {
        console.log("No data available");
      }
    }).catch((error) => {
      console.error(error);
    });
  }

  async removePlayerFromLobby(lobbyID, nickname) {
    const db = getDatabase();

    // create DatabaseReference
    const dbRef = ref(db, lobbyID + "/" + nickname);

    remove(dbRef).then(() => console.log("Deleted"))
  }
}
