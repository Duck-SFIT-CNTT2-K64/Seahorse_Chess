package menu_game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Game Co ca ngua");
        setSize(1366, 774);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel bgrPanel = new BackgroundPanel("./assets/bgr_img.jpg");
        bgrPanel.setLayout(new BoxLayout(bgrPanel, BoxLayout.Y_AXIS));
        bgrPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 50)); // canh le

        // tieu de
        JLabel titleLabel = new JLabel("Game Co ca ngua", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // can giua tieu de
        titleLabel.setForeground(Color.BLACK);
        bgrPanel.add(titleLabel);
        bgrPanel.add(Box.createVerticalStrut(20)); // khoang cach giua tieu de va cac nut

        // tao cac nut
        JButton startButton = createMenuButton("Start Game");
        startButton.addActionListener(e -> {
            this.dispose(); // dong cua so hien tai
            App.startGame(); // mo cua so game
        });

        JButton guideButton = createMenuButton("Guide");
        guideButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "This is a guide for the game");
        });

        JButton settingButton = createMenuButton("Setting");
        settingButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "This is a setting for the game");
        });

        JButton exitButton = createMenuButton("Exit");
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        // them cac nut vao bgrPanel
        bgrPanel.add(Box.createVerticalGlue()); // day cac nut ve duoi
        bgrPanel.add(wrapButton(startButton));
        bgrPanel.add(Box.createVerticalStrut(10)); // khoang cach giua cac nut
        bgrPanel.add(wrapButton(guideButton));
        bgrPanel.add(Box.createVerticalStrut(10));
        bgrPanel.add(wrapButton(settingButton));
        bgrPanel.add(Box.createVerticalStrut(10));
        bgrPanel.add(wrapButton(exitButton));
        bgrPanel.add(Box.createVerticalGlue());

        setContentPane(bgrPanel);
        setVisible(true);
    }

    // ham tao nut menu
    private RoundedButton createMenuButton(String text) {
        RoundedButton button = new RoundedButton(text);
        button.setPreferredSize(new Dimension(200, 60));
        button.setMaximumSize(new Dimension(200, 60));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        return button;
    }

    // ham boc nut de giu layout
    private JPanel wrapButton(JButton button) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Giu nut o giua
        panel.setOpaque(false); // lam trong suot de khong che anh nen
        panel.add(button);
        return panel;
    }

    // bo goc va hieu ung hover
    class RoundedButton extends JButton{
        private Color normalColor = new Color(0, 0, 0);
        private Color hoverColor = new Color(30, 144, 255);
        private Color pressedColor = new Color(0, 0, 0);

        public RoundedButton(String text){
            super(text);
            setFont(new Font("Arial", Font.BOLD, 18));
            setPreferredSize(new Dimension(200, 60));
            setMaximumSize(new Dimension(200, 60));
            setFocusPainted(false);            
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setForeground(Color.WHITE);
            setBackground(normalColor);

            addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    setBackground(hoverColor);
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e){
                    setBackground(normalColor);
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e){
                    setBackground(pressedColor);
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e){
                    setBackground(hoverColor);
                    repaint();
                }
            });
        }
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            g2d.dispose();
            super.paintComponent(g);
        }
        @Override
        protected void paintBorder(Graphics g){
            //...
        }
    }

    // anh nen
    class BackgroundPanel extends JPanel {
        private Image bgrImage;
        
        public BackgroundPanel(String imagePath) {
            bgrImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgrImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
