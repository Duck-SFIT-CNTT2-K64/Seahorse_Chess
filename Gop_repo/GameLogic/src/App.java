
import gamethread.GameThread;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
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
