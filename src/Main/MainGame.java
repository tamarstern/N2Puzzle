package Main;

import Behavior.GameBoardSolver;
import Model.GameBoard;
import Model.GameBoardPosition;
import View.GameBoardViewer;

import java.util.List;
import java.util.Scanner;


public class MainGame {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's begin ! Enter board size");
        int boardSize = getBoardSizeFromInput(scanner);

        GameBoard board = generateSolvableBoard(boardSize);
        System.out.println("Initial board:");

        while (!GameBoardSolver.isSolved(board)) {

            if (!GameBoardSolver.canBeSolved(board)) {
                break;
            }
            executeNextMove(scanner, board);
        }

        if (GameBoardSolver.isSolved(board)) {
            System.out.println("Board solved! You win!");
        } else {
            System.out.println("You reached an unsolvable state. Game Over!");
        }
        GameBoardViewer.showBoard(board);

    }

    private static int getBoardSizeFromInput(Scanner scanner) {
        int boardSize = 0;
        while (boardSize == 0) {
            try {
                String boardSizeStr = scanner.next();
                boardSize = Integer.parseInt(boardSizeStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid board size. Please enter a valid integer");
            }
        }
        return boardSize;
    }

    private static GameBoard generateSolvableBoard(int boardSize) {
        GameBoard board = new GameBoard(boardSize);
        System.out.println("Generating initial board. Please wait");
        board.shuffle(50);
        while (!GameBoardSolver.canBeSolved(board)) {
            board = new GameBoard(4);
            board.shuffle(50);
        }
        return board;
    }

    private static void executeNextMove(Scanner scanner, GameBoard board) {
        GameBoardViewer.showBoard(board);
        List<GameBoardPosition> validMoves = board.getAllValidMoves();
        System.out.println("Current Valid Moves:");
        GameBoardViewer.showMoves(board, validMoves);

        int nextMove = getNextMove(scanner);
        GameBoardPosition nextMovePos = null;
        boolean moveInList = false;
        for (GameBoardPosition pos : validMoves) {
            int currPosValue = board.getPositionValue(pos);
            if (currPosValue == nextMove) {
                moveInList = true;
                nextMovePos = pos;
                break;
            }

        }
        if (moveInList) {
            board.executeGameMove(nextMovePos);
        } else {
            System.out.println("Move you entered not in valid moves list");
        }
    }

    private static int getNextMove(Scanner scanner) {
        System.out.println("Enter your next executeGameMove:");
        int nextMove = 0;
        while(nextMove == 0) {
            try {
                String nextMoveStr = scanner.next();
                nextMove = Integer.parseInt(nextMoveStr);
            } catch(NumberFormatException e) {
                System.out.println("You did not enter a valid executeGameMove. Please enter one of the numbers in the valid moves list.");
            }

        }
        return nextMove;
    }


}
