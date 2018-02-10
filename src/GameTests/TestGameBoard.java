package GameTests;

import Behavior.GameBoardSolver;
import Exceptions.IllegalBoardException;
import Exceptions.InvalidMoveException;
import Model.GameBoard;
import Model.GameBoardPosition;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestGameBoard {

    @Test
    public void newGameBoardShouldBeSolved() {
        GameBoard board = new GameBoard(4);
        boolean solved = GameBoardSolver.isSolved(board);
        assert solved;
    }

    @Test
    public void newGameBoardHaveBlankPositionLast() {
        int size = 4;
        GameBoard board = new GameBoard(size);
        GameBoardPosition blankPosition = board.getBlankPosition();
        assert blankPosition.getXIndex() == size - 1;
        assert blankPosition.getYIndex() == size - 1;
    }

    @Test
    public void blankPositionValueShouldBe0() {
        int size = 4;
        GameBoard board = new GameBoard(size);
        GameBoardPosition blankPosition = board.getBlankPosition();
        assert board.getPositionValue(blankPosition) == 0;
    }

    @Test
    public void gameBoardEqualsItsClone() {
        GameBoard board = new GameBoard(4);
        board.shuffle(20);
        GameBoard cloned = new GameBoard(board);
        boolean boardsEquals = board.equals(cloned);
        assert boardsEquals;
    }

    @Test
    public void shouldReturnAllElementsOnGetAllPositions() {
        int NSize = 4;
        int sumAllElements = 0;
        for (int i = 0; i < (NSize * NSize); i++) {
            sumAllElements += i;
        }
        GameBoard board = new GameBoard(4);
        board.shuffle(20);
        List<GameBoardPosition> gameBoardPositions = board.getAllGameBoardPositions();
        int sumBoardElements = 0;
        for (GameBoardPosition pos : gameBoardPositions) {
            int posValue = board.getPositionValue(pos);
            sumBoardElements += posValue;
        }
        assert sumAllElements == sumBoardElements;
    }

    @Test
    public void throwExceptionWhenMovingInvalidMoveWithNegativeX() {
        GameBoard board = new GameBoard(4);
        GameBoardPosition illegalPosition = new GameBoardPosition(-1, 0);
        boolean thrown = false;
        try {
            board.executeGameMove(illegalPosition);
        } catch (InvalidMoveException e) {
            thrown = true;
        }
        assert thrown;
    }

    @Test
    public void throwExceptionWhenMovingOnInvalidBoard() {
        GameBoard board = new GameBoard(4);
        GameBoardPosition blankPosition = board.getBlankPosition();
        board.setPositionValue(blankPosition, 4 * 4);
        List<GameBoardPosition> validMoves = board.getAllValidMoves();
        GameBoardPosition move = validMoves.get(0);
        boolean thrown = false;
        try {
            board.executeGameMove(move);
        } catch (IllegalBoardException e) {
            thrown = true;
        }
        assert thrown;
    }

    @Test
    public void throwExceptionWhenMovingOnInvalidMove() {
        GameBoard board = new GameBoard(4);
        GameBoardPosition invalidMove = getInvalidMove(board);
        boolean thrown = false;
        try {
            board.executeGameMove(invalidMove);
        } catch (InvalidMoveException e) {
            thrown = true;
        }
        assert thrown;
    }

    private GameBoardPosition getInvalidMove(GameBoard board) {
        List<GameBoardPosition> validMoves = board.getAllValidMoves();
        List<GameBoardPosition> allGameBoardPositions = board.getAllGameBoardPositions();
        GameBoardPosition invalidMove = null;
        for (GameBoardPosition pos : allGameBoardPositions) {
            if (!validMoves.contains(pos)) {
                invalidMove = pos;
                break;
            }
        }
        return invalidMove;
    }

    @Test
    public void newGameBoardInitializedNSizeCorrectly() {
        int NSize = 3;
        GameBoard board = new GameBoard(NSize);
        assert board.getNSize() == NSize;
    }

    @Test
    public void throwExceptionWhenMovingInvalidMoveWithNegativeY() {
        GameBoard board = new GameBoard(4);
        GameBoardPosition illegalPosition = new GameBoardPosition(0, -1);
        boolean thrown = false;
        try {
            board.executeGameMove(illegalPosition);
        } catch (InvalidMoveException e) {
            thrown = true;
        }
        assert thrown;
    }


    @Test
    public void moveShouldReplaceBlankPosition() {
        GameBoard board = new GameBoard(4);
        GameBoardPosition blankPosition = board.getBlankPosition();
        int blankPositionValue = board.getPositionValue(blankPosition);
        List<GameBoardPosition> validMoves = board.getAllValidMoves();
        GameBoardPosition move = validMoves.get(0);
        int movedValue = board.getPositionValue(move);
        board.executeGameMove(move);
        int newMovedValue = board.getPositionValue(move);
        assert newMovedValue == blankPositionValue;
        int newBlankPositionValue = board.getPositionValue(blankPosition);
        assert newBlankPositionValue == movedValue;

    }

    @Test
    public void gameBoardNotEqualsNull() {
        GameBoard board = new GameBoard(4);
        assert !board.equals(null);
    }

    @Test
    public void gameBoardEqualsItself() {
        GameBoard board = new GameBoard(4);
        assert board.equals(board);
    }


}
