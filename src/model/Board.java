package model;

import java.util.*;

public class Board {

    public Integer[][] configuration;

    public Pair<Integer, Integer> emptyCellPosition;

    public Board(){

        this(4, 4);
    }

    public Board(int n, int m){
        configuration = new Integer[n][m];
        emptyCellPosition = new Pair<>(n-1, m-1);
        initialBoard();
    }

    public void shuffle(){
        int n = configuration.length;
        int m = configuration[0].length;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < n*m; i++)
            data.add(i);
        Collections.shuffle(data);
        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                int value = data.get(i*n + j);
                if(value == 0) {
                    emptyCellPosition.setLeft(i);
                    emptyCellPosition.setRight(j);
                }
                configuration[i][j] = value;
            }
        }
    }

    public void initialBoard(){
        int n = configuration.length;
        int m = configuration[0].length;
        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                configuration[i][j] = i*n + j + 1;
            }
        }
        configuration[n-1][m-1] = 0;
        emptyCellPosition.setLeft(n-1);
        emptyCellPosition.setRight(m-1);
    }

    public void move(Direction direction){
        if (isPossibleMove(direction))
            switch (direction){
                case UP:
                case DOWN:
                    moveVertical(direction);
                    break;
                case LEFT:
                case RIGHT:
                    moveHorizontal(direction);
                    break;
            }
    }

    /**
     *
     * @param direction
     * 1 if UP
     * -1 if DOWN
     */
    private void moveVertical(Direction direction){
        int step = (direction == Direction.UP) ? -1 : 1;
        int x = emptyCellPosition.getLeft();
        int y = emptyCellPosition.getRight();
        swap(x, y, x - step, y);
        emptyCellPosition.setLeft(x-step);
    }

    /**
     *
     * @param direction
     * 1 if RIGHT
     * -1 if LEFT
     */
    private void moveHorizontal(Direction direction){
        int step = (direction == Direction.RIGHT) ? 1 : -1;
        int x = emptyCellPosition.getLeft();
        int y = emptyCellPosition.getRight();
        swap(x, y, x, y - step);
        emptyCellPosition.setRight(y - step);
    }

    private void swap(int x1, int y1, int x2, int y2){
        int temp = configuration[x1][y1];
        configuration[x1][y1] = configuration[x2][y2];
        configuration[x2][y2] = temp;
    }

    private boolean isPossibleMove(Direction direction) {
        switch (direction) {
            case RIGHT:
                return emptyCellPosition.getRight() != 0;
            case LEFT:
                return emptyCellPosition.getRight() != configuration.length - 1;
            case DOWN:
                return emptyCellPosition.getLeft() != 0;
            case UP:
                return emptyCellPosition.getLeft() != configuration[0].length - 1;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Board)) return false;
        Board board1 = (Board) obj;
        return emptyCellPosition.equals(board1.emptyCellPosition)
                && Arrays.deepEquals(board1.configuration, configuration);
    }
}
