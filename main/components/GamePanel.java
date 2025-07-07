package main.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private int x1Coord =50;
    private int y1Coord =60;
    private int x2Coord =20;
    private int y2Coord =60;
    private int snakeLengthX = 5;
    private int snakeLengthY = 5;

    private int defaultLength = 5;

    private boolean up,down, left, right = true;

    private Timer timer;

    public GamePanel() {
        Border gameBorder = BorderFactory.createLineBorder(Color.WHITE);

        this.setBorder(gameBorder);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        addKeyListener(this);
        this.setFocusable(true);

        startGame();
    }

    public void startGame() {
        timer = new Timer(100, e -> {
            updateSnakePosition();
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Welcome to the Snake Game!", 50, 50);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1Coord, y1Coord, x2Coord, y2Coord);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateSnakePosition();
    }

    @Override
    public void keyTyped(KeyEvent e) {    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed: " + e.getKeyCode() + " " + e.getKeyChar());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP:
                updateDirection(true, false, false, false);
                break;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                updateDirection(false, true, false, false);
                break;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                updateDirection(false, false, true, false);
                break;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                updateDirection(false, false, false, true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("key released: " + e.getKeyCode() + " " + e.getKeyChar());
    }

    public void updateDirection(boolean up, boolean down, boolean left, boolean right) {
        System.out.println("Direction updated: " +
            "Up: " + up + ", Down: " + down +
            ", Left: " + left + ", Right: " + right);
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void updateCoordinates(int x1, int y1, int x2, int y2) {
        this.x1Coord = x1;
        this.y1Coord = y1;
        this.x2Coord = x2;
        this.y2Coord = y2;
    }

    public void updateSnakePosition() {
        if (up) {
            updateCoordinates(x1Coord, y1Coord - snakeLengthY, x2Coord, y2Coord - snakeLengthY);
        } else if (down) {
            updateCoordinates(x1Coord, y1Coord + snakeLengthY, x2Coord, y2Coord + snakeLengthY);
        } else if (left) {
            updateCoordinates(x1Coord - snakeLengthX, y1Coord, x2Coord - snakeLengthX, y2Coord);
        } else if (right) {
            updateCoordinates(x1Coord + snakeLengthX, y1Coord, x2Coord + snakeLengthX, y2Coord);
        }

        repaint();
    }
}
