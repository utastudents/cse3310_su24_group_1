var idx = -1;
            var gameid = -1;
            class UserEvent {
                Button = -1;
                PlayerIdx = 0;
                GameId = 0;
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
            const ButtonStateToDisplay = new Map();
            ButtonStateToDisplay.set("XPLAYER", "X");
            ButtonStateToDisplay.set("OPLAYER", "O");
            ButtonStateToDisplay.set("NOPLAYER", " ");
            connection.onmessage = function (evt) {
                var msg;
                msg = evt.data;
        
                console.log("Message received: " + msg);
                const obj = JSON.parse(msg);
        
                if ('YouAre' in obj) {
                    if (obj.YouAre == "XPLAYER") {
                        idx = 0;
                    }
                    else {
                        idx = 1;
                    }
        
                    gameid = obj.GameId;
                }
                else if('leaderboard' in obj) {
                    var t = obj.lobbyId;
                    if(t) {
                        console.log("Lobby ID retrieved successfully");
                        document.getElementById("topMessage").innerHTML = "Lobby: " + t;
                    }
                }
                else if ('CurrentTurn' in obj) {
                    // show statistics to everyone
                    var t = obj.Stats;
                    if (t) {
                        document.getElementById("timeMsg").innerHTML = "elapsed time " + t.RunningTime;
                        document.getElementById("statMsg").innerHTML =
                            " in progress " + t.GamesInProgress + " XWin " + t.XWins + " OWin " + t.OWins +
                            " Draw " + t.Draws + " Total " + t.TotalGames;
                    }
        
                    // only pay attention to this game
                    if (gameid == obj.GameId) {
                        // button state to display values
        
                        document.getElementById("b1").value = ButtonStateToDisplay.get(obj.Button[0]);
                        document.getElementById("b2").value = ButtonStateToDisplay.get(obj.Button[1]);
                        document.getElementById("b3").value = ButtonStateToDisplay.get(obj.Button[2]);
                        document.getElementById("b4").value = ButtonStateToDisplay.get(obj.Button[3]);
                        document.getElementById("b5").value = ButtonStateToDisplay.get(obj.Button[4]);
                        document.getElementById("b6").value = ButtonStateToDisplay.get(obj.Button[5]);
                        document.getElementById("b7").value = ButtonStateToDisplay.get(obj.Button[6]);
                        document.getElementById("b8").value = ButtonStateToDisplay.get(obj.Button[7]);
                        document.getElementById("b9").value = ButtonStateToDisplay.get(obj.Button[8]);
        
        
                        // the message line
                        document.getElementById("topMessage").innerHTML = obj.Msg[idx];
                    }
                }
            }
        
            function buttonclick(i) {
                U = new UserEvent();
                U.Button = i;
                if (idx == 0)
                    U.PlayerIdx = "XPLAYER";
                else
                    U.PlayerIdx = "OPLAYER";
                U.GameId = gameid;
                connection.send(JSON.stringify(U));
                console.log(JSON.stringify(U))
            }

function nameSubmit() {
    var usernameInput = document.getElementById("username").value;

    // alert(usernameInput);

    console.log(usernameInput);
}