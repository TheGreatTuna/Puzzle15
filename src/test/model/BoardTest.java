package model;

import junit.framework.TestCase;
import model.Board;
import model.Direction;

import java.util.HashSet;
import java.util.Set;

import static java.util.Optional.ofNullable;

public class BoardTest extends TestCase {

    public void testShuffle() {

        Board b = new Board();

        b.shuffle();
        assertTrue(NotHaveDuplicate(b));

    }

    private boolean NotHaveDuplicate(Board b){
        Set<Integer> allValues = new HashSet<>();
        Boolean result = true;
        for(int i = 0; i < 16; i++)
        {
            allValues.add(i);
        }
        for(int i = 0; i < b.configuration.length; i++){
            for(int j = 0; j < b.configuration[0].length; j++){
                result &= allValues.remove(b.configuration[i][j]);
            }
        }
        return result && allValues.isEmpty();
    }
    public void testInitialBoard() {
        Board test = new Board();
        Board temp = new Board();
        test.shuffle();
        assertFalse(test.equals(temp));
        test.initialBoard();
        assertTrue(NotHaveDuplicate(test));
        assertTrue(temp.equals(test));
    }

    public void testMoveRight() {
        Board test = new Board();
        test.shuffle();
        Board copy = cloneBoard(test);

        int beforeEmptyCellX =  test.emptyCellPosition.getLeft();
        int beforeEmptyCellY =  test.emptyCellPosition.getRight();

        if(test.emptyCellPosition.getRight()==0){
            test.move(Direction.RIGHT);
            assertTrue(test.equals(copy));
        } else {
            int template = test.configuration[test.emptyCellPosition.getLeft()][test.emptyCellPosition.getRight() - 1];
            test.move(Direction.RIGHT);
            assertTrue(equalsWithoutTwoElements(test.configuration, copy.configuration,
                            test.emptyCellPosition, copy.emptyCellPosition));

            assertEquals((int) test.configuration[beforeEmptyCellX][beforeEmptyCellX],
                    template);
            assertEquals(0, (int) test.configuration[beforeEmptyCellX][beforeEmptyCellY-1]);

        }
    }

    public void testMoveLeft() {

        Board test = new Board();
        test.shuffle();
        Board copy = cloneBoard(test);

        int beforeEmptyCellX =  test.emptyCellPosition.getLeft();
        int beforeEmptyCellY =  test.emptyCellPosition.getRight();

        if(test.emptyCellPosition.getRight()==(test.configuration[0].length-1)){
            test.move(Direction.LEFT);
            assertTrue(test.equals(copy));
        } else {
            int template = test.configuration[test.emptyCellPosition.getLeft()][test.emptyCellPosition.getRight() + 1];
            test.move(Direction.LEFT);
            assertTrue(equalsWithoutTwoElements(test.configuration, copy.configuration,
                    test.emptyCellPosition, copy.emptyCellPosition));

            assertEquals((int) test.configuration[beforeEmptyCellX][beforeEmptyCellX],
                    template);
            assertEquals(0, (int) test.configuration[beforeEmptyCellX][beforeEmptyCellY+1]);

        }
    }

    public void testMoveUp() {

        Board test = new Board();
        test.shuffle();
        Board copy = cloneBoard(test);

        int beforeEmptyCellX =  test.emptyCellPosition.getLeft();
        int beforeEmptyCellY =  test.emptyCellPosition.getRight();

        if(test.emptyCellPosition.getLeft()==(test.configuration.length-1)){
            test.move(Direction.UP);
            assertTrue(test.equals(copy));
        } else {
            int template = test.configuration[test.emptyCellPosition.getLeft() + 1][test.emptyCellPosition.getRight()];
            test.move(Direction.UP);
            assertTrue(equalsWithoutTwoElements(test.configuration, copy.configuration,
                    test.emptyCellPosition, copy.emptyCellPosition));

            assertEquals((int) test.configuration[beforeEmptyCellX][beforeEmptyCellX],
                    template);
            assertEquals(0, (int) test.configuration[beforeEmptyCellX + 1][beforeEmptyCellY]);

        }
    }

    public void testMoveDown() {

        Board test = new Board();
        test.shuffle();
        Board copy = cloneBoard(test);

        int beforeEmptyCellX =  test.emptyCellPosition.getLeft();
        int beforeEmptyCellY =  test.emptyCellPosition.getRight();

        if(test.emptyCellPosition.getLeft()==0){
            test.move(Direction.DOWN);
            assertTrue(test.equals(copy));
        } else {
            int template = test.configuration[test.emptyCellPosition.getLeft() - 1][test.emptyCellPosition.getRight()];
            test.move(Direction.DOWN);
            assertTrue(equalsWithoutTwoElements(test.configuration, copy.configuration,
                    test.emptyCellPosition, copy.emptyCellPosition));

            assertEquals((int) test.configuration[beforeEmptyCellX][beforeEmptyCellX],
                    template);
            assertEquals(0, (int) test.configuration[beforeEmptyCellX - 1][beforeEmptyCellY]);

        }
    }

    public void testMoveIncorrect(){
        Board test = new Board();
        test.shuffle();
        Board copy = cloneBoard(test);

        test.move(Direction.NOT_DIRECTION);

        assertEquals(test,copy);
    }

    public void testMoveNull(){

    }


    private boolean equalsWithoutTwoElements(Integer[][] arr1, Integer[][] arr2,
                                             Pair<Integer, Integer> index1,
                                             Pair<Integer, Integer> index2){
        if(arr1.length!=arr2.length || arr1[0].length!=arr1.length) return false;
        for(int i = 0; i<arr1.length; i++)
            for (int j = 0; j < arr1[0].length; j++)
                if (arr1[i][j] != arr2[i][j]) {
                    Pair p = new Pair<>(i, j);
                    if (!(p.equals(index1) || p.equals(index2)))
                        return false;
                }
        return true;
    }

    private Board cloneBoard(Board b){
        Board clone = new Board();
        clone.configuration = b.configuration.clone();
        for (int i = 0; i < b.configuration.length; i++) {
            clone.configuration[i] = b.configuration[i].clone();
        }
        clone.emptyCellPosition.setRight(b.emptyCellPosition.getRight());
        clone.emptyCellPosition.setLeft(b.emptyCellPosition.getLeft());
        return clone;
    }

}