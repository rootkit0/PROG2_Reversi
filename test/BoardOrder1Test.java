

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardOrder1Test {

    @Test
    public void test_to_string() {
        Board board = new Board(1);
        String expected = "" +
                "wb\n" +
                "bw\n";
        assertEquals(expected, board.toString());
    }

    @Test
    public void test_getStatus() {
        Board board = new Board(1);
        assertEquals("B:  2 W:  2", board.getStatus());
    }
}
