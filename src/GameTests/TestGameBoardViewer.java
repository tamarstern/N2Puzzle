package GameTests;

import Model.GameBoard;
import Model.GameBoardPosition;
import View.GameBoardViewer;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestGameBoardViewer {

    @Test
    public void generateSameDisplayOnEqualBoards() {
        int NSize = 4;
        GameBoard board = new GameBoard(NSize);
        board.shuffle(20);
        GameBoard cloned = new GameBoard(board);
        boolean boardsEquals = board.equals(cloned);
        assert boardsEquals;
        String firstBoardDisplay = GameBoardViewer.generateBoardDisplay(board);
        String secondBoardDisplay = GameBoardViewer.generateBoardDisplay(cloned);
        assert firstBoardDisplay.equals(secondBoardDisplay);
    }

    @Test
    public void generateDifferentDisplayOnNotEqualBoards() {
        int NSize = 4;
        GameBoard board1 = new GameBoard(NSize);
        board1.shuffle(20);
        GameBoard board2 = new GameBoard(NSize);
        boolean boardsEquals = board1.equals(board2);
        assert !boardsEquals;
        String firstBoardDisplay = GameBoardViewer.generateBoardDisplay(board1);
        String secondBoardDisplay = GameBoardViewer.generateBoardDisplay(board2);
        assert !firstBoardDisplay.equals(secondBoardDisplay);
    }

    @Test
    public void generateSameDisplayOnSameMoves() {
        int NSize = 4;
        GameBoard board = new GameBoard(NSize);
        board.shuffle(20);
        GameBoard cloned = new GameBoard(board);
        boolean boardsEquals = board.equals(cloned);
        assert boardsEquals;
        List<GameBoardPosition> allValidMoves = board.getAllValidMoves();
        String validMovesDisplay = GameBoardViewer.generateMovesDisplay(board, allValidMoves);
        List<GameBoardPosition> allValidMovesCloned = cloned.getAllValidMoves();
        String validMovesCloneDisplay = GameBoardViewer.generateMovesDisplay(cloned, allValidMovesCloned);
        assert validMovesDisplay.equals(validMovesCloneDisplay);
    }

    @Test
    public void generateDifferentDisplayOnDifferentMoves() {
        int NSize = 4;
        GameBoard board1 = new GameBoard(NSize);
        board1.shuffle(20);
        GameBoard board2 = new GameBoard(NSize);
        boolean boardsEquals = board1.equals(board2);
        assert !boardsEquals;
        List<GameBoardPosition> allValidMovesBoard1 = board1.getAllValidMoves();
        String validMovesBoard1Display = GameBoardViewer.generateMovesDisplay(board1, allValidMovesBoard1);
        List<GameBoardPosition> allValidMovesBoard2 = board2.getAllValidMoves();
        String validMovesBoard2Display = GameBoardViewer.generateMovesDisplay(board2, allValidMovesBoard2);
        assert !validMovesBoard1Display.equals(validMovesBoard2Display);
    }

}
