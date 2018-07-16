package model;

import java.util.HashSet;
import java.util.Set;

public class GameLogic {

    private Board board;

    private Set<Integer> incorrectElements;

    public GameLogic(){
        board = new Board(4,4);
        incorrectElements = new HashSet<>();
    }

    public void unitForStart(){
        do {
            board.shuffle();
        } while (!isDecidable());

        checkIncorrectElements();
    }

    public Board getBoard() {
        return board;
    }

    private boolean isDecidable(){
        Integer[][] conf = board.configuration;
        Integer emptyRow = board.emptyCellPosition.getRight();
        Integer sumTotal = 0;
        for(int i = 0; i < conf.length; i++){
            for(int j = 0; j < conf.length; j++){
                Integer sumCurrent = 0;
                for (int m = i; m < conf.length; m++) {
                    if(i == m){
                        for(int n = j; n < conf.length; n++){
                            if(conf[n][m] < conf[i][j]) sumCurrent ^= 1;
                        }
                    }
                    else{
                        for(int n = 0; n < conf.length; n++){
                            if(conf[n][m] < conf[i][j]) sumCurrent ^= 1;
                        }
                    }
                }
                sumTotal ^= sumCurrent;
            }
        }
        return (emptyRow ^ sumTotal) == 0;
    }

    public void move(Direction direction){
        int x = board.emptyCellPosition.getLeft();
        int y = board.emptyCellPosition.getRight();
        board.move(direction);
        int expectedValue = x*board.configuration[0].length+y+1;
        if(board.configuration[x][y] == expectedValue){
            incorrectElements.remove(expectedValue);
        }
        else {
            incorrectElements.add(expectedValue);
        }
    }

    public boolean isSolved() {
        return incorrectElements.isEmpty();
    }

    private void checkIncorrectElements(){
        int n = board.configuration[0].length;
        int m = board.configuration.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int expectedValue = (i*n + j + 1) %(n*m);
                if (board.configuration[i][j] != expectedValue)
                    incorrectElements.add(expectedValue);
            }
        }
    }
}
