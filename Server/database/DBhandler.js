import { initializeApp }  from 'firebase/app';
import { getFirestore, doc, setDoc, updateDoc, query, collection, orderBy, limit, getDocs } from 'firebase/firestore';
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

  async writeToFirestore(username, score) {
    const db = getFirestore();
    const highscoreDocRef = doc(db, 'highscores', username);

    await setDoc(highscoreDocRef, {
      username: username,
      score: score
    });

    console.log('Highscore saved to firestore.');
  }

  writeToDB(lobbyID, username, score) {
    const db = getDatabase();
    return update(ref(db, lobbyID), {
      [username]: score
    });
    //console.log('Highscore saved to database.');
  }

  getScoreFromDB(lobbyID, username) {
    console.log(lobbyID);
    console.log(username)
    const dbRef = ref(getDatabase());
    return get(child(dbRef, lobbyID));
  }

  removePlayerFromLobby(lobbyID, username) {
    const db = getDatabase();

    // create DatabaseReference
    const dbRef = ref(db, lobbyID + "/" + username);

    return remove(dbRef);
  }

  async getHighscores() {
    const db = getFirestore();
    const q = query(collection(db, 'highscores'), orderBy('score', 'desc'), limit(10));
    const querySnapshot = await getDocs(q);
    const highscores = [];

    querySnapshot.forEach((doc) => {
      const data = doc.data();
      const highscore = {
        username: doc.id,
        score: data.score,
      }
      highscores.push(highscore);
    });


    return highscores;
  }
}
