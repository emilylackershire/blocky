# Blocky Game

_(Gamewerks corporation internal code—do not share!)_

## Credits

Primary developer: Emily Lackershire

CHANGES MADE IN CHANGELOG.md FILE 

### Resources Used

+ Java 17 and Java 23
+ IDE: Apache Netbeans
+ Github
+ Assignment from Professor Osera of Grinnell college
  https://osera.cs.grinnell.edu/ttap/data-structures-labs/the-worlds-best-internship.html
+ This wikipedia article about the fischer-yates shuffle was given in our assignment.
  https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
+ Reaserched how to get a random number in java for the shuffle method.
  https://www.geeksforgeeks.org/java-math-random-method-examples/
+ I was not sure if there was an easy way to make a copy of an array in Java, so I looked that up and there is!
  https://www.geeksforgeeks.org/array-copy-in-java/
+ When doing testing, I remembered there was a way to easily print an array, but I could not remember what it was, so I 
  looked it up.
  https://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
+

## Changelog

~~~console

~~~console
Git Log: 

Update README.md
1 parent 
afe5fb0
 commit 
7c54681

@@ -4,7 +4,7 @@ _(Gamewerks corporation internal code—do not share!)_

## Credits

Primary developer: _(TODO: fill me in)_
Primary developer: Emily Lackershire

### Resources Used

Update README.md
1 parent 
7c54681
 commit 
f60cfe6


### Resources Used

+ _(TODO: fill me in)_
+ ...
+ ...
+ Java 17 and Java 23
+ IDE: Apache Netbeans
+ Github
+ Assignment from Professor Osera of Grinnell college, https://osera.cs.grinnell.edu/ttap/data-structures-labs/the-worlds-best-internship.html 

## Changelog

Update Blocky.java
Changed the locations of the functions so that they weren't nested in main.
1 parent 
f60cfe6
 commit 
a731e7a

private static final int FPS = 10;
private static final double SPF = 1000000000.0 / FPS;

    public static void main(String[] args) {
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
    BlockyGame game = new BlockyGame();
    BlockyPanel panel = new BlockyPanel(game);
    
    
    public void keyReleased(KeyEvent e) {
int code = e.getKeyCode();
if (code == e.VK_LEFT || code == e.VK_RIGHT) {
game.setDirection(Direction.NONE);
@@ -46,8 +29,27 @@ public void keyReleased(KeyEvent e) {
game.rotatePiece(true);
}
}
        });
    public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == e.VK_LEFT) {
                    game.setDirection(Direction.LEFT);
                } else if (code == e.VK_RIGHT) {
                    game.setDirection(Direction.RIGHT);
                }
            }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Blocky");
        

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        frame.addKeyListener(new KeyAdapter());   
    
long timeElapsed = 0;
long prevTime = System.nanoTime();
while (true) {
@@ -61,4 +63,5 @@ public void keyReleased(KeyEvent e) {
}
}
}
    
}

Update Loader.java
fixed off by one error in loader, made program more efficient
1 parent 
a731e7a
 commit 
c364c14

boolean[][] rotation = new boolean[4][4];
for (int row = 3; row >= 0; row--) {
String line = in.nextLine();
            for (int col = 0; col < 5; col++) {
            for (int col = 0; col < 4; col++) {
rotation[row][col] = line.charAt(col) == 'x';
}
}
@@ -23,21 +23,20 @@ private static boolean[][] readRotation(Scanner in) {
public static boolean[][][] loadRotationData(PieceKind piece) throws IOException {
boolean[][][] data = new boolean[4][][];
File file = new File(Constants.DATA_PATH, piece.toString() + ".data");
        Scanner in = new Scanner(file);
        for (int i = 0; i < 4; i++) {
            data[i] = readRotation(in);
            if (in.hasNextLine()) {
                in.nextLine();
        try (Scanner in = new Scanner(file)) {
            for (int i = 0; i < 4; i++) {
                data[i] = readRotation(in);
                if (in.hasNextLine()) {
                    in.nextLine();
                }
}
}
        in.close();
return data;
}

public static HashMap loadAllRotationData() throws IOException {
HashMap ret = new HashMap();
        for (int i = 0; i < PieceKind.ALL.length; i++) {
            PieceKind piece = PieceKind.ALL[i];
        for (PieceKind piece : PieceKind.ALL) {
ret.put(piece, loadRotationData(piece));
}
return ret;

Update Loader.java
Implemented shuffle method
1 parent 
c364c14
 commit 
e1263a4

package com.gamewerks.blocky.util;
package com.gamewerks.blocky;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
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

public class Loader {
    private static boolean[][] readRotation(Scanner in) {
        boolean[][] rotation = new boolean[4][4];
        for (int row = 3; row >= 0; row--) {
            String line = in.nextLine();
            for (int col = 0; col < 4; col++) {
                rotation[row][col] = line.charAt(col) == 'x';
            }
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
        return rotation;
        return shuffled;
}

    
    public static boolean[][][] loadRotationData(PieceKind piece) throws IOException {
        boolean[][][] data = new boolean[4][][];
        File file = new File(Constants.DATA_PATH, piece.toString() + ".data");
        try (Scanner in = new Scanner(file)) {
            for (int i = 0; i < 4; i++) {
                data[i] = readRotation(in);
                if (in.hasNextLine()) {
                    in.nextLine();
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
        return data;
    }
    
    public static HashMap loadAllRotationData() throws IOException {
        HashMap ret = new HashMap();
        for (PieceKind piece : PieceKind.ALL) {
            ret.put(piece, loadRotationData(piece));
        }
        return ret;
}
}

Update Loader.java
Implemented shuffle method
1 parent 
c364c14
 commit 
e1263a4

package com.gamewerks.blocky.util;
package com.gamewerks.blocky;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
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

public class Loader {
    private static boolean[][] readRotation(Scanner in) {
        boolean[][] rotation = new boolean[4][4];
        for (int row = 3; row >= 0; row--) {
            String line = in.nextLine();
            for (int col = 0; col < 4; col++) {
                rotation[row][col] = line.charAt(col) == 'x';
            }
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
        return rotation;
        return shuffled;
}

    
    public static boolean[][][] loadRotationData(PieceKind piece) throws IOException {
        boolean[][][] data = new boolean[4][][];
        File file = new File(Constants.DATA_PATH, piece.toString() + ".data");
        try (Scanner in = new Scanner(file)) {
            for (int i = 0; i < 4; i++) {
                data[i] = readRotation(in);
                if (in.hasNextLine()) {
                    in.nextLine();
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
        return data;
    }
    
    public static HashMap loadAllRotationData() throws IOException {
        HashMap ret = new HashMap();
        for (PieceKind piece : PieceKind.ALL) {
            ret.put(piece, loadRotationData(piece));
        }
        return ret;
}
}

Update Loader.java
Whoops, shuffle is NOT supposed to be here
1 parent 
e1263a4
 commit 
afac781

package com.gamewerks.blocky;
package com.gamewerks.blocky.util;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

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
public class Loader {
    private static boolean[][] readRotation(Scanner in) {
        boolean[][] rotation = new boolean[4][4];
        for (int row = 3; row >= 0; row--) {
            String line = in.nextLine();
            for (int col = 0; col < 4; col++) {
                rotation[row][col] = line.charAt(col) == 'x';
            }
}
        return shuffled;
        return rotation;
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
    
    public static boolean[][][] loadRotationData(PieceKind piece) throws IOException {
        boolean[][][] data = new boolean[4][][];
        File file = new File(Constants.DATA_PATH, piece.toString() + ".data");
        try (Scanner in = new Scanner(file)) {
            for (int i = 0; i < 4; i++) {
                data[i] = readRotation(in);
                if (in.hasNextLine()) {
                    in.nextLine();
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
        return data;
    }
    
    public static HashMap loadAllRotationData() throws IOException {
        HashMap ret = new HashMap();
        for (PieceKind piece : PieceKind.ALL) {
            ret.put(piece, loadRotationData(piece));
        }
        return ret;
}
}

Update BlockyGame.java
Implemented shuffle
1 parent 
afac781
 commit 
92503fb


import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;
import static java.lang.Math.random;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class BlockyGame {
private static final int LOCK_DELAY_LIMIT = 30;

    private Board board;
    private final Board board;
private Piece activePiece;
private Direction movement;

@@ -19,9 +23,25 @@ public BlockyGame() {
trySpawnBlock();
}

    public static PieceKind[] shuffle() {
        PieceKind [] original = PieceKind.ALL;
        int length = original.length;
        PieceKind [] shuffled = Arrays.copyOf(original, length);
        int n;
        for(int i = 0; i < length; i++) {
            n = (int)(Math.random()* (i + 1));
            shuffled[i] = original[n];
            shuffled[n] = original[i];
        }
        return shuffled;
    }
    
    //THIS IS WHERE TO CHANGE PIECE
private void trySpawnBlock() {
        PieceKind[] pieceArr = shuffle();
        PieceKind letter = pieceArr[0];
if (activePiece == null) {
            activePiece = new Piece(PieceKind.I, new Position(Constants.BOARD_HEIGHT - 1, Constants.BOARD_WIDTH / 2 - 2));
            activePiece = new Piece(letter, new Position(Constants.BOARD_HEIGHT - 20, Constants.BOARD_WIDTH / 2 - 2));
if (board.collides(activePiece)) {
System.exit(0);
}
@@ -48,7 +68,7 @@ private void processMovement() {
}

private void processGravity() {
        Position nextPos = activePiece.getPosition().add(-1, 0);
        Position nextPos = activePiece.getPosition().add(1, 0);
if (!board.collides(activePiece.getLayout(), nextPos)) {
lockCounter = 0;
activePiece.moveTo(nextPos);
@@ -69,6 +89,7 @@ private void processClearedLines() {

public void step() {
trySpawnBlock();
        processMovement();
processGravity();
processClearedLines();
}

Update BlockyPanel.java
fixed gravity
1 parent 
92503fb
 commit 
0d1f627

public class BlockyPanel extends JPanel {
private static final int BLOCK_SIZE = 32;

    private int width;
    private int height;
    private BlockyGame game;
    private final int width;
    private final int height;
    private final BlockyGame game;

public BlockyPanel(BlockyGame game) {
width = Constants.BOARD_WIDTH * BLOCK_SIZE;
@@ -25,6 +25,11 @@ public BlockyPanel(BlockyGame game) {
setPreferredSize(new Dimension(width, height));
}

    /**
     *
     * @param g
     */
    @Override
public void paintComponent(Graphics g) {
boolean[][] well = game.getWell();
g.setColor(Color.GRAY);

Update Board.java
This makes it so the pieces stop at the bottom of the board and don't just go off the screen
1 parent 
0d1f627
 commit 
e354faa

}

public boolean collides(boolean[][] layout, Position pos) {
        for (int row = 0; row < layout.length; row++) {
            int wellRow = pos.row - row;
        for (int row = 0; row < (layout.length - 1); row++) {
            int wellRow = pos.row + 1;
for (int col = 0; col < layout[row].length; col++) {
int wellCol = col + pos.col;
if (layout[row][col]) {

Update Board.java
No more out of bounds error
1 parent 
e354faa
 commit 
fd7a0b0

}

public boolean collides(boolean[][] layout, Position pos) {
        for (int row = 0; row < (layout.length - 1); row++) {
            int wellRow = pos.row + 1;
            for (int col = 0; col < layout[row].length; col++) {
        int count = 0;
        for (int row = 0; row < (layout.length); row++) {
            int wellRow = pos.row + row;
            
            for (int col = 0; col < (layout[row].length); col++) {
int wellCol = col + pos.col;
if (layout[row][col]) {
                    count++;
                    System.out.println("wellrow: " + wellRow + " row " + row + " pos.row " + pos.row + " count " + count);
if (!isValidPosition(wellRow, wellCol)) {
return true;
                    } else if (well[wellRow][wellCol]) {
                    } else if (well[wellRow-1][wellCol]) {
return true;
}
}

Update BlockyGame.java
added break in switch statement so that right does not just make the program freeze
1 parent 
fd7a0b0
 commit 
7b6f28d


import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;
import static java.lang.Math.random;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;


public class BlockyGame {
private static final int LOCK_DELAY_LIMIT = 30;
@@ -36,7 +34,6 @@ public static PieceKind[] shuffle() {
return shuffled;
}

    //THIS IS WHERE TO CHANGE PIECE
private void trySpawnBlock() {
PieceKind[] pieceArr = shuffle();
PieceKind letter = pieceArr[0];
@@ -59,6 +56,7 @@ private void processMovement() {
break;
case RIGHT:
nextPos = activePiece.getPosition().add(0, 1);
            break;
default:
throw new IllegalStateException("Unrecognized direction: " + movement.name());
}

Update README.md
uploaded resources used
1 parent 
7b6f28d
 commit 
0626c2c

+ Java 17 and Java 23
+ IDE: Apache Netbeans
+ Github
+ Assignment from Professor Osera of Grinnell college, https://osera.cs.grinnell.edu/ttap/data-structures-labs/the-worlds-best-internship.html 
+ Assignment from Professor Osera of Grinnell college
  https://osera.cs.grinnell.edu/ttap/data-structures-labs/the-worlds-best-internship.html
+ This wikipedia article about the fischer-yates shuffle was given in our assignment.
  https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
+ Reaserched how to get a random number in java for the shuffle method.
  https://www.geeksforgeeks.org/java-math-random-method-examples/
+ I was not sure if there was an easy way to make a copy of an array in Java, so I looked that up and there is!
  https://www.geeksforgeeks.org/array-copy-in-java/
+ When doing testing, I remembered there was a way to easily print an array, but I could not remember what it was, so I 
  looked it up.
  https://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
+

## Changelog

Update Board.java
updated add to well
1 parent 
0626c2c
 commit 
ee80ab0


import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;
import java.util.Arrays;

public class Board {
private boolean[][] well;
@@ -23,18 +24,18 @@ public boolean collides(Piece p) {

public boolean collides(boolean[][] layout, Position pos) {
int count = 0;
        for (int row = 0; row < (layout.length); row++) {
            int wellRow = pos.row + row;
            
        for (int row = 0; row < layout.length; row++) {
            int wellRow = pos.row + row -1;
for (int col = 0; col < (layout[row].length); col++) {
int wellCol = col + pos.col;
if (layout[row][col]) {
count++;
                    System.out.println("wellrow: " + wellRow + " row " + row + " pos.row " + pos.row + " count " + count);
if (!isValidPosition(wellRow, wellCol)) {
return true;
                    } else if (well[wellRow-1][wellCol]) {
                        return true;
                    } else if (wellRow >= 0 && wellRow < well.length && wellCol < well[wellRow].length) {
                        if(well[wellRow][wellCol]) {
                            return true;
                        }
}
}
}
@@ -50,7 +51,10 @@ public void addToWell(Piece p) {
for (int col = 0; col < layout[row].length; col++) {
int wellCol = pos.col + col;
if (isValidPosition(wellRow, wellCol) && layout[row][col]) {
                    well[wellRow][wellCol] = true;
                    if (wellRow >= 0 && wellRow < well.length && wellCol < well[wellRow].length) {
                        well[wellRow][wellCol] = true;
                        
                    }
}
}
}
@@ -69,8 +73,13 @@ public void deleteRow(int n) {

public void deleteRows(List rows) {
for (int i = 0; i < rows.size(); i++) {
            int row = (Integer) rows.get(i);
            System.out.println(rows.get(i));
            
            
            
            int row = (int) rows.get(i);
deleteRow(row);
            //row + 1
}
}

Update Board.java
delete row is no longer causing error
1 parent 
ee80ab0
 commit 
e94e9b6


import java.util.LinkedList;
import java.util.List;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;
import java.util.Arrays;

public class Board {
    private boolean[][] well;
    public boolean[][] well;

public Board() {
well = new boolean[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
@@ -72,14 +70,10 @@ public void deleteRow(int n) {
}

public void deleteRows(List rows) {
        for (int i = 0; i < rows.size(); i++) {
        for (int i = 0; i < (rows.size() - 1); i++) {
System.out.println(rows.get(i));
            
            
            
int row = (int) rows.get(i);
deleteRow(row);
            //row + 1
}
}

@@ -100,6 +94,5 @@ public List getCompletedRows() {
}
return completedRows;
}
    
public boolean[][] getWell() { return well; }
}

Update Board.java
tried to fix some things with the delete so that the rows would actually delete, it didn't work unfortunately.

public Board() {
well = new boolean[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
}
    
    //needs fixing, well length
public boolean isValidPosition(int row, int col) {
return row >= 0 && row <= well.length && col >= 0 && col <= well[0].length;
}
@@ -57,11 +57,11 @@ public void addToWell(Piece p) {
}
}
}
    
    //
public void deleteRow(int n) {
        for (int row = 0; row < n - 1; row++) {
        for (int row = n - 1; row >= 0; row--) {
for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
                well[row][col] = well[row+1][col];
                well[row+1][col] = well[row][col];
}
}
for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
@@ -70,10 +70,9 @@ public void deleteRow(int n) {
}

public void deleteRows(List rows) {
        for (int i = 0; i < (rows.size() - 1); i++) {
            System.out.println(rows.get(i));
        for (int i = 0; i < (rows.size()); i++) {
int row = (int) rows.get(i);
            deleteRow(row);
            deleteRow(row + i);
}
}

Update BlockyGame.java
tried to fix the block boundaries so they actually go on top of each other
1 parent 
1b79e04
 commit 
319c120


import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;
import static java.lang.constant.ConstantDescs.NULL;
import java.util.Arrays;
import java.util.List;


public class BlockyGame {
private static final int LOCK_DELAY_LIMIT = 30;
    
private final Board board;
    private Piece activePiece;
    public Piece activePiece;
private Direction movement;
    
private int lockCounter;

public BlockyGame() {
@@ -84,8 +84,24 @@ private void processGravity() {
private void processClearedLines() {
board.deleteRows(board.getCompletedRows());
}
   
    /**
     * Helper function to print of the array of booleans representing block boundaries
     * @param arr
    */ 
    public void printWell(boolean arr[][]) {
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
       System.out.println(); 
    }
    

public void step() {
        printWell(board.getWell());
trySpawnBlock();
processMovement();
processGravity();
~~~
~~~
