package  matchmanager;

import gamethread.GameThread;
import isometricboard.IsometricBoard;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import player.Player;
import seahorse.SeaHorse;

public class MatchManager {
    //COMPONENTS
    GameThread gameCanvas;

    //BUTTONS
    JButton rollDiceButton = new JButton("Roll");
    JButton skipTurnButton = new JButton("Skip");

    //PLAYERS SETUP
    public int playerCount = 4;
    public ArrayList<Player> players = new ArrayList<>();

    //TURN INFOR
    public int diceNumber = 0;
    public int currentPlayerNumber; 
    public SeaHorse currentSeaHorse = null;

    //MAP INFOR
    
    //public int[][] coordinates = new int[56][2];
    
    //ASSETS
    private int[][] avatarCoordinates = new int[4][2];
    private BufferedImage[] unactivePlayerAvatars = new BufferedImage[4];
    private BufferedImage[] activePlayerAvatars = new BufferedImage[4];
    private BufferedImage rollSkipPanelBackground;

    //BOARD
    private IsometricBoard board;

    public MatchManager(GameThread _gameCanvas) {
        gameCanvas = _gameCanvas;
        SetupAssets();
    }
    
    void SetupAssets() {
        try {
            unactivePlayerAvatars[0] = ImageIO.read(new File("./assets/red_unactive_avatar.png"));
            activePlayerAvatars[0] = ImageIO.read(new File("./assets/red_active_avatar.png"));
            unactivePlayerAvatars[1] = ImageIO.read(new File("./assets/blue_unactive_avatar.png"));
            activePlayerAvatars[1] = ImageIO.read(new File("./assets/blue_active_avatar.png"));
            unactivePlayerAvatars[2] = ImageIO.read(new File("./assets/green_unactive_avatar.png"));
            activePlayerAvatars[2] = ImageIO.read(new File("./assets/green_active_avatar.png"));
            unactivePlayerAvatars[3] = ImageIO.read(new File("./assets/yellow_unactive_avatar.png"));
            activePlayerAvatars[3] = ImageIO.read(new File("./assets/yellow_active_avatar.png"));
            
            rollSkipPanelBackground = ImageIO.read(new File("./assets/roll_skip_panel.png"));
        } catch (IOException e) {
            
        }
    }

    public void Start() {
        //AVATAR
        avatarCoordinates[0][0] = 0;
        avatarCoordinates[0][1] = 0;

        avatarCoordinates[1][0] = (gameCanvas.maxScreenCol - 2) * 32;
        avatarCoordinates[1][1] = 0;

        avatarCoordinates[2][0] = (gameCanvas.maxScreenCol - 2) * 32;
        avatarCoordinates[2][1] = (gameCanvas.maxScreenRow - 2 - 1) * 32;

        avatarCoordinates[3][0] = 0;
        avatarCoordinates[3][1] = (gameCanvas.maxScreenRow - 2 - 1) * 32;

        //ADD PLAYERS
        for (int i = 0; i < playerCount; i++) {
            Player newPlayer = new Player(gameCanvas, this, i);
            players.add(newPlayer);
        }
        
        //SETUP BUTTONS
        rollDiceButton.setBounds(32 * (gameCanvas.maxScreenCol - 4) + 3, 32 * (gameCanvas.maxScreenRow / 2 + 1), 32 * 4 - 6, 32 - 5);
        // rollDiceButton.setBackground(C);
        rollDiceButton.addActionListener(e -> RollDice());    
        gameCanvas.add(rollDiceButton);

        skipTurnButton.setBounds(32 * (gameCanvas.maxScreenCol - 4) + 3, 32 * (gameCanvas.maxScreenRow / 2 + 2), 32 * 4 - 6, 32 - 5);
        skipTurnButton.addActionListener(e -> EndPlayerTurn());
        gameCanvas.add(skipTurnButton);
        
        //MAP
        board = new IsometricBoard();
    }    
    
    public void Paint(Graphics grp) {
        //BOARD
        board.Paint(grp);

        //PLAYERS
        for (int i = 0; i < playerCount; i++) {
            players.get(i).Paint(grp);
        }

        //ROLL DICE & SKIP PANEL
        grp.drawImage(rollSkipPanelBackground, (gameCanvas.maxScreenCol - 4) * 32, (gameCanvas.maxScreenRow / 2 - 3) * 32, 32 * 4, 32 * 6, gameCanvas);

        grp.setFont(new Font("Arial", Font.BOLD, 32 * 2));
        grp.setColor(Color.white);
        grp.drawString("0" + Integer.toString(diceNumber), 32 * (gameCanvas.maxScreenCol - 3) - 3, 32 * (gameCanvas.maxScreenRow / 2) - 3);
        
        //PLAYERS AVATAR
        for (int i = 0; i < playerCount; i++) {
            if (i == currentPlayerNumber) {
                grp.drawImage(activePlayerAvatars[i], avatarCoordinates[i][0], avatarCoordinates[i][1], 32 * 2, 32 * 2, gameCanvas);
                continue;
            }
            grp.drawImage(unactivePlayerAvatars[i], avatarCoordinates[i][0], avatarCoordinates[i][1], 32 * 2, 32 * 2, gameCanvas);
        }
    }

    public void Update() {
        if (currentSeaHorse != null) {

            if(currentSeaHorse.indexOnMap == (players.get(currentPlayerNumber).deployIndexOnMap[currentPlayerNumber]-2)){
                currentSeaHorse.steps = 0;
                currentSeaHorse = null;
                EndPlayerTurn();
            }
            // if(currentSeaHorse.indexOnMap == (players.get(currentPlayerNumber).deployIndexOnMap[currentPlayerNumber]-1)){

            // }
            //check next cell empty or not
                //empty
                    //check next cell have effect or not
                    //move
                
                //not empty
                    //step - 1 == 0
                        //check next cell have effect or not
                        //move
                        //kick
                    //step - 1 > 0
                        //sea horse step = 0
                        //end turn
            
            
            currentSeaHorse.Move();
            if (currentSeaHorse.steps == 0) {
                currentSeaHorse = null;
                EndPlayerTurn();
            }
        }
    }

    void RollDice() {
        diceNumber = 6;
        rollDiceButton.setEnabled(false);
        SetPlayerActions();
    }

    void StartPlayerTurn() {
        rollDiceButton.setEnabled(true);
        skipTurnButton.setEnabled(true);
    }

    void SetPlayerActions() {
        if (diceNumber == 6) {
            players.get(currentPlayerNumber).showDeployButton = true;
        }
        players.get(currentPlayerNumber).ActiveButton();
    }

    public void StartPlayerAction() {
        skipTurnButton.setEnabled(false);
    }

    public void EndPlayerTurn() {
        players.get(currentPlayerNumber).UnactiveButton();
        currentPlayerNumber++;
        if (currentPlayerNumber == 4) currentPlayerNumber = 0;
        diceNumber = 0;
        StartPlayerTurn();
    }
}