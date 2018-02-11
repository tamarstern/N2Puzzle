package View;

import Model.GameBoard;
import Model.GameBoardPosition;

import java.util.List;

public class GameBoardViewer {

    private static final String NEW_LINE = "\n";

    private static final String BOARD_SEPARATOR = "=================";

    private static final String ONE_SPACE = " ";

    private static final String TWO_SPACE = "  ";

    private static final String BOARD_POSITION_SEPARATOR = "|";

    public static String generateMovesDisplay(GameBoard board, List<GameBoardPosition> gameBoardPositions) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BOARD_POSITION_SEPARATOR);
        buffer.append(ONE_SPACE);
        for (GameBoardPosition pos : gameBoardPositions) {
            generatePositionValueDisplay(board, buffer, pos);
        }
        buffer.append(NEW_LINE);
        String moveDisplay = buffer.toString();
        return moveDisplay;
    }

    public static String generateBoardDisplay(GameBoard board) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BOARD_SEPARATOR);
        buffer.append(NEW_LINE);
        int NSize = board.getNSize();
        for (int i = 0; i < NSize; i++) {
            buffer.append(BOARD_POSITION_SEPARATOR);
            buffer.append(ONE_SPACE);
            for (int j = 0; j < NSize; j++) {
                GameBoardPosition currentPos = new GameBoardPosition(i, j);
                generatePositionValueDisplay(board, buffer, currentPos);
            }
            buffer.append(NEW_LINE);
        }
        buffer.append(BOARD_SEPARATOR);
        buffer.append(NEW_LINE);
        buffer.append(NEW_LINE);
        String boardStr = buffer.toString();
        return boardStr;
    }

    private static void generatePositionValueDisplay(GameBoard board, StringBuffer buffer, GameBoardPosition currentPos) {
        int n = board.getPositionValue(currentPos);
        String s;
        if (n > 0) {
            s = Integer.toString(n);
            if (Integer.toString(n).length() == 1) {
                s += ONE_SPACE;
            }
        } else {
            s = TWO_SPACE;
        }
        buffer.append(s + BOARD_POSITION_SEPARATOR + ONE_SPACE);
    }
}
