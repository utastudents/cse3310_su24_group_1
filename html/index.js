var id = -1;
var lobbyid = -1;
class Player {
    playerName = "";
    playerID = -1;
    points = -1;
    statusPlayer = false;
    inventory = "";
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

        lobbyid = obj.lobbyId;
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

    lobbyScreen.style.display = "none";
}