package view;

import model.Board;

import java.util.Scanner;

public class GraphicsModuleImpl implements GraphicsModule {
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
    public void destroy() {
        System.out.flush();
    }

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);
    }
}
