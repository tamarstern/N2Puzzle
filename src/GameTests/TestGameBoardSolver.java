package GameTests;

import Behavior.GameBoardSolver;
import Model.GameBoard;
import Model.GameBoardPosition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestGameBoardSolver {

    @Test
    public void testNeighborsBoards() {
        GameBoard board = new GameBoard(4);
        board.shuffle(20);
        List<GameBoardPosition> validMoves = board.getAllValidMoves();
        List<GameBoard> adjBoards = new ArrayList<>();
        for (GameBoardPosition pos : validMoves) {
            GameBoard cloned = new GameBoard(board);
            cloned.executeGameMove(pos);
            adjBoards.add(cloned);
        }
        List<GameBoard> adjBoardsGeneratedFromOriginal = GameBoardSolver.getAllNeighborsBoards(board);
        for (GameBoard adjBoard : adjBoards) {
            assert adjBoardsGeneratedFromOriginal.contains(adjBoard);
        }
    }

    @Test
    public void verifyGameBoardSolution() {
        int NSIze = 4;
        GameBoard solution = GameBoardSolver.getSolution(NSIze);
        assert NSIze == solution.getNSize();
        assert GameBoardSolver.isSolved(solution);
    }

    @Test
    public void heuristicCostIs0OnSolvedBoard() {
        GameBoard board = new GameBoard(4);
        boolean solved = GameBoardSolver.isSolved(board);
        assert solved;
        int misplaced = GameBoardSolver.heuristicCostEstimate(board);
        assert misplaced == 0;

    }


    @Test
    public void heuristicCost1OnSolvedBoardWith1Move() {
        GameBoard board = new GameBoard(4);
        boolean solved = GameBoardSolver.isSolved(board);
        assert solved;
        List<GameBoardPosition> validMoves = board.getAllValidMoves();
        GameBoardPosition gameBoardPosition = validMoves.get(0);
        board.executeGameMove(gameBoardPosition);
        int misplaced = GameBoardSolver.heuristicCostEstimate(board);
        assert misplaced == 1;

    }

    @Test
    public void canBeSolvedReturnTrueForSolvablePermutation() {
        GameBoard board = new GameBoard(4);
        board.shuffle(50);
        boolean canBeSolved = GameBoardSolver.canBeSolved(board);
        assert canBeSolved == true;
    }

    //Attention ! This test trying to run the algorithm on an unsolvable permutation . So it's runtime is long
    @Test
    public void canBeSolvedReturnFalseForUnsolvablePermutation() {
        GameBoard unsolvable = new GameBoard(3);
        GameBoardPosition position1 = new GameBoardPosition(2,0);
        unsolvable.setPositionValue(position1, 8);
        GameBoardPosition position2 = new GameBoardPosition(2,1);
        unsolvable.setPositionValue(position2, 7);
        boolean canBeSolved = GameBoardSolver.canBeSolved(unsolvable);
        assert canBeSolved == false;
    }


}
