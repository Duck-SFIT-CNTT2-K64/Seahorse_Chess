package main;

import horse.Pair;
import horse.SeaHorse;
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
    //change board
    private int TILE_WIDTH = 64;
    private int TILE_HEIGHT = 32;
    private int BOARD_SIZE = 15;
    public static int startX = 500;
    public static int startY = 100;
    private int lastMouseX, lastMouseY;

    //change tile
    private BufferedImage tileImage;
    private BufferedImage tileImage2;
    private BufferedImage blueflat;
    private BufferedImage blue;
    private BufferedImage greenflat;
    private BufferedImage green;
    private BufferedImage redflat;
    private BufferedImage red;
    private BufferedImage yellowflat;
    private BufferedImage yellow;
    private BufferedImage bluecircle;
    private BufferedImage greencircle;
    private BufferedImage redcircle;
    private BufferedImage yellowcircle;

    private BufferedImage tank1;
    private SeaHorse seaHorse;
    public IsometricBoard(){
        try {
            tileImage = ImageIO.read(new File("./assets/Land/blocks_white.png"));
            tileImage2 = ImageIO.read(new File("./assets/Land/blocks_white_flat.png"));
            blueflat = ImageIO.read(new File("./assets/Land/blocks_blue_flat.png"));
            blue = ImageIO.read(new File("./assets/Land/blocks_blue.png"));
            greenflat = ImageIO.read(new File("./assets/Land/blocks_green_flat.png"));
            green = ImageIO.read(new File("./assets/Land/blocks_green.png"));
            redflat = ImageIO.read(new File("./assets/Land/blocks_red_flat.png"));
            red = ImageIO.read(new File("./assets/Land/blocks_red.png"));
            yellowflat = ImageIO.read(new File("./assets/Land/blocks_yellow_flat.png"));
            yellow = ImageIO.read(new File("./assets/Land/blocks_yellow.png"));
            bluecircle = ImageIO.read(new File("./assets/Land/blocks_blue_circle_flat.png"));
            greencircle = ImageIO.read(new File("./assets/Land/blocks_green_circle_flat.png"));
            redcircle = ImageIO.read(new File("./assets/Land/blocks_red_circle_flat.png"));
            yellowcircle = ImageIO.read(new File("./assets/Land/blocks_yellow_circle_flat.png"));
            tank1 = ImageIO.read(new File("./assets/Tank/Tiger_export/Merged/german_tiger_merged1.png"));

            seaHorse = new SeaHorse(tank1);
            
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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                seaHorse.move();
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
                if(row==7 && col == 0){
                    currentImage = redcircle;
                }
                else if(row==0 && col == 7){
                    currentImage = bluecircle;
                }
                else if(row==7 && col == 14){
                    currentImage = greencircle;
                }
                else if(row==14 && col == 7){
                    currentImage = yellowcircle;
                }
                else if((row==1&&col==1)|| (row==1)&&(col==4)|| (row==4&&col==1)||(row==4&&col==4)){
                    currentImage = tileImage2;
                }
                else if((col == 7 && row >= 1 && row <= 6)||(row == 0 && col == 6)){
                    currentImage = blue;
                }
                else if ((row==10&&col==1)||(row==10&&col==4)||(row==13&&col==1)||(row==13&&col==4)) {
                    currentImage = tileImage2;
                }
                else if((col >= 1 && col <= 6 && row == 7)|| (row == 8 && col == 0)){
                    currentImage = red;
                }
                else if ((row==1&&col==10)||(row==1&&col==13)||(row==4&&col==10)||(row==4&&col==13)) {
                    currentImage = tileImage2;
                }
                else if((row == 7 && col >= 8 && col < 14)|| (row == 6 && col == 14)){
                    currentImage = green;
                }
                else if ((row==10&&col==10)||(row==10&&col==13)||(row==13&&col==10)||(row==13&&col==13)) {
                    currentImage = tileImage2;
                }
                else if((col == 7 && row >= 8 && row < 14) ||(row == 14 && col == 8)){
                    currentImage = yellow;
                }
                else if(row < 6 && col < 6){
                    currentImage = blueflat;
                }else if(row >= 9 && col < 6){
                    currentImage = redflat;
                }else if(row < 6 && col >= 9){
                    currentImage = greenflat;
                }else if(row >= 9 && col >= 9){
                    currentImage = yellowflat;
                }
                else{
                    currentImage = tileImage;
                }
                g2d.drawImage(currentImage, x, y, null);
            }
        }
        //g2d.drawImage(tank1, startX + (6-1)*TILE_WIDTH/2-32, startY+(6+1)*TILE_HEIGHT/2-64, null);
        // public void Paint(Garphics g){
        Pair<Integer, Integer> position = seaHorse.getPosition();
        int x = startX + (position.getValue()-position.getKey())*TILE_WIDTH/2 - 32;
        int y = startY + (position.getValue()+ position.getKey())*TILE_HEIGHT/2 - 64;
        g2d.drawImage(seaHorse.getImage(), x, y, null);
        // }
        
    }

}