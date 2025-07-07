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
    private int snakeSize = 10;
    private int snakeSpeed = 5;

    private boolean up = false;
    private boolean down = false;
    private boolean left= false;
    private boolean right = true; // Starting position

    private Timer timer; // For animation

    public GamePanel() {
        Border gameBorder = BorderFactory.createLineBorder(Color.WHITE);

        this.setBorder(gameBorder);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        addKeyListener(this);
        this.setFocusable(true);

        timer = new Timer(100, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Welcome to the Snake Game!", 50, 50);
        g2d.setColor(Color.white);
        g2d.fillRect(x1Coord, y1Coord, snakeSize, snakeSize);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveSnake();
        repaint();
    }

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

    public void updateDirection(boolean up, boolean down, boolean left, boolean right) {

        if(this.up && down) return; // Prevents moving in opposite direction immediately
        if(this.down && up) return; // Prevents moving in opposite direction immediately
        if(this.left && right) return; // Prevents moving in opposite direction immediately
        if(this.right && left) return; // Prevents moving in opposite direction immediately

        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void updateCoordinates(int x1, int y1) {
        this.x1Coord = x1;
        this.y1Coord = y1;
    }

    public void moveSnake() {
        if (up) {
            y1Coord -= snakeSpeed;
        } else if (down) {
            y1Coord += snakeSpeed;
        } else if (left) {
            x1Coord -= snakeSpeed;
        } else if (right) {
            x1Coord += snakeSpeed;
        }

        // Boundary conditions
        if (x1Coord < 0) x1Coord = 0;
        if (y1Coord < 0) y1Coord = 0;
        if (x1Coord > getWidth() - snakeSize) x1Coord = getWidth() - snakeSize;
        if (y1Coord > getHeight() - snakeSize) y1Coord = getHeight() - snakeSize;
    }


    @Override
    public void keyTyped(KeyEvent e) {    }


    @Override
    public void keyReleased(KeyEvent e) {    }
}
