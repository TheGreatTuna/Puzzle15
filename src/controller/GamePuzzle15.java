package controller;

import model.Direction;
import model.GameLogic;
import view.GraphicsModule;
import view.GraphicsModuleImpl;

public class GamePuzzle15 implements Game {

    private GameLogic gameLogic;

    private GraphicsModule graphicsModule = GraphicsModuleImpl.getInstance();

    public GamePuzzle15(){
        gameLogic = new GameLogic();
    }

    @Override
    public void run(){

        gameLogic.unitForStart();
        graphicsModule.draw(gameLogic.getBoard());

        while(!gameLogic.isSolved()){


            Direction direction = graphicsModule.input();
            gameLogic.move(direction);
            graphicsModule.draw(gameLogic.getBoard());
        }

        System.out.println("You win!");
    }

}
