package Behavior;

import Model.GameBoard;
import Model.GameBoardPosition;

import java.util.*;

public class GameBoardSolver {

    private static final HashMap<Integer, GameBoard> SolvedGameBoards = new HashMap<>();

    public static GameBoard getSolution(int NSize) {
        if (SolvedGameBoards.get(NSize) == null) {
            SolvedGameBoards.put(NSize, new GameBoard(NSize));
        }
        return SolvedGameBoards.get(NSize);
    }

    public static int heuristicCostEstimate(GameBoard board) {
        int misplacedElements = 0;
        GameBoard solution = GameBoardSolver.getSolution(board.getNSize());
        for (GameBoardPosition position : board.getAllGameBoardPositions()) {
            int currValue = board.getPositionValue(position);
            if (currValue > 0 && currValue != solution.getPositionValue(position)) {
                misplacedElements++;
            }
        }
        return misplacedElements;
    }

    public static boolean canBeSolved(GameBoard start) {
        return solveBoard(start) != null;
    }

    public static List<GameBoard> getAllNeighborsBoards(GameBoard start) {
        ArrayList<GameBoard> boards = new ArrayList<>();
        List<GameBoardPosition> validMoves = start.getAllValidMoves();
        for (GameBoardPosition move : validMoves) {
            GameBoard clonedBoard = new GameBoard(start);
            clonedBoard.executeGameMove(move);
            boards.add(clonedBoard);
        }
        return boards;
    }

    public static boolean isSolved(GameBoard board) {
        return GameBoardSolver.heuristicCostEstimate(board) == 0;
    }


    private static List<GameBoard> solveBoard(GameBoard start) {
        HashMap<GameBoard, Integer> gScoreMap = new HashMap<>();
        final HashMap<GameBoard, Integer> fScoreMap = new HashMap<>();
        Comparator<GameBoard> comparator = Comparator.comparingInt(fScoreMap::get);
        PriorityQueue<GameBoard> openSet = new PriorityQueue<>(10000, comparator);
        HashMap<GameBoard, GameBoard> cameFromMap = new HashMap<>();
        cameFromMap.put(start, null);
        gScoreMap.put(start, 0);
        fScoreMap.put(start, heuristicCostEstimate(start));
        openSet.add(start);
        while (openSet.size() > 0) {
            GameBoard current = openSet.remove();
            if (isSolved(current)) {
                return reconstructPathAndReturn(cameFromMap, current);
            }
            for (GameBoard neighborBoard : getAllNeighborsBoards(current)) {
                if (!cameFromMap.containsKey(neighborBoard)) {
                    cameFromMap.put(neighborBoard, current);
                    Integer tentativeGScore = gScoreMap.get(current) + 1;
                    gScoreMap.put(neighborBoard, tentativeGScore);
                    int estimateHeuristicScore = heuristicCostEstimate(neighborBoard);
                    fScoreMap.put(neighborBoard, tentativeGScore + estimateHeuristicScore);
                    openSet.add(neighborBoard);
                }
            }
        }
        return null;
    }

    private static List<GameBoard> reconstructPathAndReturn(HashMap<GameBoard, GameBoard> predecessor, GameBoard candidate) {
        LinkedList<GameBoard> solution = new LinkedList<>();
        GameBoard backtracePath = candidate;
        while (backtracePath != null) {
            solution.addFirst(backtracePath);
            backtracePath = predecessor.get(backtracePath);
        }
        return solution;
    }
}
