package View;

import Model.GameBoard;
import Model.GameBoardPosition;

import java.util.List;

public class GameBoardViewer {

    public static void showMoves(GameBoard board, List<GameBoardPosition> gameBoardPositions) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("| ");
        for (GameBoardPosition pos : gameBoardPositions) {
            printPositionValue(board, buffer, pos);
        }
        buffer.append("\n");
        String toPrint = buffer.toString();
        System.out.print(toPrint);
    }

    public static void showBoard(GameBoard board) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("=================\n");
        int NSize = board.getNSize();
        for (int i = 0; i < NSize; i++) {
            buffer.append("| ");
            for (int j = 0; j < NSize; j++) {
                GameBoardPosition currentPos = new GameBoardPosition(i, j);
                printPositionValue(board, buffer, currentPos);
            }
            buffer.append("\n");
        }
        buffer.append("=================\n\n");
        String toPrint = buffer.toString();
        System.out.print(toPrint);
    }

    private static void printPositionValue(GameBoard board, StringBuffer buffer, GameBoardPosition currentPos) {
        int n = board.getPositionValue(currentPos);
        String s;
        if (n > 0) {
            s = Integer.toString(n);
            if (Integer.toString(n).length() == 1) {
                s += " ";
            }
        } else {
            s = "  ";
        }
        buffer.append(s + "| ");
    }


}
