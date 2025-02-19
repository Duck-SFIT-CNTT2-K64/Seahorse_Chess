package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class IsometricBoard extends JPanel{
    private int TILE_WIDTH = 64;
    private int TILE_HEIGHT = 32;
    private int BOARD_SIZE = 15;
    private int startX = 500;
    private int startY = 100;
    private int lastMouseX, lastMouseY;

    private BufferedImage tileImage;
    private BufferedImage test1;
    private BufferedImage test2;
    private BufferedImage test3;
    private BufferedImage test4;
    public IsometricBoard(){
        try {
            tileImage = ImageIO.read(new File("./assets/Isometric_Tiles_Pixel_Art/Blocks/blocks_12.png"));
            test1 = ImageIO.read(new File("./assets/Isometric_Tiles_Pixel_Art/Blocks/blocks_30.png"));
            test2 = ImageIO.read(new File("./assets/Isometric_Tiles_Pixel_Art/Blocks/blocks_31.png"));
            test3 = ImageIO.read(new File("./assets/Isometric_Tiles_Pixel_Art/Blocks/blocks_35.png"));
            test4 = ImageIO.read(new File("./assets/Isometric_Tiles_Pixel_Art/Blocks/blocks_36.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBackground(Color.BLACK);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                lastMouseX = e.getX();
                lastMouseY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                int dx = e.getX() - lastMouseX;
                int dy = e.getY() - lastMouseY;
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                startX += dx;
                startY += dy;
                repaint();
            } 
        });
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // row = x, col = y
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                int x = startX + (col - row) * TILE_WIDTH/2 ;
                int y = startY + (col + row) * TILE_HEIGHT/2 ;
                BufferedImage currentImage;
                if(row < 6 && col < 6){
                    currentImage = test1;
                }else if(row >= 9 && col < 6){
                    currentImage = test2;
                }else if(row < 6 && col >= 9){
                    currentImage = test3;
                }else if(row >= 9 && col >= 9){
                    currentImage = test4;
                }else if(col >= 1 && col <= 6 && row == 7){
                    currentImage = test2;
                }
                else if(col == 7 && row >= 8 && row < 14){
                    currentImage = test4;
                }
                else if(col == 7 && row >= 1 && row <= 6){
                    currentImage = test1;
                }
                else if(row == 7 && col >= 8 && col < 14){
                    currentImage = test3;

                }else{
                    currentImage = tileImage;
                }
                g2d.drawImage(currentImage, x, y, null);
            }
        }
        
    }

}