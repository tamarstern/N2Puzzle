package Model;

import Exceptions.IllegalBoardException;
import Exceptions.InvalidMoveException;

import java.util.*;

public class GameBoard {

    private int NSize;
    private int[][] board;
    private GameBoardPosition blankPosition;
    ArrayList<GameBoardPosition> allGameBoardPositions;


    public GameBoard(int NSize) {
        this.NSize = NSize;
        board = new int[NSize][NSize];
        initAllGameBoardPositions();
        initBoardValues(NSize);
        initBoardBlankPosition(NSize);
    }

    public GameBoard(GameBoard other) {
        this(other.getNSize());
        for (GameBoardPosition position : this.allGameBoardPositions) {
            setPositionValue(position, other.getPositionValue(position));
        }
        blankPosition = other.getBlankPosition();
    }

    private void initBoardBlankPosition(int NSize) {
        int blankPositionInitialIndex = NSize - 1;
        blankPosition = new GameBoardPosition(blankPositionInitialIndex,
                blankPositionInitialIndex);
        setPositionValue(blankPosition, 0);
    }

    private void initBoardValues(int NSize) {
        for (GameBoardPosition position : this.allGameBoardPositions) {
            int x = position.getXIndex();
            int y = position.getYIndex();
            int posValue = x * NSize + y + 1;
            setPositionValue(position, posValue);
        }
    }

    public List<GameBoardPosition> getAllGameBoardPositions() {
        List<GameBoardPosition> positions = new ArrayList<>();
        for (GameBoardPosition position : this.allGameBoardPositions) {
            GameBoardPosition clone = new GameBoardPosition(position);
            positions.add(clone);
        }
        return positions;
    }

    private void initAllGameBoardPositions() {
        this.allGameBoardPositions = new ArrayList<>();
        for (int i = 0; i < this.NSize; i++) {
            for (int j = 0; j < this.NSize; j++) {
                allGameBoardPositions.add(new GameBoardPosition(i, j));
            }
        }
    }

    public int getPositionValue(GameBoardPosition position) {
        return board[position.getXIndex()][position.getYIndex()];
    }

    public void setPositionValue(GameBoardPosition position, int value) {
        board[position.getXIndex()][position.getYIndex()] = value;
    }

    public GameBoardPosition getBlankPosition() {
        return new GameBoardPosition(blankPosition);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof GameBoard) {
            for (GameBoardPosition p : this.allGameBoardPositions) {
                if (this.getPositionValue(p) != ((GameBoard) obj).getPositionValue(p)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (GameBoardPosition p : this.allGameBoardPositions) {
            hashCode = (hashCode * NSize * NSize) + this.getPositionValue(p);
        }
        return hashCode;
    }


    public List<GameBoardPosition> getAllValidMoves() {
        ArrayList<GameBoardPosition> validMoves = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int xIndex = this.blankPosition.getXIndex() + i;
                int yIndex = this.blankPosition.getYIndex() + j;
                GameBoardPosition boardPosition = new GameBoardPosition(xIndex,
                        yIndex);
                if (isValidMove(boardPosition)) {
                    validMoves.add(boardPosition);
                }
            }
        }
        return validMoves;
    }

    private boolean isValidMove(GameBoardPosition p) {
        int posX = p.getXIndex();
        if ((posX < 0) || (posX >= this.NSize)) {
            return false;
        }
        int posY = p.getYIndex();
        if ((posY < 0) || (posY >= this.NSize)) {
            return false;
        }
        int dx = this.blankPosition.getXIndex() - posX;
        int dy = this.blankPosition.getYIndex() - posY;
        return (Math.abs(dx) + Math.abs(dy) == 1);
    }

    public void executeGameMove(GameBoardPosition position) {
        if (!isValidMove(position)) {
            throw new InvalidMoveException("Received an invalid executeGameMove");
        }
        if (getPositionValue(blankPosition) != 0) {
            throw new IllegalBoardException("Board is in invalid state. Aborting");
        }
        int value = getPositionValue(position);
        setPositionValue(blankPosition, value);
        setPositionValue(position, 0);
        this.blankPosition = position;
    }

    public void shuffle(int iterations) {
        for (int i = 0; i < iterations; i++) {
            List<GameBoardPosition> validMoves = getAllValidMoves();
            int validMovePosition = (int) (Math.random() * validMoves.size());
            GameBoardPosition move = validMoves.get(validMovePosition);
            this.executeGameMove(move);
        }
    }

    public int getNSize() {
        return NSize;
    }
}
