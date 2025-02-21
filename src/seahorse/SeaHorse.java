package seahorse;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import matchmanager.MatchManager;
import player.Player;

public class SeaHorse {
    public Player player;
    
    MatchManager matchManager;

    public int x;
    public int y;

    public int goalX;
    public int goalY;

    public int indexOnMap;
    public int onWinPath = 0;
    public int indexOnWinPath;
    public int steps;
    public int invincible = 1;

    public boolean isInStartStable = true;

    private BufferedImage image;
    // sua lai path cho phu hop voi ban co
    public static int[][] path = {{1, 6}, {2,6}, {3, 6}, {4, 6}, {5, 6}, {6, 6}, {6, 5}, { 6, 4}, {6, 3} , { 6, 2}, {6, 1} , {6, 0}, {7, 0},{8,1},{8,2},{8,3},{8,4},{8,5},{8,6},{9,6},{10,6},{11,6},{12,6},{13,6},{14,6},{14,7},{13,8},{12,8},{11, 8}, {10,8}, {9,8}, {8,8},{8,9},{8,10},{8,11},{8,12},{8,13},{8,14},{7,14},{6,13},{6,12},{6,11},{6,10},{6,9},{6,8},{5,8},{4,8},{3,8},{2,8},{1,8},{0,8},{0,7}};
    public static int[][] winPath = {{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{1,7},{2,7},{3,7},{4,7},{5,7},{6,7}, {7,8}, {7,9}, {7,10}, {7,11}, {7,12}, {7,13}, {8,7}, {9,7}, {10,7}, {11,7}, {12,7}, {13,7}};
    public SeaHorse(int X, int Y, Player _player) {
        x = X;
        y = Y;
        player = _player;
        try {
            image = ImageIO.read(new File("./assets/german_tiger_merged2.png"));
        } catch (IOException e) {
            
        }
    }

    public void Paint(Graphics grp) {
        grp.drawImage(image, x, y, null);
    }

    public void Update() {

    }

    public void Move() {
        int localX, localY;
        if(onWinPath == 1){
            indexOnWinPath++;
            localX = winPath[indexOnWinPath][0];
            localY = winPath[indexOnWinPath][1];
            invincible = 1;
        }
        else{
            indexOnMap++;
            invincible = 0;
            if (indexOnMap == path.length) {
                indexOnMap = 0;
            }
            localX = path[indexOnMap][0];
            localY = path[indexOnMap][1];
        }
        x = 651 + (localY-localX)*64/2;
        y = 100 + (localY+localX)*32/2-20;
        steps--;
    }

}
