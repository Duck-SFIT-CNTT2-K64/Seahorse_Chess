package player;

import gamethread.GameThread;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JButton;
import matchmanager.MatchManager;
import seahorse.SeaHorse;

public class Player {
    GameThread gameCanvas;
    MatchManager matchManager;

    public int playerIndex;

    public boolean showDeployButton = false;
    public JButton deployButton = new JButton();
    public ArrayList<SeaHorse> seaHorses = new ArrayList<>();
    public ArrayList<JButton> chooseSeaHorseButton = new ArrayList<>();

    public int indexOfSeaHorseOnDeploy = -1;
    public int[] deployIndexOnMap = {12, 51, 38, 25};
    public int[][] deployCoordinates = {
        {8,0},
        {0,6},
        {6,14},
        {14,8}
    };
    public int[][][] startStableCoordinates = {
        {{10,1},{10,4},{13,1},{13,4}},
        {{1,1},{1,4},{4, 1},{4, 4}},
        {{1, 10}, {1, 13}, {4, 10}, {4, 13}},
        {{10,10}, {10,13}, {13,10}, {13,13}},
    };

    public Player(GameThread _gameCanvas, MatchManager _matchManager, int _playerIndex) {
        gameCanvas = _gameCanvas;
        matchManager = _matchManager;

        gameCanvas.add((deployButton));
        for (int i = 0; i < 4; i++) {
            chooseSeaHorseButton.add(new JButton());
            gameCanvas.add(chooseSeaHorseButton.get(i));
        }

        playerIndex = _playerIndex;

        //LOCAL TO GLOBAL
        int localX = deployCoordinates[playerIndex][0];
        int localY = deployCoordinates[playerIndex][1];
        deployCoordinates[playerIndex][0] = 651 + (localY-localX)*64/2 - 32;
        deployCoordinates[playerIndex][1] = 100 + (localY+localX)*32/2 - 64;

        for (int i = 0; i < 4; i++) {
            localX = startStableCoordinates[playerIndex][i][0];
            localY = startStableCoordinates[playerIndex][i][1];
            startStableCoordinates[playerIndex][i][0] = 651 + (localY-localX)*64/2 - 32;
            startStableCoordinates[playerIndex][i][1] = 100 + (localY+localX)*32/2 - 64;
        }

        //CHOOSE SEAHORSE BUTTONS
        for (int i = 0; i < 4; i++) {
            int index = i;
            chooseSeaHorseButton.get(i).addActionListener(e -> MoveSeaHorse(index));
        }

        //DEPLOY BUTTON
        deployButton.addActionListener(e -> DeploySeaHorse());
        deployButton.setBounds(deployCoordinates[playerIndex][0], deployCoordinates[playerIndex][1], 32, 32);

        UnactiveButton();

        //CREATE SEAHORSES
        for (int i = 0; i < 4; i++) {
            seaHorses.add(new SeaHorse(startStableCoordinates[playerIndex][i][0], startStableCoordinates[playerIndex][i][1], this));
        }
    }

    // public void SetDeployCoordinates(int[] _deployCoordinates) {
    //     deployCoordinates = _deployCoordinates;
    //     deployButton.setBounds(deployCoordinates[0] * 32, deployCoordinates[1] * 32, 32, 32);
    //     deployButton.addActionListener(e -> DeploySeaHorse());
    // 
    //     for (int i = 0; i < 4; i++) {
    //         int index = i;
    //         chooseSeaHorseButton.get(i).addActionListener(e -> MoveSeaHorse(index));
    //     }
    // }
    // 
    // public void SetStartStableCoordinates(int[][] _startStableCoordinates) {
    //     startStableCoordinates = _startStableCoordinates;
    //     for (int i = 0; i < 4; i++) {
    //         seaHorses.add(new SeaHorse(startStableCoordinates[i][0] * 32, startStableCoordinates[i][1] * 32));
    //     }
    //     UnactiveButton();
    // }

    public void Update() {

    }

    public void Paint(Graphics grp) {
        for (int i = 0; i < 4; i++) {
            seaHorses.get(i).Paint(grp);
        }
    }

    public void ActiveButton() {
        if (indexOfSeaHorseOnDeploy == -1) {
            deployButton.setVisible(showDeployButton);
        }
        else {
            deployButton.setVisible(false);
        }
        
        for (int i = 0; i < 4; i++) {
            chooseSeaHorseButton.get(i).setBounds(seaHorses.get(i).x, seaHorses.get(i).y, 32, 32);
            chooseSeaHorseButton.get(i).setVisible(!seaHorses.get(i).isInStartStable);
        }
    }

    public void UnactiveButton() {
        deployButton.setVisible(false);
        for (int i = 0; i < 4; i++) {
            chooseSeaHorseButton.get(i).setVisible(false);
        }
    }

    void DeploySeaHorse() {
        for (int i = 0; i < 4; i++) {
            UnactiveButton();
            if (seaHorses.get(i).isInStartStable == true) {
                seaHorses.get(i).isInStartStable = false;
                seaHorses.get(i).x = deployCoordinates[playerIndex][0];
                seaHorses.get(i).y = deployCoordinates[playerIndex][1];
                seaHorses.get(i).indexOnMap = deployIndexOnMap[playerIndex];
                indexOfSeaHorseOnDeploy = i;
                showDeployButton = false;
                matchManager.EndPlayerTurn();
                break;
            }
        }
    }

    void MoveSeaHorse(int index) {
        UnactiveButton();
        matchManager.StartPlayerAction();
        matchManager.currentSeaHorse = seaHorses.get(index);
        seaHorses.get(index).steps = matchManager.diceNumber;
        if (index == indexOfSeaHorseOnDeploy) {
            indexOfSeaHorseOnDeploy = -1;
        }
    }
}
