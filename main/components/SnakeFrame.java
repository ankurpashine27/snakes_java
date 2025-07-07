package main.components;

import javax.swing.*;

public class SnakeFrame extends JFrame {

    GamePanel gamePanel = new GamePanel();

    public SnakeFrame() {
        this.add(gamePanel);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setResizable(false);

        this.setVisible(true);

        gamePanel.requestFocusInWindow();
    }

}
