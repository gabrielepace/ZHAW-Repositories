package ch.zhaw.moba1.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerOneScore, playerTwoScore, playerStatus, connected;
    private Button[] buttons = new Button[9];
    private Button reset;

    private int playerOneScoreCount, playerTwoScoreCount;

    public boolean activePlayerX;

    public int[] gameState = {2,2,2,2,2,2,2,2,2};

    public int[][] winningPositions = {
                    {0,1,2}, {3,4,5}, {6,7,8}, // rows
                    {0,3,6}, {1,4,7}, {2,5,8}, // columns
                    {0,4,8}, {2,4,6} // diagonals
    };

    private WebSocket webSocket;

    boolean online = false;
    boolean alreadyPlayed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);
        connected = (TextView) findViewById(R.id.connected);

        reset = (Button) findViewById(R.id.reset);

        for (int i=0; i< buttons.length; i++){
            String buttonId = "btn_" + i;
            int resourceId = getResources().getIdentifier(buttonId, "id",getPackageName());
            buttons[i] = (Button) findViewById(resourceId);
            buttons[i].setOnClickListener(this);
        }

        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayerX = true;

        findViewById(R.id.onlinebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPlayerScore();
                playAgain();
                goOnline();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
//                playerOneScoreCount = 0;
//                playerTwoScoreCount = 0;
//                playerStatus.setText("");
//                updatePlayerScore();
            }
        });
    }

    void goOnline(){
        Log.i("app","going online");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://80.218.189.9:3001").build();//"wss://echo.websocket.org""ws://localhost:3001/""ws://178.82.64.27:3001/""ws://moba1.herokuapp.com/"
        webSocket = client.newWebSocket(request, new SocketListener());
    }

    private class SocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            Log.i("app","onOpen success");
            online = true;
            connected.setText("Connected");
        }

        @Override
        public void onMessage(WebSocket webSocket, final String boardRecieved) {
            super.onMessage(webSocket, boardRecieved);
//            Log.i("app","new board " + boardRecieved);
//            Log.i("app","old gameState " + Arrays.toString(gameState));
            try {
                JSONObject newBoard = new JSONObject(boardRecieved);
                JSONArray newGameState  = (JSONArray) newBoard.get("message");
                int zeroes = 0;//X, represented by 0
                int ones = 0;//O, represented by 1
                for (int i = 0; i < gameState.length; i++) {
                    gameState[i] = (int)newGameState.get(i);
                    if(gameState[i] == 0)zeroes++;
                    if(gameState[i] == 1)ones++;
                }
                activePlayerX = ones>=zeroes;//true => X
                //Log.i("app","new gameState " + Arrays.toString(gameState));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            updateBoard();
            alreadyPlayed = false;


//            if (!checkWinner()) {
//                activePlayer = !activePlayer;
//            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showWinner();
                    Log.i("app","checking winner after onMessage: " + checkWinner());
                    Log.i("app","new gameState after onMessage: " + Arrays.toString(gameState));

                }
            });
        }
    }

    void sendBoard(){
        try {
            if(webSocket != null){
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray(gameState);
                String board = Arrays.toString(gameState);
                jsonObject.put("message", jsonArray);
                Boolean a = webSocket.send(jsonObject.toString());
//                Log.i("app: ","board sent");
//                Log.i("board was : ",board);
//                Log.i("send-successful: ",a.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //uses the board array to update the graphics
    public void updateBoard(){
        int[] btns = {R.id.btn_0,R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4,R.id.btn_5,R.id.btn_6,R.id.btn_7,R.id.btn_8};

        for (int i = 0;i<9;i++){
            Button current = (Button) findViewById(btns[i]);
            if(gameState[i] == 0){
                current.setText("X");
                current.setTextColor(Color.parseColor("#FFC34A"));
            } else if(gameState[i] == 1) {
                current.setText("O");
                current.setTextColor(Color.parseColor("#70FFEA"));
            } else {
                current.setText("");
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if(online && alreadyPlayed){
            return;
        }
        if(!online){
            activePlayerX= !activePlayerX;
        }

        String buttonId = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length() - 1, buttonId.length()));

        if (activePlayerX) {
            gameState[gameStatePointer] = 0;
        } else {
            gameState[gameStatePointer] = 1;
        }
        alreadyPlayed=true;

        sendBoard();
        updateBoard();
        showWinner();
    }

    boolean checkIfTie(){
        int sum = 0;
        for (int i = 0;i<9;i++){
            sum+= gameState[i] == 2 ? 0 : 1;
        }
        return sum == 9;
    }

    void showWinner(){
        if (checkWinner()!=2) {
            if(checkWinner()==0){
                playerOneScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player X won last game!");
                Toast.makeText(this, "Player X Won!", Toast.LENGTH_SHORT).show();

//                playAgain();
//                goOnline();
            }else {
                playerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player O won last game!");
                Toast.makeText(this, "Player O Won!", Toast.LENGTH_SHORT).show();
//                  playAgain();
//                goOnline();
            }
        }else if(checkIfTie()){
            playerStatus.setText("Nobody won last game");
            Toast.makeText(this, "No Winner!", Toast.LENGTH_SHORT).show();
//            playAgain();
//            goOnline();
        }else {
            //if(online) activePlayerX = !activePlayerX;
        }

//        if(playerOneScoreCount > playerTwoScoreCount){
//            playerStatus.setText("Player X is Winning!");
//        }else if(playerTwoScoreCount > playerOneScoreCount){
//            playerStatus.setText("Player O is Winning!");
//        }else {
//            playerStatus.setText("");
//        }
    }

    public int checkWinner(){
        int winnerResult = 2;//2 means nobody won yet, 0 = X won, 1 = O won

        for(int[] winningPosition: winningPositions){
            if((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                    (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&
                            (gameState[winningPosition[2]] != 2)){
                winnerResult = gameState[winningPosition[2]];
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void resetPlayerScore(){
        playerOneScore.setText("0");
        playerTwoScore.setText("0");
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
    }



    public void playAgain(){
        for(int i=0; i<buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
        sendBoard();
        alreadyPlayed=false;
        activePlayerX = true;
    }
}
