package menu_game;

import javax.swing.JFrame;
import gamethread.GameThread;

public class App {
    public static void main (String[] args){
        new MainMenu();
    }
    public static void startGame() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Sea Horse Game");
        window.setSize(1366, 774);
        
        GameThread gameThread = new GameThread();
        window.add(gameThread);

        gameThread.startGameThread();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
