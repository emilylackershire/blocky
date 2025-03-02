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
    
    public BlockyGame() {
        board = new Board();
        movement = Direction.NONE;
        lockCounter = 0;
        trySpawnBlock();
    }
    
    public static PieceKind[] shuffle() {
        PieceKind [] original = PieceKind.ALL;
        int length = original.length;
        PieceKind [] shuffled = Arrays.copyOf(original, length);
        int n;
        for(int i = length - 1; i >= 0; i--) {
            n = (int)(Math.random()* (i + 1));
            PieceKind temp = shuffled[i];
            shuffled[i] = shuffled[n];
            shuffled[n] = temp;
        }
        return shuffled;
    }
    
    private void trySpawnBlock() {
        PieceKind[] pieceArr = shuffle();
        PieceKind letter = pieceArr[0];
        if (activePiece == null) {
            activePiece = new Piece(letter, new Position(Constants.BOARD_HEIGHT - 20, Constants.BOARD_WIDTH / 2 - 2));
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
        printWell(board.getWell());
        trySpawnBlock();
        processMovement();
        processGravity();
        processClearedLines();
    }
    
    public boolean[][] getWell() {
        return board.getWell();
    }
    
    public Piece getActivePiece() { return activePiece; }
    public void setDirection(Direction movement) { this.movement = movement; }
    public void rotatePiece(boolean dir) { activePiece.rotate(dir); }
}
