package controller;

import model.Board;
import view.GraphicsModule;

import java.util.Set;

public class GamePuzzle15 implements Game {

    private Board board;

    private Set<Integer> incorrectElements;

    private GraphicsModule graphicsModule;

    public GamePuzzle15(){
        board = new Board(4, 4);
    }

    @Override
    public void run(){

        board.shuffle();

        while(!isSolved()){


//            input();
//            logic();
            graphicsModule.destroy();
            graphicsModule.draw(board);
        }

        graphicsModule.destroy();
    }

    private boolean isDecidable(Board b){
        Integer[][] conf = b.configuration;
        Integer emptyRow = b.emptyCellPosition.getRight();
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

    protected boolean isSolved() {
        return incorrectElements.isEmpty();
    }

    private void checkIncorrectElements(){
        for(int i = 0; i < board.configuration.length; i++){
            for(int j = 0; j < board.configuration[0].length; j++){
                if (board.configuration[i][j] != i*board.configuration.length + j + 1)
                incorrectElements.add(i*board.configuration.length + j + 1);
            }
        }
    }
}
