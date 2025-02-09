package com.gamewerks.blocky;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.gamewerks.blocky.engine.BlockyGame;
import com.gamewerks.blocky.engine.Direction;
import com.gamewerks.blocky.engine.PieceKind;
import com.gamewerks.blocky.gfx.BlockyPanel;
import java.util.Arrays;

public class Blocky {
    private static final int FPS = 10;
    private static final double SPF = 1000000000.0 / FPS;
    
    public static int[] shuffle() {
        int [] original = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int length = original.length;
        int [] shuffled = Arrays.copyOf(original, length);
        int n;
        for(int i = 0; i < length; i++) {
            n = (int)(Math.random()* (i + 1));
            shuffled[i] = original[n];
            shuffled[n] = original[i];
        }
        return shuffled;
    }
    
    public static void main(String[] args) {
        
        int[] pieceArr = shuffle();
        System.out.println(Arrays.toString(pieceArr));
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Blocky");
        
        BlockyGame game = new BlockyGame();
        BlockyPanel panel = new BlockyPanel(game);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == e.VK_LEFT) {
                    game.setDirection(Direction.LEFT);
                } else if (code == e.VK_RIGHT) {
                    game.setDirection(Direction.RIGHT);
                }
            }
            
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == e.VK_LEFT || code == e.VK_RIGHT) {
                    game.setDirection(Direction.NONE);
                } else if (code == e.VK_Z) {
                    game.rotatePiece(false);
                } else if (code == e.VK_X) {
                    game.rotatePiece(true);
                }
            }
        });
        
        long timeElapsed = 0;
        long prevTime = System.nanoTime();
        while (true) {
            long currentTime = System.nanoTime();
            timeElapsed += currentTime - prevTime;
            prevTime = currentTime;
            if (timeElapsed > SPF) {
                game.step();
                panel.paintImmediately(new Rectangle(0, 0, panel.getWidth(), panel.getHeight()));
                timeElapsed = (long) (timeElapsed - SPF);
            }
        }
    }
}
