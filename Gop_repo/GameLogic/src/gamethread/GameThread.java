package gamethread;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import matchmanager.MatchManager;

public class GameThread extends JPanel implements Runnable {
    //MATCH
    MatchManager matchManager = new MatchManager(this);

    //GRAPHIC
    // public int 32 = 32;
    public int maxScreenCol = 42;
    public int maxScreenRow = 24;
    int screenHeight = 768;
    int screenWidth = 1366;

    public int boardStartX = (maxScreenCol - 15) / 2;
    public int boardStartY = (maxScreenRow - 15) / 2;

    public GameThread() {
        // // // this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // // this.setBackground(new Color(219, 253, 255));
        // // this.setDoubleBuffered(true);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics grp) {
        super.paintComponent(grp);
        Graphics2D grp2D = (Graphics2D)(grp);
        ImagePanel("./assets/bgr_img.jpg");
        grp2D.drawImage(image, 0, 0, screenWidth, screenHeight,this);
        // grp.setColor(Color.pink);
        // grp.fillRect(2 * 32, 1 * 32, 32, 32);

        matchManager.Paint(grp);
    }

    //GAME LOOP
    Thread gameThread;
    int maxFPS = 60;

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long timePerFrame = 1000000000 / maxFPS;
        repaint();
        Start();
        while (gameThread != null) {
            long startFrameTime = System.nanoTime();
            repaint();
            Update();
            try {
                long remainFrameTime = timePerFrame - (System.nanoTime() - startFrameTime);
                if (remainFrameTime < 0) {
                    remainFrameTime = 0;
                }
                Thread.sleep(remainFrameTime / 1000000);
            } catch (InterruptedException e) {
                
            }
        }
    }
    
    public void Start() {
        matchManager.Start();
    }

    public void Update() {
        matchManager.Update();
    }

    private BufferedImage image;

    public void ImagePanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath)); // Đọc ảnh từ file
        } catch (IOException e) {
        
        }
    }
}
