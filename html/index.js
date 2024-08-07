// global variables and necessary classes to send JSON data to backend are stored here
var id = -1;
var conn = "";
var lobbyId = -1;
var lobbyPlayerCount = -1;
class Player {
    playerName = "";
    playerID = -1;
    conn = "";
    points = -1;
    statusPlayer = false;
    inventory = "";
}
class UserEvent {
    lobbyId = -1;
    playerId = -1;
    status = "";
    userGuess = "";
    validLetters = [];
    charInput = "";
}

var connection = null;
        
var serverUrl;
serverUrl = "ws://" + window.location.hostname +":"+ (parseInt(location.port) + 100);
// Create the connection with the server
connection = new WebSocket(serverUrl);
        
connection.onopen = function (evt) {
    console.log("open");
    document.getElementById("topMessage").innerHTML = "Server Online";
}

connection.onclose = function (evt) {
    console.log("close");
    document.getElementById("topMessage").innerHTML = "Server Offline"
}

connection.onmessage = function (evt) {
    var msg;
    msg = evt.data;

    console.log("Message received: " + msg);
    const obj = JSON.parse(msg);

    // When server is online, lobby ID should be displayed in the header of the html file under the title of the game
    if('players' in obj) {
        var t = obj.lobbyId;
        if(t) {
            console.log("Lobby ID retrieved successfully");
            document.getElementById("topMessage").innerHTML = "Lobby: " + t;
        }

        lobbyId = obj.lobbyId;
        lobbyPlayerCount = obj.playerCount;
    }
    else if('playerID' in obj) {
        var t = obj.playerID;
        if(t) {
            console.log("Player ID retrieved successfully");
            document.getElementById("topMessage2").innerHTML = "Player: " + t;
        }

        id = obj.playerID;
        conn = obj.conn;
    }
    else if('validLetters' in obj) {
        const letters = obj.validLetters;
        if(letters) {
            letters.forEach(function updateWordDisplay(value, index, array) {
                if(value == 0) {document.getElementById("11").innerHTML = obj.charInput;}
                if(value == 1) {document.getElementById("12").innerHTML = obj.charInput;}
                if(value == 2) {document.getElementById("13").innerHTML = obj.charInput;}
                if(value == 3) {document.getElementById("14").innerHTML = obj.charInput;}
                if(value == 4) {document.getElementById("15").innerHTML = obj.charInput;}
                if(value == 5) {document.getElementById("16").innerHTML = obj.charInput;}
                if(value == 6) {document.getElementById("17").innerHTML = obj.charInput;}
                if(value == 7) {document.getElementById("18").innerHTML = obj.charInput;}
                if(value == 8) {document.getElementById("19").innerHTML = obj.charInput;}
                if(value == 9) {document.getElementById("110").innerHTML = obj.charInput;}
                if(value == 10) {document.getElementById("111").innerHTML = obj.charInput;}
                if(value == 11) {document.getElementById("112").innerHTML = obj.charInput;}
                if(value == 12) {document.getElementById("113").innerHTML = obj.charInput;}
                if(value == 13) {document.getElementById("114").innerHTML = obj.charInput;}
                if(value == 14) {document.getElementById("115").innerHTML = obj.charInput;}
            })
        }
    }
    /* else if ('CurrentTurn' in obj) {
        // show statistics to everyone
        var t = obj.Stats;
        if (t) {
            document.getElementById("timeMsg").innerHTML = "elapsed time " + t.RunningTime;

        }
    } */
}
// nameSubmit() takes the text value given by the user, saves it using the same structure as Player class, and
// then sending it to the backend to save the user's information
function nameSubmit() {
    var usernameInput = document.getElementById("username").value;
    console.log(usernameInput);
    //alert(usernameInput);

    if(usernameInput) {
        P = new Player();
        P.playerName = usernameInput;
        P.playerID = id;
        P.conn = conn;
        P.points = 0;

        connection.send(JSON.stringify(P));
        console.log(JSON.stringify(P));
    }
    else {
        // add message here to send to user when username is invalid
    }
}
// gameStart() works by hiding the lobby screen and then enabling the game screen to become visible
// The start also only works when the amount of players is valid (need to add/change function to do this for all
// players once one person starts or let other players know when someone else has started)
function gameStart() {
    var lobbyScreen = document.getElementById("lobbyScreen");
    var gameScreen = document.getElementById("gameScreen");

    if(lobbyPlayerCount >= 2 && lobbyPlayerCount <= 4) {
        lobbyScreen.style.display = "none";
        gameScreen.style.display = "flex";

        UE = new UserEvent()
        UE.lobbyId = lobbyId;
        UE.playerId = id;
        UE.status = "start";
        UE.userGuess = "empty";
        UE.validLetters = [];
        UE.charInput = "a";

        connection.send(JSON.stringify(UE));
        console.log(JSON.stringify(UE));
        console.log("Game has been started with " + lobbyPlayerCount + " players");
    }
}
// gameStartTest() is a temporary function to allow testing the game without the need of a second player
function gameStartTest() {
    var lobbyScreen = document.getElementById("lobbyScreen");
    var gameScreen = document.getElementById("gameScreen");

    lobbyScreen.style.display = "none";
    gameScreen.style.display = "flex";
}
// sendUserGuess() takes user input to allow for the game to happen (validity checking needs to be added)
function sendUserGuess() {
    var userGuess = document.getElementById("userGuess").value;

    // The input is only sent if the input is not empty or (valid; needs to be implemented)
    if(userGuess) {
        UE = new UserEvent();
        UE.lobbyId = lobbyId;
        UE.playerId = id;
        UE.status = "start";
        UE.userGuess = userGuess;
        document.getElementById("userFeedback").innerHTML = "";
        connection.send(JSON.stringify(UE));
        console.log(JSON.stringify(UE));
    }
    else {
        document.getElementById("userFeedback").innerHTML = "Please enter a valid character or word(s)";
    }
}

/*
IMPORTANT!: TO DO
- NEED TO IMPLEMENT FUNCTIONALITY TO SHOW LETTERS AS VALID GUESSES OCCUR
- NEED TO HAVE A WAY TO SHOW ROUND # AND TO SWITCH TO A NEW ROUND
- HAVE TO SEND ROUND UPDATES TO ALL PLAYERS
- (MAY NEED TO WORK ON BOTH FRONTEND AND BACKEND TO IMPLEMENT THESE OR ONE INDIVIDUALLY)
*/