package view;

import model.Board;
import model.Direction;

public interface GraphicsModule {

    /**
     * Отрисовывает переданное игровое поле
     *
     * @param board Игровое поле, которое необходимо отрисовать
     */
    void draw(Board board);

    /**
     *
     *
     */
    Direction input();
}
