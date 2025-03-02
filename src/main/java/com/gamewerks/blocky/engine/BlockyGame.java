package com.gamewerks.blocky.engine;

import java.util.Arrays;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;


public class BlockyGame {
    private static final int LOCK_DELAY_LIMIT = 30;
    private final Board board;
    public Piece activePiece;
    private Direction movement;
    private int lockCounter;
    static PieceKind [] original = PieceKind.ALL;
    PieceKind [] shuffled = Arrays.copyOf(original, length);
    static int length = original.length;
    int index = 0;
                
    public BlockyGame() {
        board = new Board();
        movement = Direction.NONE;
        lockCounter = 0;
        trySpawnBlock();
    }
                
    public static PieceKind[] shuffle(PieceKind[] arr) {
        int n;
        for(int i = length - 1; i >= 0; i--) {
            n = (int)(Math.random()* (i + 1));
            PieceKind temp = arr[i];
            arr[i] = arr[n];
            arr[n] = temp;
        }
        return arr;
    }
    
    private void trySpawnBlock() {
        if (activePiece == null) {
            if(index < 7) {
                activePiece = new Piece(shuffled[index], new Position(Constants.BOARD_HEIGHT - 20, Constants.BOARD_WIDTH / 2 - 2));
            } else {
                shuffle(shuffled);
                index = 0;
                activePiece = new Piece(shuffled[index], new Position(Constants.BOARD_HEIGHT - 20, Constants.BOARD_WIDTH / 2 - 2));
            }
            index++;
            if (board.collides(activePiece)) {
                System.exit(0);
            }
        }
    }
    
    private void processMovement() {
        Position nextPos;
        switch(movement) {
        case NONE:
            nextPos = activePiece.getPosition();
            break;
        case LEFT:
            nextPos = activePiece.getPosition().add(0, -1);
            break;
        case RIGHT:
            nextPos = activePiece.getPosition().add(0, 1);
            break;
        default:
            throw new IllegalStateException("Unrecognized direction: " + movement.name());
        }
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            activePiece.moveTo(nextPos);
        }
    }
    
    private void processGravity() {
        Position nextPos = activePiece.getPosition().add(1, 0);
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            lockCounter = 0;
            activePiece.moveTo(nextPos);
        } else {
            if (lockCounter < LOCK_DELAY_LIMIT) {
                lockCounter += 1;
            } else {
                board.addToWell(activePiece);
                lockCounter = 0;
                activePiece = null;
            }
        }
    }
    
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
        //printWell(board.getWell());
        trySpawnBlock();
        processMovement();
        processGravity();
        processClearedLines();
    }
    
    public boolean[][] getWell() {
        return board.getWell();
    }
    
    public Piece getActivePiece() {return activePiece;}
    public void setDirection(Direction movement) {this.movement = movement;}
    public void rotatePiece(boolean dir) {activePiece.rotate(dir);}
}
