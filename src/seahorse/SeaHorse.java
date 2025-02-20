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

    public int steps;

    public boolean isInStartStable = true;

    private BufferedImage image;

    private int[][] path = {{1, 6}, {2,6}, {3, 6}, {4, 6}, {5, 6}, {6, 6}, {6, 5}, { 6, 4}, {6, 3} , { 6, 2}, {6, 1} , {6, 0}, {7, 0},{8,1},{8,2},{8,3},{8,4},{8,5},{8,6},{9,6},{10,6},{11,6},{12,6},{13,6},{14,6},{14,7},{13,8},{12,8},{11, 8}, {10,8}, {9,8}, {8,8},{8,9},{8,10},{8,11},{8,12},{8,13},{8,14},{7,14},{6,13},{6,12},{6,11},{6,10},{6,9},{6,8},{5,8},{4,8},{3,8},{2,8},{1,8},{0,8},{0,7}};

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
        indexOnMap++;
        if (indexOnMap == path.length) {
            indexOnMap = 0;
        }
        int localX = path[indexOnMap][0];
        int localY = path[indexOnMap][1];
        x = 651 + (localY-localX)*64/2;
        y = 100 + (localY+localX)*32/2-20;
        steps--;
    }

}
