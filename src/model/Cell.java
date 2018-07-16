package model;

public class Cell <T> {
    public int positionX;
    public int positionY;
    public final T value;

    public Cell(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
        value = null;
    }
    public Cell(int positionX, int positionY, T value){
        this.positionX = positionX;
        this.positionY = positionY;
        this.value = value;
    }

}
