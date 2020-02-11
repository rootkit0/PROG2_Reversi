

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void can_get_row_and_column() {
        Position pos = new Position(3, 2);
        assertEquals(3, pos.getRow());
        assertEquals(2, pos.getColumn());
    }

    @Test
    public void move_creates_new_position_and_not_modify_this() {
        Position begin = new Position(3, 2);
        Position end = begin.move(Direction.NE);
        
        assertEquals(2, end.getRow());
        assertEquals(3, end.getColumn());
        
        assertEquals(3, begin.getRow());
        assertEquals(2, begin.getColumn());
    }
}