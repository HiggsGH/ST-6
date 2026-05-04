package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.player1.symbol = 'X';
        game.player2.symbol = 'O';
        game.cplayer = game.player1;
    }

    @Test
    void testInitialState() {
        assertEquals(State.PLAYING, game.state);
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }

    @Test
    void testCheckStateXWinHorizontal() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }

    @Test
    void testCheckStateOWinVertical() {
        game.symbol = 'O';
        game.board[0] = 'O';
        game.board[3] = 'O';
        game.board[6] = 'O';
        State result = game.checkState(game.board);
        assertEquals(State.OWIN, result);
    }

    @Test
    void testCheckStateXWinDiagonal() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[4] = 'X';
        game.board[8] = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }

    @Test
    void testCheckStateDraw() {
        char[] fullBoard = {'X','O','X','O','X','O','O','X','O'};
        game.symbol = 'X';
        State result = game.checkState(fullBoard);
        assertEquals(State.DRAW, result);
    }

    @Test
    void testGenerateMoves() {
        game.board = new char[]{'X',' ','O',' ','X',' ','O',' ','X'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(5));
        assertTrue(moves.contains(7));
        assertEquals(4, moves.size());
    }

    @Test
    void testEvaluatePositionXWin() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        int result = game.evaluatePosition(game.board, game.player1);
        assertEquals(Game.INF, result);
    }

    @Test
    void testEvaluatePositionOWin() {
        game.symbol = 'O';
        game.board[0] = 'O';
        game.board[3] = 'O';
        game.board[6] = 'O';
        int result = game.evaluatePosition(game.board, game.player2);
        assertEquals(Game.INF, result);
    }

    @Test
    void testMinMove() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        game.player2.symbol = 'O';
        int result = game.MinMove(game.board, game.player1);
        assertTrue(result <= 0);
    }

    @Test
    void testMaxMove() {
        game.board = new char[]{'O',' ',' ',' ',' ',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        game.player2.symbol = 'O';
        int result = game.MaxMove(game.board, game.player2);
        assertTrue(result >= -Game.INF);
    }

    @Test
    void testMiniMax() {
        game.board = new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' '};
        game.player2.symbol = 'O';
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    void testEmptyBoard() {
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }

    @Test
    void testSymbolSwitch() {
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }

    @Test
    void testUtilityPrint() {
        char[] testBoard = {'X','O','X',' ',' ',' ',' ',' ',' '};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }

    @Test
    void testTicTacToeCell() {
        TicTacToeCell cell = new TicTacToeCell(5, 1, 1);
        assertEquals(5, cell.getNum());
        assertEquals(1, cell.getRow());
        assertEquals(1, cell.getCol());
        assertEquals(' ', cell.getMarker());
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
    }

    @Test
    void testUtilityPrintCharArray() {
        char[] testBoard = {'X', 'O', 'X', ' ', 'O', ' ', 'X', ' ', ' '};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }

    @Test
    void testUtilityPrintIntArray() {
        int[] testBoard = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertDoesNotThrow(() -> Utility.print(testBoard));

        int[] emptyBoard = new int[9];
        assertDoesNotThrow(() -> Utility.print(emptyBoard));
    }

    @Test
    void testUtilityPrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(1);
        moves.add(3);
        moves.add(5);
        moves.add(7);
        assertDoesNotThrow(() -> Utility.print(moves));
    }

    @Test
    void testUtilityPrintEmptyArrays() {
        char[] emptyCharBoard = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        assertDoesNotThrow(() -> Utility.print(emptyCharBoard));

        int[] emptyIntBoard = new int[9];
        assertDoesNotThrow(() -> Utility.print(emptyIntBoard));

        ArrayList<Integer> emptyList = new ArrayList<>();
        assertDoesNotThrow(() -> Utility.print(emptyList));
    }
}