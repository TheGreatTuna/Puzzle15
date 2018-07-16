package view;

import model.Board;
import model.Direction;

import java.util.Scanner;

public class GraphicsModuleImpl implements GraphicsModule {

    private static GraphicsModule graphicsModule = new GraphicsModuleImpl();

    public static GraphicsModule getInstance(){
        return graphicsModule;
    }

    private GraphicsModuleImpl(){
    }

    @Override
    public void draw(Board board) {
        for(Integer[] row: board.configuration){
            for (Integer cell: row){
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public Direction input() {
        System.out.println("Enter direction: (ASWD)");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next().toLowerCase()){
            case "a": return Direction.LEFT;
            case "d": return Direction.RIGHT;
            case "s": return Direction.DOWN;
            case "w": return Direction.UP;
        }
        return Direction.NOT_DIRECTION;
    }
}
