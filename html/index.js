var id = -1;
var lobbyId = -1;
var lobbyPlayerCount = -1;
class Player {
    playerName = "";
    playerID = -1;
    points = -1;
    statusPlayer = false;
    inventory = "";
}
class UserGuess {
    playerID = -1;
    userGuess = "";
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
    if('leaderboard' in obj) {
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
    }
    /* else if ('CurrentTurn' in obj) {
        // show statistics to everyone
        var t = obj.Stats;
        if (t) {
            document.getElementById("timeMsg").innerHTML = "elapsed time " + t.RunningTime;

        }
    } */
}

function nameSubmit() {
    var usernameInput = document.getElementById("username").value;
    console.log(usernameInput);
    //alert(usernameInput);

    P = new Player();
    P.playerName = usernameInput;
    P.playerID = id;
    P.points = 0;

    connection.send(JSON.stringify(P));
    console.log(JSON.stringify(P));
}

function gameStart() {
    var lobbyScreen = document.getElementById("lobbyScreen");
    var gameScreen = document.getElementById("gameScreen");

    if(lobbyPlayerCount >= 2 && lobbyPlayerCount <= 4) {
        lobbyScreen.style.display = "none";
        gameScreen.style.display = "flex";



        console.log("Game has been started with " + lobbyPlayerCount + " players");
    }
}

function gameStartTest() {
    var lobbyScreen = document.getElementById("lobbyScreen");
    var gameScreen = document.getElementById("gameScreen");

    lobbyScreen.style.display = "none";
    gameScreen.style.display = "flex";
}

function sendUserGuess() {
    var userGuess = document.getElementById("userGuess").value;

    UG = new UserGuess();

    UG.playerID = id;
    UG.userGuess = userGuess;

    if(userGuess) {
        document.getElementById("userFeedback").innerHTML = "";
        connection.send(JSON.stringify(UG));
        console.log(JSON.stringify(UG));
    }
    else {
        document.getElementById("userFeedback").innerHTML = "Please enter a valid character or word(s)";
    }
}