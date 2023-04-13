// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
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

// Initialize Firebase
const app = initializeApp(firebaseConfig);

await highscoreRef.set({
  'nickname': 'Museknusern',
  'score': 420
});

function disbandLobby() {
    // Send highscore and disband lobby
}
