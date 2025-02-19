package mainsrc;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 4;

    // 1280 x 720
    // 2560 x 1440
    // 1920 x 1080

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16; // so hang
    final int maxScreenRow = 12; // so cot
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK); // improve game performance
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override 
    // start game thread automaticly call run
    public void run(){
        while(gameThread != null){
            System.out.print(("The game loop is running\n"));

            // 1 UPDATE: update information such as character positions
            update();
        
            // 2 DRAW: draw the screen with the update ìnormation
            repaint();
        }
    }
    public void update(){

    }
    // public void paintComponent(Garphics g){
    //     super.paintComponent(g);
    //     Graphics2D g2 = (Graphics2D)g;
    //     g2.setColor(Color.white);
    //     g2.fillRect(100,100, tileSize, tileSize);

    // }

}
