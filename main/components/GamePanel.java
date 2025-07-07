package main.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel extends JPanel {

    private int xCoord =50;
    private int yCoord =60;
    private int snakeLength = 5;

    private Timer timer;

    public GamePanel() {
        Border gameBorder = BorderFactory.createLineBorder(Color.WHITE);

        this.setBorder(gameBorder);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);

        startGame();
    }

    public void startGame() {
        timer = new Timer(100, e -> {
            xCoord += 5;
            repaint();
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Welcome to the Snake Game!", 50, 50);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(xCoord, yCoord, xCoord -snakeLength, 60);

    }
}
