package view;

import model.Board;

public interface GraphicsModule {

    /**
     * Отрисовывает переданное игровое поле
     *
     * @param board Игровое поле, которое необходимо отрисовать
     */
    void draw(Board board);

    /**
     * Заключительные действия, на случай, если модулю нужно подчистить за собой.
     */
    void destroy();

    /**
     *
     *
     */
    void input();
}
