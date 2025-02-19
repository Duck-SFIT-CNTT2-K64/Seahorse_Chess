package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
    public GameWindow(){
        setTitle("Isometric SeaHorse Board Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        add(new IsometricBoard());
    }
}