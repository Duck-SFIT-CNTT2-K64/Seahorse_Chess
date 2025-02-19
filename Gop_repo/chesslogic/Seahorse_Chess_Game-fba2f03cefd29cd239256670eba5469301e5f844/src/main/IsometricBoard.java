package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class IsometricBoard extends JPanel{
    private static final int TILE_WIDTH = 64;
    private static final int TILE_HEIGHT = 32;
    private static final int BOARD_SIZE = 10;
    private BufferedImage tileImage;

    public IsometricBoard(){
        try {
            tileImage = ImageIO.read(new File("./assets/Isometric_Tiles_Pixel_Art/Blocks/blocks_8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(800,600));
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int startX = 400;
        int startY = 50;
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                int x = startX + (col - row) * TILE_WIDTH/2 ;
                int y = startY + (col + row) * TILE_HEIGHT/2 ;
                g2d.drawImage(tileImage, x, y, null);
            }
        }
    }

}