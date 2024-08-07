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
    validLetters1 = [];
    validLetters2 = [];
    validLetters3 = [];
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
    document.getElementById("topMessage").innerHTML = "Server Offline";
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
    else if('roundNumber' in obj) {
        var t = obj.roundNumber;
        if(t) {
            console.log("This round " + t);
            document.getElementById("roundNum").innerHTML = "Round " + t + ":";
        }
 
        const letters1 = obj.userEvent.validLetters1;
        if(letters1) {
            letters1.forEach(function updateWordDisplay(value) {
                if(value == 0) {document.getElementById("11").innerHTML = obj.userEvent.charInput;}
                else if(value == 1) {document.getElementById("12").innerHTML = obj.userEvent.charInput;}
                else if(value == 2) {document.getElementById("13").innerHTML = obj.userEvent.charInput;}
                else if(value == 3) {document.getElementById("14").innerHTML = obj.userEvent.charInput;}
                else if(value == 4) {document.getElementById("15").innerHTML = obj.userEvent.charInput;}
                else if(value == 5) {document.getElementById("16").innerHTML = obj.userEvent.charInput;}
                else if(value == 6) {document.getElementById("17").innerHTML = obj.userEvent.charInput;}
                else if(value == 7) {document.getElementById("18").innerHTML = obj.userEvent.charInput;}
                else if(value == 8) {document.getElementById("19").innerHTML = obj.userEvent.charInput;}
                else if(value == 9) {document.getElementById("110").innerHTML = obj.userEvent.charInput;}
                else if(value == 10) {document.getElementById("111").innerHTML = obj.userEvent.charInput;}
                else if(value == 11) {document.getElementById("112").innerHTML = obj.userEvent.charInput;}
                else if(value == 12) {document.getElementById("113").innerHTML = obj.userEvent.charInput;}
                else if(value == 13) {document.getElementById("114").innerHTML = obj.userEvent.charInput;}
                else if(value == 14) {document.getElementById("115").innerHTML = obj.userEvent.charInput;}
            })
        }
        const letters2 = obj.userEvent.validLetters2;
        if(letters2) {
            letters2.forEach(function updateWordDisplay(value) {
                if(value == 0) {document.getElementById("21").innerHTML = obj.userEvent.charInput;}
                else if(value == 1) {document.getElementById("22").innerHTML = obj.userEvent.charInput;}
                else if(value == 2) {document.getElementById("23").innerHTML = obj.userEvent.charInput;}
                else if(value == 3) {document.getElementById("24").innerHTML = obj.userEvent.charInput;}
                else if(value == 4) {document.getElementById("25").innerHTML = obj.userEvent.charInput;}
                else if(value == 5) {document.getElementById("26").innerHTML = obj.userEvent.charInput;}
                else if(value == 6) {document.getElementById("27").innerHTML = obj.userEvent.charInput;}
                else if(value == 7) {document.getElementById("28").innerHTML = obj.userEvent.charInput;}
                else if(value == 8) {document.getElementById("29").innerHTML = obj.userEvent.charInput;}
                else if(value == 9) {document.getElementById("210").innerHTML = obj.userEvent.charInput;}
                else if(value == 10) {document.getElementById("211").innerHTML = obj.userEvent.charInput;}
                else if(value == 11) {document.getElementById("212").innerHTML = obj.userEvent.charInput;}
                else if(value == 12) {document.getElementById("213").innerHTML = obj.userEvent.charInput;}
                else if(value == 13) {document.getElementById("214").innerHTML = obj.userEvent.charInput;}
                else if(value == 14) {document.getElementById("215").innerHTML = obj.userEvent.charInput;}
            })
        }
        const letters3 = obj.userEvent.validLetters3;
        if(letters3) {
            letters3.forEach(function updateWordDisplay(value) {
                if(value == 0) {document.getElementById("31").innerHTML = obj.userEvent.charInput;}
                else if(value == 1) {document.getElementById("32").innerHTML = obj.userEvent.charInput;}
                else if(value == 2) {document.getElementById("33").innerHTML = obj.userEvent.charInput;}
                else if(value == 3) {document.getElementById("34").innerHTML = obj.userEvent.charInput;}
                else if(value == 4) {document.getElementById("35").innerHTML = obj.userEvent.charInput;}
                else if(value == 5) {document.getElementById("36").innerHTML = obj.userEvent.charInput;}
                else if(value == 6) {document.getElementById("37").innerHTML = obj.userEvent.charInput;}
                else if(value == 7) {document.getElementById("38").innerHTML = obj.userEvent.charInput;}
                else if(value == 8) {document.getElementById("39").innerHTML = obj.userEvent.charInput;}
                else if(value == 9) {document.getElementById("310").innerHTML = obj.userEvent.charInput;}
                else if(value == 10) {document.getElementById("311").innerHTML = obj.userEvent.charInput;}
                else if(value == 11) {document.getElementById("312").innerHTML = obj.userEvent.charInput;}
                else if(value == 12) {document.getElementById("313").innerHTML = obj.userEvent.charInput;}
                else if(value == 13) {document.getElementById("314").innerHTML = obj.userEvent.charInput;}
                else if(value == 14) {document.getElementById("315").innerHTML = obj.userEvent.charInput;}
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

        UE = new UserEvent();
        UE.lobbyId = lobbyId;
        UE.playerId = id;
        UE.status = "start";
        UE.userGuess = "";
        UE.validLetters1 = [];
        UE.validLetters2 = [];
        UE.validLetters3 = [];
        UE.charInput = "/";

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
        UE.validLetters1 = [];
        UE.validLetters2 = [];
        UE.validLetters3 = [];
        UE.charInput = "/";

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