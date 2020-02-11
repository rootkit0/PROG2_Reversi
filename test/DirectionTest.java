

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void north() {
        assertEquals(-1, Direction.N.getChangeInRow());
        assertEquals(0, Direction.N.getChangeInColumn());
    }

    @Test
    public void south() {
        assertEquals(1, Direction.S.getChangeInRow());
        assertEquals(0, Direction.S.getChangeInColumn());
    }

    @Test
    public void east() {
        assertEquals(0, Direction.E.getChangeInRow());
        assertEquals(1, Direction.E.getChangeInColumn());
    }

    @Test
    public void west() {
        assertEquals(0, Direction.W.getChangeInRow());
        assertEquals(-1, Direction.W.getChangeInColumn());
    }

    @Test
    public void north_west() {
        assertEquals(-1, Direction.NW.getChangeInRow());
        assertEquals(-1, Direction.NW.getChangeInColumn());
    }

    @Test
    public void north_east() {
        assertEquals(-1, Direction.NE.getChangeInRow());
        assertEquals(1, Direction.NE.getChangeInColumn());
    }

    @Test
    public void south_west() {
        assertEquals(1, Direction.SW.getChangeInRow());
        assertEquals(-1, Direction.SW.getChangeInColumn());
    }

    @Test
    public void south_east() {
        assertEquals(1, Direction.SE.getChangeInRow());
        assertEquals(1, Direction.SE.getChangeInColumn());
    }

    private static boolean contains(Direction[] dirArray, Direction direction) {
        for (int i=0; i < dirArray.length; i++)
            if (dirArray[i] == direction)
                return true;
        return false;
    }

    @Test
    public void all_contains_all_directions() {
        assertEquals(8, Direction.ALL.length);
        assertTrue(contains(Direction.ALL, Direction.N));
        assertTrue(contains(Direction.ALL, Direction.S));
        assertTrue(contains(Direction.ALL, Direction.E));
        assertTrue(contains(Direction.ALL, Direction.W));
        assertTrue(contains(Direction.ALL, Direction.NE));
        assertTrue(contains(Direction.ALL, Direction.NW));
        assertTrue(contains(Direction.ALL, Direction.SE));
        assertTrue(contains(Direction.ALL, Direction.SW));
    }
}