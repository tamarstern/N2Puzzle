package Model;

import Exceptions.IllegalXIndexValue;
import Exceptions.IllegalYIndexValue;

import java.util.Objects;

public class GameBoardPosition {
    private int xIndex;
    private int yIndex;

    public GameBoardPosition(int xIndex, int yIndex) {
        if(xIndex < -1) {
            throw new IllegalXIndexValue("Invalid x value");
        }
        if(yIndex < -1) {
            throw new IllegalYIndexValue("Invalid y value");
        }
        this.xIndex =xIndex;
        this.yIndex =yIndex;
    }

    public GameBoardPosition(GameBoardPosition other) {
        this.xIndex = other.getXIndex();
        this.yIndex = other.getYIndex();
    }

    public int getXIndex() {
        return xIndex;
    }

    public int getYIndex() {
        return yIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj ==  this) {
            return true;
        }
        if(obj instanceof GameBoardPosition) {
           GameBoardPosition other = (GameBoardPosition) obj;
           return this.getXIndex() == other.getXIndex() && this.getYIndex() == other.getYIndex();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getXIndex(), getYIndex());
    }
}
