// Import the functions you need from the SDKs you need
import { initializeApp }  from 'firebase/app';
import { getFirestore, doc, updateDoc } from 'firebase/firestore';
import { getDatabase, ref, get, child, update, remove } from 'firebase/database';
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyAZxXr9JVYIH_o77yawZ8vM-QBnPy1HhuY",
  authDomain: "tdt4240-spacechoppers.firebaseapp.com",
  databaseURL: "https://tdt4240-spacechoppers-default-rtdb.europe-west1.firebasedatabase.app",
  projectId: "tdt4240-spacechoppers",
  storageBucket: "tdt4240-spacechoppers.appspot.com",
  messagingSenderId: "467082794316",
  appId: "1:467082794316:web:98120a6fadc7fa484d6be9"
};

const app = initializeApp(firebaseConfig);

export async function writeToFirestore(nickname, score) {
  const db = getFirestore();
  const highscoreDocRef = doc(db, 'highscores', 'Highscores');

  await updateDoc(highscoreDocRef, {
    [nickname]: score
  });

  console.log('Highscore saved to firestore.');
}


export async function writeToDB(lobbyID, nickname, score) {
  const db = getDatabase();
  update(ref(db, lobbyID), {
    [nickname]: score
  });
  console.log('Highscore saved to database.');
}

export async function getScoreFromDB(lobbyID, nickname) {
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

export async function removePlayerFromLobby(lobbyID, nickname) {
  const db = getDatabase();
 
  // create DatabaseReference
  const dbRef = ref(db, lobbyID + "/" + nickname);

  remove(dbRef).then(() => console.log("Deleted"))
}

export async function isLobbyEmpty(lobbyID) {
  const db = getDatabase();
  const lobbyRef = ref(db, lobbyID);

  const snapshot = await get(lobbyRef);
  console.log(snapshot.val())

  if (snapshot.exists()) {
    return snapshot.val() === 0;
  }

  return false;
}

// Helper functions (realtime)
// writeToDB - (lobbyID, nickname, score) 
// getScoreFromDB (lobbyID, nickname)
// isLobbyEmpty (lobbyID) => true/false
// deleteLobby (lobbyID)
// removePlayerFromDB (lobbyID, nickname)

// Firestore
// writeToFirestore(nickname, score)


// create lobby:
// 1. lager en ID server-side, lager dokument med lobbyID i realtime, lagrer nickname og score i realtime

// join lobby
// 1. nickname og ID, lagrer nickname og score under lobbyID i realtime

// update score
// 1. player sender score hvert sekund, med lobbyID, lagrer under nickname i realtime

// player leaves lobby
// 1. hent ut sscore og nick og last opp i firestore
// 2. hvis player[] == 0, slett
