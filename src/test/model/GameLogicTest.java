package model;

import junit.framework.TestCase;

public class GameLogicTest extends TestCase {

    public void testMove() {

    }

    public void testIsSolved() {
        GameLogic gameLogic = new GameLogic();
        assertTrue("ожидаем, что новая игра изначально собрана", gameLogic.isSolved());
    }

    public void testIsSolvedStartBoard() {
        GameLogic gameLogic = new GameLogic();
        gameLogic.unitForStart();
        assertFalse("ожидаем, что после unitForStart игра будет разобранной ", gameLogic.isSolved());
    }

    public void testIsSolvedAfterMove(){
        GameLogic gameLogic = new GameLogic();
        gameLogic.move(Direction.DOWN);
        assertFalse("ожидаем, что после одного движения вниз в новой игре игра разобрана",gameLogic.isSolved());
        gameLogic.move(Direction.UP);
        assertTrue("ожидаем, что после движения вниз, а потом движения вверх в новой игре игра собрана", gameLogic.isSolved());

        gameLogic.move(Direction.RIGHT);
        assertFalse("ожидаем, что после одного движения вправо в новой игре игра разобрана", gameLogic.isSolved());
        gameLogic.move(Direction.LEFT);
        assertTrue("ожидаем, что после движения вправо, а потом движения вниз в новой игре игра собрана", gameLogic.isSolved());

    }
}