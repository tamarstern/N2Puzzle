package GameTests;

import Model.GameBoard;
import Model.GameBoardPosition;
import org.junit.jupiter.api.Test;

public class TestGameBoardPosition {

    @Test
    public void gameBoardPositionValuesShouldMatchInitiatedValues() {
        int xIndex = 2;
        int yIndex = 3;
        GameBoardPosition boardPosition = new GameBoardPosition(xIndex, yIndex);
        assert boardPosition.getXIndex() == xIndex;
        assert boardPosition.getYIndex() == yIndex;

    }


    @Test
    public void shouldThrowExceptionOnNegativeValueOnX() {
        int xIndex = -2;
        int yIndex = 3;
        assertExceptionOnConstructorExecution(xIndex, yIndex);
    }

    private void assertExceptionOnConstructorExecution(int xIndex, int yIndex) {
        boolean thrown = false;
        try {
            new GameBoardPosition(xIndex, yIndex);
        } catch (RuntimeException e) {
            thrown = true;
        }
        assert thrown;
    }

    @Test
    public void shouldThrowExceptionOnNegativeValueOnY() {
        int xIndex = 2;
        int yIndex = -3;
        assertExceptionOnConstructorExecution(xIndex, yIndex);
    }

    @Test
    public void gameBoardNotEqualsNull() {
        int xIndex = 2;
        int yIndex = 3;
        GameBoardPosition boardPosition = new GameBoardPosition(xIndex, yIndex);
        assert !boardPosition.equals(null);
    }

    @Test
    public void gameBoardEqualsItself() {
        int xIndex = 2;
        int yIndex = 3;
        GameBoardPosition boardPosition = new GameBoardPosition(xIndex, yIndex);
        assert boardPosition.equals(boardPosition);
    }

    @Test
    public void gameBoardEqualsItsClone() {
        int xIndex = 2;
        int yIndex = 3;
        GameBoardPosition boardPosition = new GameBoardPosition(xIndex, yIndex);
        GameBoardPosition clone = new GameBoardPosition(boardPosition);
        assert boardPosition.equals(clone);
    }


}
