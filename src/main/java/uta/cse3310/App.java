
// This is example code provided to CSE3310 Fall 2022
// You are free to use as is, or changed, any of the code provided

// Please comply with the licensing requirements for the
// open source packages being used.

// This code is based upon, and derived from the this repository
//            https:/thub.com/TooTallNate/Java-WebSocket/tree/master/src/main/example

// http server include is a GPL licensed package from
//            http://www.freeutils.net/source/jlhttp/

/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package uta.cse3310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.time.Instant;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App extends WebSocketServer {

  // All lobbies currently underway on this server are stored in
  // the vector ActiveLobbies
  private Vector<Lobby> ActiveLobbies = new Vector<Lobby>();

  private int lobbyId = 1;

  private int connectionId = 0;

  private Instant startTime;

  private Statistics stats;

  public App(int port) {
    super(new InetSocketAddress(port));
  }

  public App(InetSocketAddress address) {
    super(address);
  }

  public App(int port, Draft_6455 draft) {
    super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
  }

  @Override
  public void onOpen(WebSocket conn, ClientHandshake handshake) {

    connectionId++;

    System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
    System.out.println(conn);

    ServerEvent SE = new ServerEvent();

    Gson gson = new Gson();

    // search for a lobby needing a player
    Lobby L = null;
    for (Lobby i : ActiveLobbies) {
      if ((i.getPlayerCount() >= 1 && i.getPlayerCount() < 4) && i.gameStatus == false) {
        L = i;
        System.out.println("FOUND AN ONGOING LOBBY");
      }
    }

    // No matches ? Create a new Lobby.
    if (L == null) {
      L = new Lobby(lobbyId);
      lobbyId++;
      // Add the first player
      Player newPlayer = new Player("", connectionId, conn.toString());
      String jsonString = gson.toJson(newPlayer);
      conn.send(jsonString);
      L.players.add(newPlayer);
      L.setPlayerCount();
      SE.youAre = PlayerNum.ONE;
      SE.lobbyId = lobbyId;
      SE.playerId = connectionId;
      SE.playerCount = L.getPlayerCount();
      ActiveLobbies.add(L);
      System.out.println("CREATING A NEW LOBBY");
    } 
    else if(L.getGameStatus() == false) {
      // join an existing Lobby
      Player newPlayer = new Player("", connectionId, conn.toString());
      String jsonString = gson.toJson(newPlayer);
      conn.send(jsonString);
      L.players.add(newPlayer);
      L.setPlayerCount();
      SE.lobbyId = lobbyId;
      SE.playerId = connectionId;
      SE.playerCount = L.getPlayerCount();
      if(L.getPlayerCount() == 2) {
        SE.youAre = PlayerNum.TWO;
      }
      if(L.getPlayerCount() == 3) {
        SE.youAre = PlayerNum.THREE;
      }
      if(L.getPlayerCount() == 2) {
        SE.youAre = PlayerNum.FOUR;
      }
      System.out.println("NOT A NEW LOBBY");
    }

    SE.lobbyId = L.lobbyId;

    // allows the websocket to give us the Lobby when a message arrives..
    // it stores a pointer to L, and will give that pointer back to us
    // when we ask for it
    conn.setAttachment(L);

    // Note only send to the single connection
    String jsonString = gson.toJson(SE);
    conn.send(jsonString);
    System.out
        .println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + connectionId + " "
            + escape(jsonString));

    // Update the running time
    stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

    // The state of the game has changed, so lets send it to everyone
    jsonString = gson.toJson(L);
    System.out
        .println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
    broadcast(jsonString);

  }

  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    System.out.println(conn + " has closed");
    // Retrieve the game tied to the websocket connection
    Lobby L = conn.getAttachment();
    for(Player p : L.players) {
      if(p.getConn().equals(conn.toString())) {
        System.out.println("Removing player with connection " + p.getConn());
        L.players.remove(p);
        L.setPlayerCount();
        break;
      }
    }
    if(L.getPlayerCount() == 0) {
      System.out.println("Lobby empty, removing");
      this.ActiveLobbies.remove(L);
    }
    L = null;
  }

  @Override
  public void onMessage(WebSocket conn, String message) {
    System.out
        .println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "-" + " " + escape(message));

    // Bring in the data from the webpage
    // A UserEvent is all that is allowed at this point
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    UserEvent UE = gson.fromJson(message, UserEvent.class);

    // Update the running time
    stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

    // Get our Game Object
    Lobby L = conn.getAttachment();
    //L.Update();
    
    // When a user submits their username
    /*Player P = gson.fromJson(message, Player.class);
    for (Player p : L.players) {
      if(p.getPlayerID() == P.getPlayerID()) {
        p.setPlayerName(P.getPlayerName());
        System.out.println(p.getPlayerName());
      }
    }*/

    L.setUserEvent(UE);

    /* if(UE.status.equals("start")) {
      L.gameStart();
    }
    // When a user submits a guess
    else if(UE.userGuess.length() == 1) {
      UE.charInput = UE.userGuess.charAt(0);
      UE.validLetters = L.getGameplay().getRound().checkChar(UE.charInput);
      jsonString = gson.toJson(UE);
      broadcast(jsonString);
    } */

    // send out the game state every time
    // to everyone
    String jsonString;
    jsonString = gson.toJson(L.gameplay.round);

    System.out
        .println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
    broadcast(jsonString);
  }

  @Override
  public void onMessage(WebSocket conn, ByteBuffer message) {
    System.out.println(conn + ": " + message);
  }

  @Override
  public void onError(WebSocket conn, Exception ex) {
    ex.printStackTrace();
    if (conn != null) {
      // some errors like port binding failed may not be assignable to a specific
      // websocket
    }
  }

  @Override
  public void onStart() {
    setConnectionLostTimeout(0);
    stats = new Statistics();
    startTime = Instant.now();
  }

  private String escape(String S) {
    // turns " into \"
    String retval = new String();
    // this routine is very slow.
    // but it is not called very often
    for (int i = 0; i < S.length(); i++) {
      Character ch = S.charAt(i);
      if (ch == '\"') {
        retval = retval + '\\';
      }
      retval = retval + ch;
    }
    return retval;
  }

  public static void main(String[] args) {

    String HttpPort = System.getenv("HTTP_PORT");
    int port = 9001;
    if (HttpPort!=null) {
      port = Integer.valueOf(HttpPort);
    }

    // Set up the http server

    HttpServer H = new HttpServer(port, "./html");
    H.start();
    System.out.println("http Server started on port: " + port);

    // create and start the websocket server

    port = 9101;
    String WSPort = System.getenv("WEBSOCKET_PORT");
    if (WSPort!=null) {
      port = Integer.valueOf(WSPort);
    }

    App A = new App(port);
    A.setReuseAddr(true);
    A.start();
    System.out.println("websocket Server started on port: " + port);

  }
}
